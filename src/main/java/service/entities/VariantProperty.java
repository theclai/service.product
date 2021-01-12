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
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @author faisalrahman
 * @version $Id: VariantProperty.java, v 0.1 20210112 11.34 faisalrahman Exp $$
 */

@Entity
@Table(name = "variant_property")
@Converter(name="uuidConverter", converterClass= UUIDConverter.class)
public class VariantProperty {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "variant")
    @Convert("uuidConverter")
    private UUID variant;

    @Column(name = "id")
    private String id;

    @Column(name = "tx")
    private int tx;

    @Column(name = "valid_time")
    private Date validTime;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;

    public VariantProperty() {

    }

    public VariantProperty(UUID variant, String id, int tx, Date validTime, boolean deleted, String key, String value) {

        this.variant = variant;
        this.id = id;
        this.tx = tx;
        this.validTime = validTime;
        this.deleted = deleted;
        this.key = key;
        this.value = value;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariantProperty variantProperty = (VariantProperty) o;
        return Objects.equals(variant, variantProperty.variant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variant);
    }

    @Override
    public String toString() {
        return String.format("VariantProperty{id=%s}", variant.toString());
    }
}