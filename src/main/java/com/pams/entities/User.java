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
    public int id;

    @Column(nullable = false)
    public String username;

    @Column(nullable = false)
    public String password;

    @Column
    public String companyName;

    @Column
    public String address;

    @Column
    public String city;

    @Column public String state;

    @Column
    public int zip;

    @Column
    public String email;

    @Column(nullable = false)
    public AccessLevel accessLevel;

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZip() {
        return zip;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public String getEmail() {
        return email;
    }

}
