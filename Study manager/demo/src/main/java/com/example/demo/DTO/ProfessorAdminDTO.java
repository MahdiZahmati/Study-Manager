package com.example.demo.DTO;

import com.example.demo.model.Course;
import com.example.demo.model.Department;
import com.example.demo.model.Student;

import java.util.List;

public class ProfessorAdminDTO {
    private String firstName;
    private String lastName;
    private String email;
    private long nationalId;
    private String phoneNumber;
    private String address;
    private String city;
    private List<Course> courseList;
    private Department department;
    private List<Student> studentList;

    public ProfessorAdminDTO() {}

    public ProfessorAdminDTO(String firstName, String lastName, String email, long nationalId,
                             String phoneNumber, String address, String city, List<Course> courseList,
                             Department department, List<Student> studentList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nationalId = nationalId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.courseList = courseList;
        this.department = department;
        this.studentList = studentList;
    }

    //getters & setters

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
        return "ProfessorAdminDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", nationalId=" + nationalId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", courseList=" + courseList +
                ", department='" + department + '\'' +
                ", studentList=" + studentList +
                '}';
    }

}
