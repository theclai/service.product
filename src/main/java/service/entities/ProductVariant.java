/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import org.eclipse.persistence.annotations.ConversionValue;
import org.eclipse.persistence.annotations.Converter;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.ObjectTypeConverter;
import service.util.UUIDConverter;

/**
 * @author faisalrahman
 * @version $Id: ProductVariant.java, v 0.1 20210106 18.20 faisalrahman Exp $$
 */

@Entity
@Table(name = "product_variant")
@Converter(name="uuidConverter", converterClass= UUIDConverter.class)
public class ProductVariant implements Serializable {

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

    @Column(name="title")
    private String title;

    @Column(name="subtitle")
    private String subtitle;

    @Column(name="sku")
    private String sku;

    @Column(name="description")
    private String description;

    @Column(name="product", nullable = true)
    @Convert("uuidConverter")
    private UUID product;

    @Column(name="quantity")
    private int quantity;

    @Column(name="price")
    @Embedded
    private ProductMoney price;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="form")
    private Form productVariantForm;

    @Column(name = "width")
    private int width;

    @Column(name = "length")
    private int length;

    @Column(name = "height")
    private int height;

    @Column(name = "weight")
    private int weight;

    public ProductVariant(){

    }

    public ProductVariant(
            UUID id,
            int tx,
            Date validTime,
            boolean deleted,
            String title,
            String subtitle,
            String sku,
            String description,
            UUID product,
            int quantity,
            ProductMoney price,
            Form productVariantForm,
            int width,
            int length,
            int height,
            int weight) {

        this.id = id;
        this.tx = tx;
        this.validTime = validTime;
        this.deleted = deleted;
        this.title = title;
        this.subtitle = subtitle;
        this.sku = sku;
        this.description = description;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.productVariantForm = productVariantForm;
        this.width = width;
        this.length = length;
        this.height = height;
        this.weight = weight;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getProduct() {
        return product;
    }

    public void setProduct(UUID product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductMoney getPrice() {
        return price;
    }

    public void setPrice(ProductMoney price) {
        this.price = price;
    }

    public Form getProductVariantForm() {
        return productVariantForm;
    }

    public void setProductVariantForm(Form productVariantForm) {
        this.productVariantForm = productVariantForm;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public enum Form {
        digital, physical, billing, lending;
    }

}

