package com.example.demo.DTO;

import java.util.List;

public class CoursePublicDTO {
    private String title;
    private int unit;
    private String departmentsName;
    private List<String> professorsNames;

    public CoursePublicDTO() {}

    public CoursePublicDTO(String title, int unit, String departmentsName,
                           List<String> professorsNames) {
        this.title = title;
        this.unit = unit;
        this.departmentsName = departmentsName;
        this.professorsNames = professorsNames;
    }

    // Getters & Setters

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

    public String getDepartmentsName() {
        return departmentsName;
    }

    public void setDepartmentsNames(String departmentsName) {
        this.departmentsName = departmentsName;
    }

    public List<String> getProfessorsNames() {
        return professorsNames;
    }

    public void setProfessorsNames(List<String> professorsNames) {
        this.professorsNames = professorsNames;
    }

    //to string
    @Override
    public String toString() {
        return "CoursePublicDTO{" +
                "title='" + title + '\'' +
                ", unit=" + unit +
                ", departmentsName=" + departmentsName +
                ", professorsNames=" + professorsNames +
                '}';
    }

}
