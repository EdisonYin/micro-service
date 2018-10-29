package com.light.springboot.model;

import java.io.Serializable;
import java.util.Date;

public abstract class BasePO<I extends Serializable> implements Identifiable<I> {

    private I id;
    private Date createdDate = new Date();
    private Date modifiedDate = new Date();

    public BasePO() {}

    public BasePO(I id) {
        this.id = id;
    }

    @Override
    public I getId() {
        return id;
    }

    @Override
    public void setId(I id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
