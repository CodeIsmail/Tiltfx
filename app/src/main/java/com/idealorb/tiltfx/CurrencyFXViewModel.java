package com.idealorb.tiltfx;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveDataReactiveStreams;
import android.support.annotation.NonNull;

import com.idealorb.tiltfx.dbproperties.AppDatabase;
import com.idealorb.tiltfx.dbproperties.Currency;
import com.idealorb.tiltfx.dbproperties.CurrencyRepository;

import java.util.List;

/**
 * Created by Ismail on 11/4/2017.
 */

class CurrencyFXViewModel extends AndroidViewModel {

    private final CurrencyRepository currencyRepo;


    public CurrencyFXViewModel(@NonNull Application application) {
        super(application);

        AppDatabase appDatabase = AppDatabase.getDatabase(application);

        String crypto = "BTC";
        currencyRepo = new CurrencyRepository(appDatabase.currencyDao(), crypto);
        currencyRepo.getData();
    }

    public LiveData<List<Currency>> getCurrListLiveData() {
        return LiveDataReactiveStreams.fromPublisher(currencyRepo.getCurrencyList());
    }

}

