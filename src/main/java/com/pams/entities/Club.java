package com.pams.entities;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    public String lieAngle;

    @Column(nullable=false)
    public boolean isAuthentic;


    public String time;

    public Club() {
    }

    public Club(int serialNumber, String maker, String clubType, int year, String lieAngle, boolean isAuthentic, String time) {
        this.serialNumber = serialNumber;
        this.maker = maker;
        this.clubType = clubType;
        this.year = year;
        this.lieAngle = lieAngle;
        this.isAuthentic = isAuthentic;
        this.time = time;
    }

    @ManyToOne
    public User user;
}

