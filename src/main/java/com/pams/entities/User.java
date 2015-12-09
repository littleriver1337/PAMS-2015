package com.pams.entities;
import javax.persistence.*;

/**
 * Created by MattBrown on 12/8/15.
 */
@Entity
@Table(name = "users")
public class User {
    public enum AccessLevel{
        ADMIN,
        COMPANY_USER,
        RETAILER_USER,
        JOE_USER
    }
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    public String username;

    @Column(nullable = false)
    public String password;

    @Column(nullable = false)
    public String companyName;

    @Column(nullable = false)
    public String address;

    @Column(nullable = false)
    public String city;

    @Column(nullable = false)
    public String state;

    @Column(nullable = false)
    public int zip;

    @Column(nullable = false)
    public AccessLevel accessLevel;

    @Column(nullable = false)
    public String email;
}
