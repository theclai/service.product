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
 * @version $Id: VariantImage.java, v 0.1 20210126 08.57 faisalrahman Exp $$
 */

@Entity
@Table(name = "variant_image")
@Converter(name="uuidConverter", converterClass= UUIDConverter.class)
public class VariantImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @Convert("uuidConverter")
    private UUID id;

    @Id
    @Column(name="tx")
    private int tx;

    @Column(name = "valid_time")
    private Date validTime;

    @Column(name="deleted")
    private boolean deleted;

    @Column(name="variant", nullable = true)
    @Convert("uuidConverter")
    private UUID variant;

    @Column(name = "order_weight")
    private int orderWeight;

    public VariantImage(){

    }

    public VariantImage( UUID id, int tx, Date validTime, boolean deleted, UUID variant, int orderWeight){

        this.id = id;
        this.tx = tx;
        this.validTime = validTime;
        this.deleted = deleted;
        this.variant = variant;
        this.orderWeight = orderWeight;
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

    public Date getValidTime() {
        return validTime;
    }

    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public UUID getVariant() {
        return variant;
    }

    public void setVariant(UUID variant) {
        this.variant = variant;
    }

    public int getOrderWeight() {
        return orderWeight;
    }

    public void setOrderWeight(int orderWeight) {
        this.orderWeight = orderWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariantImage variantImage = (VariantImage) o;
        return Objects.equals(id, variantImage.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("VariantImage{id=%s}", id.toString());
    }
}