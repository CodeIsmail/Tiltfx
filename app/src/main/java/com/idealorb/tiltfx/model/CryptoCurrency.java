
package com.idealorb.tiltfx.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CryptoCurrency {

    @SerializedName("BTC")
    @Expose
    private BTC bTC;

    /**
     * No args constructor for use in serialization
     */
    public CryptoCurrency() {
    }

    /**
     * @param bTC
     */
    public CryptoCurrency(BTC bTC) {
        super();
        this.bTC = bTC;
    }

    public BTC getBTC() {
        return bTC;
    }

    public void setBTC(BTC bTC) {
        this.bTC = bTC;
    }

}
