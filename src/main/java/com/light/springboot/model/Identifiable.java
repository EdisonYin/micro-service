package com.light.springboot.model;

import java.io.Serializable;

public interface Identifiable<I extends Serializable> {

    I getId();

    void setId(I id);
}
