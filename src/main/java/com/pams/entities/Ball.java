package com.pams.entities;
import javax.persistence.*;
/**
 * Created by MattBrown on 12/19/15.
 */
@Entity
@Table(name = "balls")
public class Ball {
    @Id
    @GeneratedValue
    public int id;

    @Column(nullable = false)
    public String maker;

    @Column(nullable = false)
    public String coating;

    @Column(nullable = false)
    public String layers;

    @Column(nullable = false)
    public String boxCount;

    @Column(nullable = false)
    public String price;

    public String time;
}
