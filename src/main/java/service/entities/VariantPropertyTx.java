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
 * @version $Id: VariantPropertyTx.java, v 0.1 20210112 11.34 faisalrahman Exp $$
 */

@Entity
@Table(name = "variant_property_tx")
@Converter(name="uuidConverter", converterClass= UUIDConverter.class)
public class VariantPropertyTx implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="variant")
    @Convert("uuidConverter")
    private UUID variant;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "tx")
    private int tx;

    @Column(name = "created_time")
    private Date createdTime;

    public VariantPropertyTx(){

    }

    public VariantPropertyTx(UUID variant, String id, int tx, Date createdTime){

        this.variant = variant;
        this.id = id;
        this.tx= tx;
        this.createdTime = createdTime;
    }

    public UUID getVariant() {
        return variant;
    }

    public void setVariant(UUID variant) {
        this.variant = variant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        VariantPropertyTx variantPropertyTx = (VariantPropertyTx) o;
        return Objects.equals(variant, variantPropertyTx.variant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variant);
    }

    @Override
    public String toString() {
        return String.format("VariantPropertyTx{id=%s}", variant.toString());
    }
}