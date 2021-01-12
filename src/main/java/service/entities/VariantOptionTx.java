/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.entities;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import service.util.UUIDConverter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @author faisalrahman
 * @version $Id: ProductOptionTx.java, v 0.1 20210111 15.09 faisalrahman Exp $$
 */

@Entity
@Table(name = "variant_option_tx")
@Converter(name="uuidConverter", converterClass= UUIDConverter.class)
public class VariantOptionTx implements Serializable {

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

    public VariantOptionTx(){

    }

    public VariantOptionTx(UUID variant, String id, int tx, Date createdTime){

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
        VariantOptionTx variantOptionTx = (VariantOptionTx) o;
        return Objects.equals(variant, variantOptionTx.variant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variant);
    }

    @Override
    public String toString() {
        return String.format("VariantOptionTx{id=%s}", variant.toString());
    }
}