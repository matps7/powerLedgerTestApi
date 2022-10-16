package com.powerldger.code.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Batteries
{

    @Id
    private String name;
    @Column
    private int postcode;
    @Column
    private int watt;

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getPostcode()
    {
        return postcode;
    }
    public void setPostcode(int postcode)
    {
        this.postcode = postcode;
    }
    public int getWatt()
    {
        return watt;
    }
    public void setWatt(int watt)
    {
        this.watt = watt;
    }
}

