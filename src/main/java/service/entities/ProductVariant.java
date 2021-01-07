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
import org.eclipse.persistence.annotations.Converter;
import org.eclipse.persistence.annotations.Convert;
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
    private double price;

    @Column(name="form")
    private Form form;

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
            double price,
            Form form) {

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
        this.form = form;

    }

    public enum Form {
        digital, physical, billing, lending;
    }
}