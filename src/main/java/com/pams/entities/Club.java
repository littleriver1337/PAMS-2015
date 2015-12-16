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

    @Column(nullable=false)
    public boolean isAuthentic;

    public Club() {
    }

    public Club(int serialNumber, String maker, String clubType, int year, String color, boolean isAuthentic) {
        this.serialNumber = serialNumber;
        this.maker = maker;
        this.clubType = clubType;
        this.year = year;
        this.color = color;
        this.isAuthentic = isAuthentic;
    }

    @ManyToOne
    public User user;
}

