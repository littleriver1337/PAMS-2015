package com.pams.entities;
import javax.persistence.*;
/**
 * Created by MattBrown on 12/20/15.
 */
@Entity
@Table(name = "pants")
public class Pant {
    @Id
    @GeneratedValue
    public int id;

    @Column(nullable = false)
    public String maker;
    
    @Column(nullable = false)
    public String fit;

    @Column(nullable = false)
    public String pantSize;

    @Column(nullable = false)
    public String inseam;

    @Column(nullable = false)
    public String color;

    @Column(nullable = false)
    public String price;

    public String time;

    @ManyToOne
    public User user;
}
