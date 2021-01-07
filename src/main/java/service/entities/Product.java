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
 * @version $Id: Product.java, v 0.1 20210106 16.38 faisalrahman Exp $$
 */

@Entity
@Table(name = "product")
@Converter(name="uuidConverter", converterClass= UUIDConverter.class)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @Convert("uuidConverter")
    private UUID id;

    @Column(name="tx")
    private int tx;

    @Column(name = "valid_time")
    private Date validTime;

    @Column(name="deleted")
    private boolean deleted;

    @Column(name="category", nullable = true)
    @Convert("uuidConverter")
    private UUID category;

    @Column(name="quantity")
    private int quantity;

    public Product(){

    }

    public Product(UUID id, int tx, Date validTime, boolean deleted, UUID category, int quantity){

        this.id = id;
        this.tx = tx;
        this.validTime = validTime;
        this.deleted = deleted;
        this.category = category;
        this.quantity = quantity;
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

    public UUID getCategory() { return category; }

    public void setCategory(UUID category) { this.category = category; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Product{id=%s}", id.toString());
    }
}