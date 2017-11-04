package com.idealorb.tiltfx;

/**
 * Created by Ismail on 11/4/2017.
 */

import com.idealorb.tiltfx.dbproperties.Currency;

import java.util.List;

/**
 * Access point for managing currency data.
 */
public interface CurrencyDataSource {

    /**
     * Gets the currency from the data source.
     *
     * @return the currency from the data source.
     */
    List<Currency> getCurrencyList();

    /**
     * Inserts the currency list into the data source, or, if this is an existing currency list, updates it.
     *
     * @param currencies the currencies to be inserted or updated.
     */
    void insertOrUpdateCurrency(List<Currency> currencies);

    /**
     * Inserts the currency into the data source, or, if this is an existing currency, updates it.
     *
     * @param currency the currency to be inserted or updated.
     */
    void insertOrUpdateCurrency(Currency currency);

    /**
     * Deletes all users from the data source.
     */
    void deleteAllUsers();
}
