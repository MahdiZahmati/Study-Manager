package com.example.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "professor")
public class Professor extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToMany
    @JoinTable(
            name = "professor_course",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courseList = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany(mappedBy = "professorList")
    private List<Student> studentList = new ArrayList<>();

    //constructors
    public Professor(String firstName, String lastName, String email, long nationalId,
                     String phoneNumber, String address, String city) {
        super(firstName, lastName, email, nationalId, phoneNumber, address, city);
    }
    public Professor() {}


    //setters & getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    //Relations Setters & Getters
}
