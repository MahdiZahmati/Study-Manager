package com.example.demo.model;

import com.example.demo.DTO.CoursePublicDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20 ,nullable = false)
    private String title;

    @Min(value = 1)
    @Max(value = 4)
    @Column(nullable = false)
    private int unit;

    @ManyToMany(mappedBy = "courseList", fetch = FetchType.LAZY)
    private List<Professor> professorList = new ArrayList<>();

    @ManyToMany(mappedBy = "courseList", fetch = FetchType.LAZY)
    private List<Student> studentList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
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


    public List<Professor> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(List<Professor> professorList) {
        this.professorList = professorList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    //to string

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
