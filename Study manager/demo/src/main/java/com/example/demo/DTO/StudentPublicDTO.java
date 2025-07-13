package com.example.demo.DTO;

public class StudentPublicDTO {
    private String firstName;
    private String lastName;
    private String email;
    private double average;
    private String departmentName;

    public StudentPublicDTO() {}

    public StudentPublicDTO(String firstName, String lastName, String email, double average, String departmentName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.average = average;
        this.departmentName = departmentName;
    }

    //setters & getters

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

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    //to string

    @Override
    public String toString() {
        return "StudentPublicDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", average=" + average +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }

}
