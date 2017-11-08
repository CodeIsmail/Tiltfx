package com.idealorb.tiltfx.dbproperties;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Ismail on 10/23/2017.
 */

@Entity(tableName = "currency")
public class Currency {

    @PrimaryKey()
    private int currencyID;

    @ColumnInfo(name = "currency_tagname")
    private String currencyTagName;

    @ColumnInfo(name = "bitcoin_xRate")
    private double bitcoinExchangeRate;

    /*@ColumnInfo(name = "ether_xRate")
    private double etherExchangeRate;*/


   //
    public Currency(int currencyID, String currencyTagName,  double bitcoinExchangeRate) {
        this.currencyID = currencyID;
        this.currencyTagName = currencyTagName;
        this.bitcoinExchangeRate = bitcoinExchangeRate;

    }

    //get currency id from database
    public int getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(int currencyID) {
        this.currencyID = currencyID;
    }

    //get currency tag name (USD) from database
    public String getCurrencyTagName() {
        return currencyTagName;
    }

    public void setCurrencyTagName(String currencyTagName) {
        this.currencyTagName = currencyTagName;
    }

    //get bitcoin exchange rate
    public double getBitcoinExchangeRate() {
        return bitcoinExchangeRate;
    }

    public void setBitcoinExchangeRate(double bitcoinExchangeRate) {
        this.bitcoinExchangeRate = bitcoinExchangeRate;
    }


    /*public void setEtherExchangeRate(double etherExchangeRate) {
        this.etherExchangeRate = etherExchangeRate;
    }


    //get etherium exchange rate
    public double getEtherExchangeRate() {
        return etherExchangeRate;
    }*/

    /*//get data from Currency Tag Name and Etherium Column
    public void getDataFromTagNameAndEtherium(){

    }*/
    @Override
    public String toString() {


        return getCurrencyTagName() + " , " + getBitcoinExchangeRate();
    }
}
