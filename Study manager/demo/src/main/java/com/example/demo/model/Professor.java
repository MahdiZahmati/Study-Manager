package com.example.demo.model;

import com.example.demo.enums.Role;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "professor")
public class Professor extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "professor_course",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courseList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany(mappedBy = "professorList", fetch = FetchType.LAZY)
    private List<Student> studentList = new ArrayList<>();

    //constructors
    public Professor() {}

    public Professor(String username, String password, Role role, String firstName, String lastName, String email,
                     long nationalId, String phoneNumber, String address, String city, List<Course> courseList,
                     Department department, List<Student> studentList) {
        super(username, password, role, firstName, lastName, email, nationalId, phoneNumber, address, city);
        this.courseList = courseList;
        this.department = department;
        this.studentList = studentList;
    }

    //setters & getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //Relations Setters & Getters

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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
        return "Professor{" +
                "id=" + id +
                ", courseList=" + courseList +
                ", department=" + department +
                ", studentList=" + studentList +
                '}';
    }
}
