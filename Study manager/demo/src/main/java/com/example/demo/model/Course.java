package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private int unit;

    @ManyToMany(mappedBy = "courseList")
    private List<Professor> professorList = new ArrayList<>();

    @ManyToMany(mappedBy = "courseList")
    private List<Student> studentList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    //constructors
    public Course(String title, int unit) {
        this.title = title;
        this.unit = unit;
    }
    public Course() {}

    //getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    //Relations Setters & Getters

    @Override
    public String toString() {
        return "Course{" +
                ", title='" + title + '\'' +
                ", unit=" + unit +
                ", professorList=" + professorList +
                ", studentList=" + studentList +
                ", department=" + department +
                '}';
    }
}
