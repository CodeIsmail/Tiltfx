package com.idealorb.tiltfx;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.idealorb.tiltfx.dbproperties.AppDatabase;
import com.idealorb.tiltfx.dbproperties.Currency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyFXActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Currency>>{

    public static  final String LOG_TAG = CurrencyFXActivity.class.getName();
    public static final String[] currencyNames = {"GBP","USD","EUR","NGN","JPY","CHF",
            "CAD","INR","RUB","ZAR","MXN","MYR","DKK","SGD","SAR","AED","KRW",
            "TRY","NOK","SEK"};
    private static final String CRYPTOCOMPARE_REQUEST_URL = "https://min-api.cryptocompare.com/" +
            "data/pricemulti?";
    public static String cryptoCurrency = "";
    private AppDatabase database;
    private  CurrencyAdapter currencyAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_fx);


        ListView listView = findViewById(R.id.list_view);
        currencyAdapter = new CurrencyAdapter(this, new ArrayList<Currency>());

        listView.setAdapter(currencyAdapter);

        //create database
        database = AppDatabase.getDatabase(getApplicationContext());


        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String cryptoPrefs = sharedPrefs.getString(
                getString(R.string.settings_cryptocurrency_key),
                getString(R.string.settings_cryptocurrency_by_default));


        TextView cryptoTextViewLabel = findViewById(R.id.cryptocurrency_textview);

        if(cryptoPrefs.equalsIgnoreCase("Bitcoin"))
        {
            cryptoTextViewLabel.setText(getString(R.string.bitcoin_label));
            cryptoCurrency = "BTC";
        }else
        {
            cryptoTextViewLabel.setText(getString(R.string.ether_label));
            cryptoCurrency = "ETH";
        }

        ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo  = connManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()){
            getLoaderManager().initLoader(0, null, this);
        }else{
            //mProgress.setVisibility(View.GONE);
            String noInternetMessage = "No internet connection";
            Log.v("CurrencyFXActivity", noInternetMessage);
            //mTextView.setText(noInternetMessage);
        }

        //new DatabaseAsync().execute();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent converterIntent =
                        new Intent(CurrencyFXActivity.this, ConverterActivity.class);
                //find the currency that was clicked
                Currency selectedCurrency = (Currency) currencyAdapter.getItem(position);

                String[] currencyToString = {selectedCurrency.getCurrencyTagName(),
                        Double.toString(selectedCurrency.getBitcoinExchangeRate()), "BTC"};
                converterIntent.putExtra("CurrencyData", currencyToString
                );

                startActivity(converterIntent);

                overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String createStringFromArray()
    {
        StringBuilder urlBuilder = new StringBuilder("");
        for (int i = 0; i < currencyNames.length; i++)
        {
            urlBuilder.append(currencyNames[i]);
            if (i==(currencyNames.length-1))
                break;
            urlBuilder.append(",");

        }
        return  urlBuilder.toString();
    }

    @Override
    public Loader<List<Currency>> onCreateLoader(int i, Bundle bundle) {



        Uri baseUri = Uri.parse(CRYPTOCOMPARE_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();


        uriBuilder.appendQueryParameter("fsyms", cryptoCurrency);
        uriBuilder.appendQueryParameter("tsyms", createStringFromArray() );



        return new CurrencyFXLoader(this, database, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Currency>> loader, List<Currency> currencyList) {

        Log.v("CurrencyFXActivity", "Log Message from onLoadFinished()");
        // Clear the adapter of previous exchange rate data
        currencyAdapter.clear();

        //mProgress.setVisibility(View.GONE);
        // If there is a valid list of {@link Currency}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (currencyList != null && !currencyList.isEmpty()) {

            currencyAdapter.addAll(currencyList);
//            LiveData<List<Currency>> currencyLiveData = database.currencyDao().fetchAllCurrencies();
//            currencyLiveData.observe(this, new Observer<List<Currency>>() {
//                @Override
//                public void onChanged(@Nullable List<Currency> currencyData) {
//                    //Update your UI here.
//                    currencyAdapter.addAll(currencyData);
//                    currencyAdapter.notifyDataSetChanged();
//                    Log.v("CurrencyFX", Integer.toString(currencyData.size()));
//                }
//            });
        }else{
            String emptyMessage = "No currency exchange rate found!";
            //mTextView.setText(emptyMessage);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Currency>> loader) {
        Log.v("CurrencyFXActivity", "Log Message from onLoaderReset()");
        currencyAdapter.clear();
    }

}
