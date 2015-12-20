package com.pams.entities;
import javax.persistence.*;
/**
 * Created by MattBrown on 12/20/15.
 */
@Entity
@Table(name = "umbrellas")
public class Umbrella {
    @Id
    @GeneratedValue
    public int id;

    @Column(nullable = false)
    public String maker;

    @Column(nullable = false)
    public String color;

    @Column(nullable = false)
    public String collection;

    @Column(nullable = false)
    public String umbrellaStyle;

    @Column(nullable = false)
    public String pattern;

    @Column(nullable = false)
    public String price;

    public String time;

    @ManyToOne
    public User user;

}
