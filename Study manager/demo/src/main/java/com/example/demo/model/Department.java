package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String manager;

    @OneToMany(mappedBy = "department" , cascade = CascadeType.ALL)
    private List<Professor> professorList = new ArrayList<>();

    @OneToMany(mappedBy = "department" , cascade = CascadeType.ALL)
    private List<Course> courseList = new ArrayList<>();

    @OneToMany(mappedBy = "department" , cascade = CascadeType.ALL)
    private List<Student> studentList = new ArrayList<>();


    //constructors
    public Department(String name, String manager) {
        this.name = name;
        this.manager = manager;
    }

    //getters & setters
    public Department() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    //Relations Setters & Getters
}
