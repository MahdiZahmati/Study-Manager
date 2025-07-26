package com.example.demo.model;

import com.example.demo.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "20.0")
    @Column(nullable = false)
    private double average;

    @Pattern(regexp = "^40\\d{6}$")
    @Column(length = 8, nullable = false)
    private String studentCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "srudent_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn (name = "course_id")
    )
    private List<Course> courseList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_professor",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private List<Professor> professorList = new ArrayList<>();

    //constructors


    public Student(String username, String password, Role role, String firstName, String lastName, String email,
                   long nationalId, String phoneNumber, String address, String city, double average, String studentCode,
                   Department department, List<Course> courseList, List<Professor> professorList) {
        super(username, password, role, firstName, lastName, email, nationalId, phoneNumber, address, city);
        this.average = average;
        this.studentCode = studentCode;
        this.department = department;
        this.courseList = courseList;
        this.professorList = professorList;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<Professor> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(List<Professor> professorList) {
        this.professorList = professorList;
    }

    //to string

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", average=" + average +
                ", studentCode='" + studentCode + '\'' +
                ", department=" + department +
                ", courseList=" + courseList +
                ", professorList=" + professorList +
                '}';
    }

}
