package com.idealorb.tiltfx.dbproperties;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Ismail on 10/23/2017.
 */
@Dao
public interface CurrencyDao {

    @Insert
    void insertCurrency(Currency currency);

    @Insert
    void insertCurrencies(Currency... currencies);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertListCurrencies(List<Currency> currencies);

    @Query("select * from currency")
    List<Currency> fetchAllCurrencies();

    @Query("select * from currency where currencyID = :currencyId")
    Currency fetchCurrency(long currencyId);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCurrency(Currency currency);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCurrencies(Currency... currency);


    @Delete()
    void deleteCurrency(Currency currency);

    @Delete()
    void deleteCurrencies(List<Currency> currency);

}
