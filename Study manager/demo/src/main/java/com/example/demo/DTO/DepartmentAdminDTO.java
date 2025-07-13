package com.example.demo.DTO;

import com.example.demo.model.Course;
import com.example.demo.model.Professor;
import com.example.demo.model.Student;

import java.util.List;

public class DepartmentAdminDTO {
    private Long id;
    private String name;
    private String manager;
    private List<Professor> professors;
    private List<Course> courseList;
    private List<Student> studentList;

    public DepartmentAdminDTO() {}

    public DepartmentAdminDTO(Long id, String name, String manager, List<Professor> professors,
                              List<Course> courseList, List<Student> studentList) {
        this.id = id;
        this.name = name;
        this.manager = manager;
        this.professors = professors;
        this.courseList = courseList;
        this.studentList = studentList;
    }

    //getters & setters

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

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
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
        return "DepartmentAdminDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manager='" + manager + '\'' +
                ", professors=" + professors +
                ", courseList=" + courseList +
                ", studentList=" + studentList +
                '}';
    }

}
