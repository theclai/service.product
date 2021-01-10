/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.entities;

import org.eclipse.persistence.jpa.config.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author faisalrahman
 * @version $Id: Log.java, v 0.1 20210105 09.37 faisalrahman Exp $$
 */

@Entity
@Table(name = "log")
public class Log implements Serializable {

    @Id
    @Column(name="tx")
    private int tx;

    @Column(name="transaction_time")
    private Date transactionTime;

    public Log(){

    }

    public Log(int tx, Date transactionTime){

        this.tx = tx;
        this.transactionTime = transactionTime;
    }

    public int getTx() {
        return tx;
    }

    public void setTx(int tx) {
        this.tx = tx;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return Objects.equals(tx, log.tx);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tx);
    }

    @Override
    public String toString() {

        return String.format("Log{tx=%d}", tx);
    }
}