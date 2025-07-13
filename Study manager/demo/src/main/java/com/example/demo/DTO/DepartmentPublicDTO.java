package com.example.demo.DTO;

import com.example.demo.model.Course;

import java.util.List;

public class DepartmentPublicDTO {
    private String name;
    private String manager;
    private List<String> courseNames;

    public DepartmentPublicDTO() {}

    public DepartmentPublicDTO(String name, String manager, List<String> courseNames) {
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

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
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
