package com.idealorb.tiltfx.dbproperties;

import android.arch.persistence.room.Entity;

/**
 * Created by Ismail on 10/30/2017.
 */

@Entity(tableName = "cryptocurrecy")
class CryptoCurrency {

    private long cryptoCurrencyID;
    private double btcCurrency;
    private double ethCurrency;


}
