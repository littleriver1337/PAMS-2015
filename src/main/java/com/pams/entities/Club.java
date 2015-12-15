package com.pams.entities;

import javax.persistence.*;

/**
 * Created by MattBrown on 12/8/15.
 */
@Entity
@Table(name = "clubs")
public class Club {
    @Id
    @GeneratedValue
    public int id;

    public String fileName;

    @Column(nullable = false)
    public int serialNumber;

    @Column(nullable = false)
    public String maker;

    @Column(nullable = false)
    public String clubType;

    @Column(nullable = false)
    public int year;

    @Column(nullable = false)
    public String color;

    @ManyToOne
    public User user;
}

