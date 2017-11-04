package com.idealorb.tiltfx;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.idealorb.tiltfx.dbproperties.AppDatabase;
import com.idealorb.tiltfx.dbproperties.Currency;

import java.util.List;

/**
 * Created by Ismail on 10/30/2017.
 */

class CurrencyFXLoader extends AsyncTaskLoader<List<Currency>> {

    /** URL for exchange rate data from the CRYPTOCOMPARE dataset*/
    private String CRYPTOCOMPARE_REQUEST_URL = "";
    private List<Currency> mData;
    private final AppDatabase appDB;

    public CurrencyFXLoader(Context context, AppDatabase appDB, String currencyURL) {
        super(context);
        CRYPTOCOMPARE_REQUEST_URL = currencyURL;
        this.appDB = appDB;
    }

    @Override
    protected void onStartLoading() {
        if (mData != null) {
            // Use cached data
            deliverResult(mData);
        } else {
            // We have no data, so kick off loading it
            forceLoad();
        }

        Log.v("CurrencyFXLoader", "Log Message from onStartLoading()");
    }

    @Override
    public List<Currency> loadInBackground() {
        // Don't perform the request if the URL is null.
        if (CRYPTOCOMPARE_REQUEST_URL == null) {
            return null;
        }

        Log.v("CurrencyLoader", "Log Message from loadInBackground()");
        List<Currency> result = QueryApi.fetchExchangeRateData(CRYPTOCOMPARE_REQUEST_URL);

        Log.v("CurrencyFX", Integer.toString(result.size()));
        List<Long> longList = appDB.currencyDao().insertListCurrencies(QueryApi.fetchExchangeRateData(CRYPTOCOMPARE_REQUEST_URL));

        Log.v("CurrencyFX", Integer.toString(longList.size()));
        return appDB.currencyDao().fetchAllCurrencies();
    }

    @Override
    public void deliverResult(List<Currency> data) {
        // We’ll save the data for later retrieval
        mData = data;
        // We can do any pre-processing we want here
        // Just remember this is on the UI thread so nothing lengthy!
        super.deliverResult(data);
    }
}
