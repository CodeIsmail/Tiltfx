package com.idealorb.tiltfx;

/**
 * Created by Ismail on 10/30/2017.
 */

class CurrencyFXLoader {
//extends AsyncTaskLoader<List<Currency>>
//    private final AppDatabase appDB;
//    /** URL for exchange rate data from the CRYPTOCOMPARE dataset*/
//    private String CRYPTOCOMPARE_REQUEST_URL = "";
//    private List<Currency> mData;
//    private CurrencyRepository currencyRepo;
//
//    public CurrencyFXLoader(Context context, AppDatabase appDB, String currencyURL) {
//        super(context);
//        CRYPTOCOMPARE_REQUEST_URL = currencyURL;
//        this.appDB = appDB;
//        currencyRepo = new CurrencyRepository(appDB.currencyDao());
//    }
//
//    @Override
//    protected void onStartLoading() {
//        if (mData != null) {
//            // Use cached data
//            deliverResult(mData);
//        } else {
//            // We have no data, so kick off loading it
//            forceLoad();
//        }
//
//        Log.v("CurrencyFXLoader", "Log Message from onStartLoading()");
//    }
//
//    @Override
//    public List<Currency> loadInBackground() {
//        // Don't perform the request if the URL is null.
//        if (CRYPTOCOMPARE_REQUEST_URL == null) {
//            return null;
//        }
//
//        Log.v("CurrencyLoader", "Log Message from loadInBackground()");
//
//        List<Currency> currencyList = QueryApi.fetchExchangeRateData(CRYPTOCOMPARE_REQUEST_URL);
//        currencyRepo.insertOrUpdateCurrency(currencyList);
//
//        return currencyRepo.getCurrencyList();
//    }
//
//    @Override
//    public void deliverResult(List<Currency> data) {
//        // We’ll save the data for later retrieval
//        mData = data;
//        // We can do any pre-processing we want here
//        // Just remember this is on the UI thread so nothing lengthy!
//        super.deliverResult(data);
//    }
}
