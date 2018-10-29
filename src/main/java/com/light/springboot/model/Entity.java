package com.light.springboot.model;

import java.io.Serializable;
import java.util.Date;

/**
 * A base class for persistent objects.
 */
public abstract class Entity<I extends Serializable> extends BasePO<I> implements Identifiable<I>{

    private long createdDateEpoch = System.currentTimeMillis();
    private long modifiedDateEpoch = System.currentTimeMillis();

    public Entity() {}

    public long getCreatedDateEpoch() {
        return createdDateEpoch;
    }

    public void setCreatedDateEpoch(long createdDateEpoch) {
        this.createdDateEpoch = createdDateEpoch;
    }

    public long getModifiedDateEpoch() {
        return modifiedDateEpoch;
    }

    public void setModifiedDateEpoch(long modifiedDateEpoch) {
        this.modifiedDateEpoch = modifiedDateEpoch;
    }

    /**
     * @deprecated use getCreatedDateEpoch instead.
     */
    @Override
    @Deprecated
    public Date getCreatedDate() {
        return new Date(createdDateEpoch);
    }

    /**
     * @deprecated use setCreatedDateEpoch instead.
     */
    @Override
    @Deprecated
    public void setCreatedDate(Date createdDate) {
        createdDateEpoch = createdDate.getTime();
    }

    /**
     * @deprecated use getModifiedDateEpoch instead.
     */
    @Override
    @Deprecated
    public Date getModifiedDate() {
        return new Date(modifiedDateEpoch);
    }

    /**
     * @deprecated use setModifiedDateEpoch instead.
     */
    @Override
    @Deprecated
    public void setModifiedDate(Date modifiedDate) {
        modifiedDateEpoch = modifiedDate.getTime();
    }
}
