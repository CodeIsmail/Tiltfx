package com.idealorb.tiltfx.networking;

import android.util.Log;

import com.idealorb.tiltfx.dbproperties.CurrencyDao;
import com.idealorb.tiltfx.model.CryptoCurrency;
import com.idealorb.tiltfx.networking.exception.NetworkRequestInternalException;
import com.idealorb.tiltfx.networking.exception.NetworkRequestTechFailureException;

import java.net.HttpRetryException;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Ismail on 11/4/2017.
 */
//APIServiceLayer: It will make network request, parse response and process it
public class CryptoCompareApiService {

    private ICryptoCompareApi cryptoCompareApi;
    //private AppDatabase appDatabase;
    private boolean isRequestingData;

    private String cryptoCurrency;
    private CurrencyDao currencyDao;
    private String[] currencyNames = {};


    public CryptoCompareApiService(Retrofit retrofit, String[] currencyNames, String crypto) {


        this.cryptoCompareApi = retrofit.create(ICryptoCompareApi.class);
        this.currencyNames = currencyNames;
        this.cryptoCurrency = crypto;


    }

    public void setCryptoCompareApi(ICryptoCompareApi cryptoCompareApi) {
        this.cryptoCompareApi = cryptoCompareApi;
    }

    public Flowable<CryptoCurrency> getExchangeRate() {

        return cryptoCompareApi.getBtcExchangeRateData(cryptoCurrency, createStringFromArray())
                .doOnSubscribe(disposable -> isRequestingData = true)
                .doOnTerminate(() -> isRequestingData = false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(this::handleDataError)
                .doOnNext(currencies -> Log.v("CyrptoCompareApiService", currencies.toString()));
    }

    private Flowable handleDataError(Throwable throwable) {

        if (throwable instanceof HttpRetryException) {

            int status = ((HttpRetryException) throwable).responseCode();

            if (status == 401) {
                throw new IllegalArgumentException();
            } else {
                throw new NetworkRequestInternalException();
            }

        } else {
            throw new NetworkRequestTechFailureException();
        }
    }

    private String createStringFromArray() {
        StringBuilder urlBuilder = new StringBuilder("");
        for (int i = 0; i < currencyNames.length; i++) {
            urlBuilder.append(currencyNames[i]);
            if (i == (currencyNames.length - 1))
                break;
            urlBuilder.append(",");

        }
        return urlBuilder.toString();
    }


}
