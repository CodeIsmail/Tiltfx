package com.idealorb.tiltfx.networking;

import com.idealorb.tiltfx.model.CryptoCurrency;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ismail on 11/4/2017.
 */
//RetrofitLayer: It will make the main network request
public interface ICryptoCompareApi {


    @GET("pricemulti?")
    Flowable<CryptoCurrency> getExchangeRateData(@Query("fsyms") String cryptoCurrencyString,
                                                 @Query("tsyms") String currenciesString);

    @GET("pricemulti?")
    Flowable<CryptoCurrency> getBtcExchangeRateData(@Query("fsyms") String cryptoCurrencyString,
                                                    @Query("tsyms") String currenciesString);
}
