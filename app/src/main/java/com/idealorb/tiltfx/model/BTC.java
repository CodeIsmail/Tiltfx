
package com.idealorb.tiltfx.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BTC {

    @SerializedName("GBP")
    @Expose
    private Double gBP;
    @SerializedName("USD")
    @Expose
    private Double uSD;
    @SerializedName("EUR")
    @Expose
    private Double eUR;
    @SerializedName("NGN")
    @Expose
    private Double nGN;
    @SerializedName("JPY")
    @Expose
    private Double jPY;
    @SerializedName("CHF")
    @Expose
    private Double cHF;
    @SerializedName("CAD")
    @Expose
    private Double cAD;
    @SerializedName("INR")
    @Expose
    private Double iNR;
    @SerializedName("RUB")
    @Expose
    private Double rUB;
    @SerializedName("ZAR")
    @Expose
    private Double zAR;
    @SerializedName("MXN")
    @Expose
    private Double mXN;
    @SerializedName("MYR")
    @Expose
    private Double mYR;
    @SerializedName("DKK")
    @Expose
    private Double dKK;
    @SerializedName("SGD")
    @Expose
    private Double sGD;
    @SerializedName("SAR")
    @Expose
    private Double sAR;
    @SerializedName("AED")
    @Expose
    private Double aED;
    @SerializedName("KRW")
    @Expose
    private Double kRW;
    @SerializedName("TRY")
    @Expose
    private Double tRY;
    @SerializedName("NOK")
    @Expose
    private Double nOK;
    @SerializedName("SEK")
    @Expose
    private Double sEK;

    /**
     * No args constructor for use in serialization
     */
    public BTC() {
    }

    /**
     * @param kRW
     * @param uSD
     * @param tRY
     * @param cHF
     * @param eUR
     * @param iNR
     * @param rUB
     * @param sAR
     * @param nOK
     * @param dKK
     * @param zAR
     * @param mYR
     * @param sGD
     * @param mXN
     * @param aED
     * @param sEK
     * @param gBP
     * @param nGN
     * @param jPY
     * @param cAD
     */
    public BTC(Double gBP, Double uSD, Double eUR, Double nGN, Double jPY, Double cHF, Double cAD, Double iNR, Double rUB, Double zAR, Double mXN, Double mYR, Double dKK, Double sGD, Double sAR, Double aED, Double kRW, Double tRY, Double nOK, Double sEK) {
        super();
        this.gBP = gBP;
        this.uSD = uSD;
        this.eUR = eUR;
        this.nGN = nGN;
        this.jPY = jPY;
        this.cHF = cHF;
        this.cAD = cAD;
        this.iNR = iNR;
        this.rUB = rUB;
        this.zAR = zAR;
        this.mXN = mXN;
        this.mYR = mYR;
        this.dKK = dKK;
        this.sGD = sGD;
        this.sAR = sAR;
        this.aED = aED;
        this.kRW = kRW;
        this.tRY = tRY;
        this.nOK = nOK;
        this.sEK = sEK;
    }

    public Double getGBP() {
        return gBP;
    }

    public void setGBP(Double gBP) {
        this.gBP = gBP;
    }

    public Double getUSD() {
        return uSD;
    }

    public void setUSD(Double uSD) {
        this.uSD = uSD;
    }

    public Double getEUR() {
        return eUR;
    }

    public void setEUR(Double eUR) {
        this.eUR = eUR;
    }

    public Double getNGN() {
        return nGN;
    }

    public void setNGN(Double nGN) {
        this.nGN = nGN;
    }

    public Double getJPY() {
        return jPY;
    }

    public void setJPY(Double jPY) {
        this.jPY = jPY;
    }

    public Double getCHF() {
        return cHF;
    }

    public void setCHF(Double cHF) {
        this.cHF = cHF;
    }

    public Double getCAD() {
        return cAD;
    }

    public void setCAD(Double cAD) {
        this.cAD = cAD;
    }

    public Double getINR() {
        return iNR;
    }

    public void setINR(Double iNR) {
        this.iNR = iNR;
    }

    public Double getRUB() {
        return rUB;
    }

    public void setRUB(Double rUB) {
        this.rUB = rUB;
    }

    public Double getZAR() {
        return zAR;
    }

    public void setZAR(Double zAR) {
        this.zAR = zAR;
    }

    public Double getMXN() {
        return mXN;
    }

    public void setMXN(Double mXN) {
        this.mXN = mXN;
    }

    public Double getMYR() {
        return mYR;
    }

    public void setMYR(Double mYR) {
        this.mYR = mYR;
    }

    public Double getDKK() {
        return dKK;
    }

    public void setDKK(Double dKK) {
        this.dKK = dKK;
    }

    public Double getSGD() {
        return sGD;
    }

    public void setSGD(Double sGD) {
        this.sGD = sGD;
    }

    public Double getSAR() {
        return sAR;
    }

    public void setSAR(Double sAR) {
        this.sAR = sAR;
    }

    public Double getAED() {
        return aED;
    }

    public void setAED(Double aED) {
        this.aED = aED;
    }

    public Double getKRW() {
        return kRW;
    }

    public void setKRW(Double kRW) {
        this.kRW = kRW;
    }

    public Double getTRY() {
        return tRY;
    }

    public void setTRY(Double tRY) {
        this.tRY = tRY;
    }

    public Double getNOK() {
        return nOK;
    }

    public void setNOK(Double nOK) {
        this.nOK = nOK;
    }

    public Double getSEK() {
        return sEK;
    }

    public void setSEK(Double sEK) {
        this.sEK = sEK;
    }


    public List<Double> getArrayList() {
        List<Double> exchangerate = new ArrayList<>();

        exchangerate.add(getGBP());
        exchangerate.add(getUSD());
        exchangerate.add(getEUR());
        exchangerate.add(getNGN());
        exchangerate.add(getJPY());
        exchangerate.add(getCHF());
        exchangerate.add(getCAD());
        exchangerate.add(getINR());
        exchangerate.add(getRUB());
        exchangerate.add(getZAR());
        exchangerate.add(getMXN());
        exchangerate.add(getMYR());
        exchangerate.add(getDKK());
        exchangerate.add(getSGD());
        exchangerate.add(getSAR());
        exchangerate.add(getAED());
        exchangerate.add(getKRW());
        exchangerate.add(getTRY());
        exchangerate.add(getNOK());
        exchangerate.add(getSEK());

        return exchangerate;
    }
}



