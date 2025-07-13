package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length= 20, nullable = false)
    private String firstName;
    @Column(length = 20, nullable = false)
    private String lastName;
    @Column(length = 50, nullable = false)
    private String email;

    @Pattern(regexp = "^\\d{10}$")
    @Column(nullable = false)
    private long nationalId;

    @Pattern(regexp = "^09\\d{9}$")
    private String phoneNumber;

    @Column(length = 100)
    private String address;

    @Column(length = 20)
    private String city;


    //constructors
    public User(String firstName, String lastName, String email, long nationalId,
                String phoneNumber, String address, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nationalId = nationalId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
    }
    public User() {}


    //getters & setters
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getNationalId() {
        return nationalId;
    }

    public void setNationalId(long nationalId) {
        this.nationalId = nationalId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    //to string

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", nationalId=" + nationalId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

}
