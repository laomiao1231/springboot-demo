package com.m.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 3044507290000431734L;
    private Integer Id;

    private Integer age;

    private String cupSize;
}
