package com.pams.entities;
import javax.persistence.*;


/**
 * Created by MattBrown on 12/19/15.
 */
@Entity
@Table(name = "hats")
public class Hat {
    @Id
    @GeneratedValue
    public int id;

    @Column(nullable = false)
    public String maker;

    @Column(nullable = false)
    public String fit;

    @Column(nullable = false)
    public String color;

    @Column(nullable = false)
    public String price;

    public String time;

    @ManyToOne
    public User user;
}
