package com.idealorb.tiltfx;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.idealorb.tiltfx.dbproperties.Currency;

import java.util.ArrayList;

public class CurrencyFXActivity extends AppCompatActivity {

    public static  final String LOG_TAG = CurrencyFXActivity.class.getName();
    private static String cryptoCurrency = "";
    private  CurrencyAdapter currencyAdapter;
    private TextView cryptoTextViewLabel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_fx);


        ListView listView = findViewById(R.id.list);
        cryptoTextViewLabel = findViewById(R.id.cryptocurrency_textview);
        //SwipeRefreshLayout mySwipeRefreshLayout = findViewById(R.id.swiperefresh);
        setPrefToView();
        currencyAdapter = new CurrencyAdapter(this, new ArrayList<>());

        listView.setAdapter(currencyAdapter);
        setPrefToView();
        CurrencyFXViewModel currencyFXViewModel = ViewModelProviders.of(this).get(CurrencyFXViewModel.class);


        ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo  = connManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()){
            currencyFXViewModel.getCurrListLiveData()
                    .observe(this, currencies -> currencyAdapter.addAll(currencies));


        }else{
            //mProgress.setVisibility(View.GONE);
            String noInternetMessage = "No internet connection";
            Log.v("CurrencyFXActivity", noInternetMessage);
            //mTextView.setText(noInternetMessage);
        }

        listView.setOnItemClickListener((adapterView, view, position, l) -> {

            Intent converterIntent =
                    new Intent(CurrencyFXActivity.this, ConverterActivity.class);
            //find the currency that was clicked
            Currency selectedCurrency = (Currency) currencyAdapter.getItem(position);

            String[] currencyToString = {selectedCurrency.getCurrencyTagName(),
                    Double.toString(selectedCurrency.getBitcoinExchangeRate()), cryptoCurrency};
            converterIntent.putExtra("CurrencyData", currencyToString
            );

            startActivity(converterIntent);

            overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);

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


    private void setPrefToView()
    {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String cryptoPrefs = sharedPrefs.getString(
                getString(R.string.settings_cryptocurrency_key),
                getString(R.string.settings_cryptocurrency_by_default));
        Log.v("CurrencyFXActivity", "setPrefs above observable. \ncrptocurrency value: " + cryptoPrefs);
        SettingActivity.CurrencyFXPreferenceFragment
                .getPref(cryptoPrefs)
                .subscribe(s -> {
                    Log.v("CurrencyFXActivity", "setPrefs observer. \ncrptocurrency value: " + cryptoPrefs);
                    if (s.equalsIgnoreCase("Bitcoin")) {
                        cryptoTextViewLabel.setText(getString(R.string.bitcoin_label));
                        cryptoCurrency = "BTC";
                    } else {
                        cryptoTextViewLabel.setText(getString(R.string.ether_label));
                        cryptoCurrency = "ETH";
                    }
                });


    }
}
