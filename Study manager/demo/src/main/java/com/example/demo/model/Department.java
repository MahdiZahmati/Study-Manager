package com.example.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Professor manager;

    @OneToMany(mappedBy = "department" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Professor> professorList = new ArrayList<>();

    @OneToMany(mappedBy = "department" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courseList = new ArrayList<>();

    @OneToMany(mappedBy = "department" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> studentList = new ArrayList<>();


    //constructors
    public Department(String name) {
        this.name = name;
    }

    public Department(Long id, String name, Professor manager) {
        this.id = id;
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

    public Professor getManager() {
        return manager;
    }

    public void setManager(Professor manager) {
        this.manager = manager;
    }

    //Relations Setters & Getters

    public List<Professor> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(List<Professor> professorList) {
        this.professorList = professorList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    //to string

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manager='" + manager + '\'' +
                ", professorList=" + professorList +
                ", courseList=" + courseList +
                ", studentList=" + studentList +
                '}';
    }
}
