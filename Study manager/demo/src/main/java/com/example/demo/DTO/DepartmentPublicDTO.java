package com.example.demo.DTO;

import com.example.demo.model.Course;
import com.example.demo.model.Professor;

import java.util.List;

public class DepartmentPublicDTO {
    private String name;
    private Professor manager;
    private List<String> courseNames;

    public DepartmentPublicDTO() {}

    public DepartmentPublicDTO(String name, Professor manager, List<String> courseNames) {
        this.name = name;
        this.manager = manager;
        this.courseNames = courseNames;
    }

    //getters & setters

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

    public List<String> getCourseList() {
        return courseNames;
    }

    public void setCourseList(List<String> courseNames) {
        this.courseNames = courseNames;
    }

    //to string

    @Override
    public String toString() {
        return "DepartmentPublicDTO{" +
                "name='" + name + '\'' +
                ", manager='" + manager + '\'' +
                ", courseNames=" + courseNames +
                '}';
    }

}
