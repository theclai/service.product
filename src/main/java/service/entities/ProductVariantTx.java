/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.entities;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import service.util.UUIDConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @author faisalrahman
 * @version $Id: ProductVariantTx.java, v 0.1 20210106 18.20 faisalrahman Exp $$
 */

@Entity
@Table(name = "product_variant_tx")
@Converter(name="uuidConverter", converterClass= UUIDConverter.class)
public class ProductVariantTx implements Serializable {

    @Id
    @Column(name="id")
    @Convert("uuidConverter")
    private UUID id;

    @Column(name="tx")
    private int tx;

    @Column(name = "created_time")
    private Date createdTime;

    public ProductVariantTx(){

    }

    public ProductVariantTx(UUID id, int tx, Date createdTime){

        this.id = id;
        this.tx = tx;
        this.createdTime = createdTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getTx() {
        return tx;
    }

    public void setTx(int tx) {
        this.tx = tx;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductVariantTx productVariantTx = (ProductVariantTx) o;
        return Objects.equals(id, productVariantTx.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("ProductVariantTx{id=%s}", id.toString());
    }
}