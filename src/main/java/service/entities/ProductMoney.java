/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.entities;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

/**
 * @author faisalrahman
 * @version $Id: ProductMoney.java, v 0.1 20210107 17.45 faisalrahman Exp $$
 */

@Embeddable
public class ProductMoney {

    @Basic
    private String currencyCode;

    @Basic
    private long units;

    @Basic
    private int nanos;

    public ProductMoney(){

    }

    public ProductMoney(String currencyCode, long units, int nanos){

        this.currencyCode = currencyCode;
        this.units = units;
        this.nanos = nanos;
    }

    public ProductMoney(String currencyCode, long units){

        this.currencyCode = currencyCode;
        this.units = units;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public long getUnits() {
        return units;
    }

    public void setUnits(long units) {
        this.units = units;
    }

    public int getNanos() {
        return nanos;
    }

    public void setNanos(int nanos) {
        this.nanos = nanos;
    }
}