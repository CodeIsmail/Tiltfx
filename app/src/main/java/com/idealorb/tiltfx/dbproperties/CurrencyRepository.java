package com.idealorb.tiltfx.dbproperties;

import com.idealorb.tiltfx.CurrencyDataSource;
import com.idealorb.tiltfx.networking.CryptoCompareApiService;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ismail on 11/4/2017.
 */

public class CurrencyRepository implements CurrencyDataSource {

    private static final String CRYPTOCOMPARE_REQUEST_URL = "https://min-api.cryptocompare.com/data/";
    private final CurrencyDao mCurrencyDao;
    private final CryptoCompareApiService cryptoCurrencyApiService;
    private final String[] currencyNames = {"GBP", "USD", "EUR", "NGN", "JPY", "CHF",
            "CAD", "INR", "RUB", "ZAR", "MXN", "MYR", "DKK", "SGD", "SAR", "AED", "KRW",
            "TRY", "NOK", "SEK"};

    public CurrencyRepository(CurrencyDao mCurrencyDao, String crypto) {

        this.mCurrencyDao = mCurrencyDao;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CRYPTOCOMPARE_REQUEST_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        this.cryptoCurrencyApiService = new CryptoCompareApiService(retrofit, currencyNames, crypto);


    }

    @Override
    public Flowable<List<Currency>> getCurrencyList() {
        return mCurrencyDao.fetchAllCurrencies();
    }

    @Override
    public void insertOrUpdateCurrency(List<Currency> currencies) {
        mCurrencyDao.insertCurrencyList(currencies);
    }

    @Override
    public void insertOrUpdateCurrency(Currency currency) {
        mCurrencyDao.insertCurrency(currency);
    }

    @Override
    public void deleteAllUsers() {
        mCurrencyDao.deleteAllCurrencies();
    }

    public Flowable<List<Currency>> getData() {

        List<Currency> currencyList = new ArrayList<>();

        return new Flowable<List<Currency>>() {
            @Override
            protected void subscribeActual(Subscriber<? super List<Currency>> s) {
                cryptoCurrencyApiService.getExchangeRate()
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .map(cryptoCurrency -> {

                            for (int i = 0; i < 20; i++) {
                                currencyList.add(new Currency(i + 1, currencyNames[i],
                                        cryptoCurrency.getBTC().getArrayList().get(i)));
                            }

                            insertOrUpdateCurrency(currencyList);
                            return cryptoCurrency;
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


}
