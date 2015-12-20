package com.pams.entities;
import javax.persistence.*;
/**
 * Created by MattBrown on 12/19/15.
 */
@Entity
@Table(name = "bags")
public class Bag {
    @Id
    @GeneratedValue
    public int id;

    @Column(nullable = false)
    public String maker;

    @Column(nullable = false)
    public String stand;

    @Column(nullable = false)
    public String harness;

    @Column(nullable = false)
    public String teamName;

    @Column(nullable = false)
    public String schoolName;

    @Column(nullable = false)
    public String price;

    public String time;
}
