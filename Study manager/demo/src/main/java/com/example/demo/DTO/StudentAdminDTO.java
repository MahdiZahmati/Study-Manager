package com.example.demo.DTO;

import com.example.demo.model.Course;
import com.example.demo.model.Department;
import com.example.demo.model.Professor;

import java.util.List;

public class StudentAdminDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private long nationalId;
    private String phoneNumber;
    private String address;
    private String city;
    private double average;
    private String studentCode;
    private Department department;
    private List<Course> courseList;
    private List<Professor> professorList;

    public StudentAdminDTO() {}

    public StudentAdminDTO(Long id, String firstName, String lastName, String email, long nationalId,
                           String phoneNumber, String address, String city, double average, String studentCode,
                           Department department, List<Course> courseList, List<Professor> professorList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nationalId = nationalId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.average = average;
        this.studentCode = studentCode;
        this.department = department;
        this.courseList = courseList;
        this.professorList = professorList;
    }

    //getters & setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "StudentAdminDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", nationalId=" + nationalId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", average=" + average +
                ", studentCode='" + studentCode + '\'' +
                ", department=" + department +
                ", courseList=" + courseList +
                ", professorList=" + professorList +
                '}';
    }

}
