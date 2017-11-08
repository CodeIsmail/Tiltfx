package com.idealorb.tiltfx.dbproperties;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Ismail on 10/23/2017.
 */
@Dao
public interface CurrencyDao {

    /**
     * Insert a currency in the database. If the currency already exists, replace it.
     *
     * @param currency the user to be inserted.
     */
    @Insert
    void insertCurrency(Currency currency);

    /**
     * Insert currency list in the database. If the currency list already exists, replace it.
     *
     * @param currencies the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCurrencyList(List<Currency> currencies);

    /**
     * Get the list of currency from the table. Since for simplicity we only have one list of currency in the database,
     * this query gets all users from the table, but limits the result to just the 1st list of currency.
     *
     * @return the list of currency from the table
     */
    @Query("select * from currency")
    Flowable<List<Currency>> fetchAllCurrencies();

    @Query("select * from currency where currencyID = :currencyId")
    Currency fetchCurrency(long currencyId);

    /**
     * Delete all currencies.
     */
    @Query("delete from currency")
    void deleteAllCurrencies();

}
