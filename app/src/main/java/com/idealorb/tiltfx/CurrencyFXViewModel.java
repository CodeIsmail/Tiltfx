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

public class CurrencyFXViewModel extends AndroidViewModel {

    private CurrencyRepository currencyRepo;
    private AppDatabase appDatabase;
    private String crypto = "BTC";


    public CurrencyFXViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(application);

        currencyRepo = new CurrencyRepository(appDatabase.currencyDao(), crypto);
        currencyRepo.changeDataFormat()
                .doOnNext(Object::toString);
    }

    public LiveData<List<Currency>> getCurrListLiveData() {
        return LiveDataReactiveStreams.fromPublisher(currencyRepo.getCurrencyList());
    }

}

