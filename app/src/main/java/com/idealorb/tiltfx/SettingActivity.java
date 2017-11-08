package com.idealorb.tiltfx;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class SettingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


    }

    public static class CurrencyFXPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {


        public static Observable<String> getPref(String cryptoPrefs) {
            Log.v("SettingActivity", "crptocurrency value: " + cryptoPrefs);
            return new Observable<String>() {
                @Override
                protected void subscribeActual(Observer<? super String> observer) {
                    observer.onNext(cryptoPrefs);
                }
            };
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_main);

            Preference cryptoPreference = findPreference(getString(R.string.settings_cryptocurrency_key));
            bindPreferenceSummaryToValue(cryptoPreference);


        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                CharSequence[] labels = listPreference.getEntryValues();
                preference.setSummary(labels[prefIndex]);
            }

            return true;
        }

        private void bindPreferenceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            String preferenceString = preferences.getString(preference.getKey(), "");
            onPreferenceChange(preference, preferenceString);
        }
    }
}
