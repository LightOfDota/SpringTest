package com.by.test.entity;

import java.io.Serializable;

/**
 * Created by root on 2018/3/5.
 */
public class Person implements Serializable {

    private static final long serialVersionUID = -6207897737858976014L;

    private Integer id;

    private String name;

    private String address;

    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
