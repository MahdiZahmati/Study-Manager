package com.example.demo.DTO;

import com.example.demo.model.Professor;
import com.example.demo.model.Student;

import java.util.List;

public class CourseAdminDTO {
    private Long id;
    private String title;
    private int unit;
    private String departmentsName;
    private List<Student> students;
    private List<Professor> professors;

    public CourseAdminDTO() {}

    public CourseAdminDTO(Long id, String title, int unit, String departmentsName,
                          List<Student> students, List<Professor> professors) {
        this.id = id;
        this.title = title;
        this.unit = unit;
        this.departmentsName = departmentsName;
        this.students = students;
        this.professors = professors;
    }

    // Getters & Setters

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

    public String getDepartmentsName() {
        return departmentsName;
    }

    public void setDepartmentsName(String departmentsName) {
        this.departmentsName = departmentsName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }

    //to string

    @Override
    public String toString() {
        return "CourseAdminDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", unit=" + unit +
                ", departmentsName='" + departmentsName + '\'' +
                ", students=" + students +
                ", professors=" + professors +
                '}';
    }

}
