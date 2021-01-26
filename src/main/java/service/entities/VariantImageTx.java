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
 * @version $Id: VariantImageTx.java, v 0.1 20210126 08.57 faisalrahman Exp $$
 */
@Entity
@Table(name = "variant_image_tx")
@Converter(name="uuidConverter", converterClass= UUIDConverter.class)
public class VariantImageTx implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @Convert("uuidConverter")
    private UUID id;

    @Column(name="tx")
    private int tx;

    @Column(name = "created_time")
    private Date createdTime;

    public VariantImageTx(){

    }

    public VariantImageTx(UUID id, int tx, Date createdTime){

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
        VariantImageTx variantImageTx = (VariantImageTx) o;
        return Objects.equals(id, variantImageTx.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("VariantImageTx{id=%s}", id.toString());
    }
}