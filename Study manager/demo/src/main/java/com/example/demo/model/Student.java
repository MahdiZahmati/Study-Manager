package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double average;
    private String studentCode;

    public Student(String firstName, String lastName, String email, long nationalId,
            String phoneNumber, String address, String city, double average , String studentCode) {
        super(firstName, lastName, email, nationalId, phoneNumber, address, city);
        this.average = average;
        this.studentCode = studentCode;
    }

    public Student() {

    }


    //setters & getters
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }
}
