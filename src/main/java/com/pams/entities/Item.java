package com.pams.entities;

import javax.persistence.*;

/**
 * Created by MattBrown on 12/8/15.
 */
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue
    public int id;


    @Column(nullable = false)
    public String fileName;

    @Column(nullable = false)
    public String serialNumber;

    @Column(nullable = false)
    public String productModel;

    @Column(nullable = false)
    public String companyUser;

    //many items to one user
}

