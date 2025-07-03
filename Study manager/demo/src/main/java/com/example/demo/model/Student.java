package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private double average;

    @NotNull
    private String studentCode;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "srudent_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn (name = "course_id")
    )
    private List<Course> courseList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "student_professor",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private List<Professor> professorList = new ArrayList<>();

    //constructors
    public Student(String firstName, String lastName, String email, long nationalId,
            String phoneNumber, String address, String city, double average , String studentCode) {
        super(firstName, lastName, email, nationalId, phoneNumber, address, city);
        this.average = average;
        this.studentCode = studentCode;
    }
    public Student() {}


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

    //Relations Setters & Getters
}
