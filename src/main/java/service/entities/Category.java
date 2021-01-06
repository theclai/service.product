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
 * @version $Id: Category.java, v 0.1 20210104 11.36 faisalrahman Exp $$
 */

@Entity
@Table(name = "category")
@Converter(name="uuidConverter", converterClass= UUIDConverter.class)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String Category_find_by_id = "Category.findById";

    public static final String Category_find_by_id_query =
            "select * from category_tx \n" +
            "join log using(tx)\n" +
            "join category using(id, tx)\n" +
            "where node_tx.id = 'MY_CATEGORY_UUID'\n" +
            "and deleted = false";

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

    @Column(name="description")
    private String description;

    @Column(name="parent", nullable = true)
    @Convert("uuidConverter")
    private UUID parent;

    public Category(){

    }

    public Category(UUID id, int tx, Date validTime, boolean deleted, String title, String subtitle, String description, UUID parent){

        this.id = id;
        this.tx = tx;
        this.validTime = validTime;
        this.deleted = deleted;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.parent = parent;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getParent() {
        return parent;
    }

    public void setParent(UUID parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Category{id=%s}", id.toString());
    }
}