package com.idealorb.tiltfx.dbproperties;

import com.idealorb.tiltfx.CurrencyDataSource;

import java.util.List;

/**
 * Created by Ismail on 11/4/2017.
 */

public class CurrencyRepository implements CurrencyDataSource {

    private final CurrencyDao mCurrencyDao;

    public CurrencyRepository(CurrencyDao mCurrencyDao){

        this.mCurrencyDao = mCurrencyDao;
    }

    @Override
    public List<Currency> getCurrencyList() {
        return mCurrencyDao.fetchAllCurrencies();
    }

    @Override
    public void insertOrUpdateCurrency(List<Currency> currencies) {
        mCurrencyDao.insertCurrencyList(currencies);
    }

    @Override
    public void insertOrUpdateCurrency(Currency currency) {
        mCurrencyDao.insertCurrency(currency);
    }

    @Override
    public void deleteAllUsers() {
        mCurrencyDao.deleteAllCurrencies();
    }
}
