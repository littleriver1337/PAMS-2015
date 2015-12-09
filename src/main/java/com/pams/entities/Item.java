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
    int id;

    @Column(nullable = false)
    String serialNumber;

    @Column(nullable = false)
    String productModel;

    @Column(nullable = false)
    String companyUser;

    //many items to one user
}

