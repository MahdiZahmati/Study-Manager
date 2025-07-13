package com.example.demo.service;

import com.example.demo.DTO.DepartmentAdminDTO;
import com.example.demo.DTO.DepartmentPublicDTO;
import com.example.demo.model.Course;
import com.example.demo.model.Department;
import com.example.demo.model.Professor;
import com.example.demo.model.Student;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService extends GenericService<Department, Long>{

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        super(departmentRepository);
        this.departmentRepository = departmentRepository;
    }

    public Optional<Department> findByName(String name) {
        return departmentRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public String getDepartmentAdminDTO(String name) {
        Department department = departmentRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Long id = department.getId();
        String manager = department.getManager();
        List<Professor> professorList = department.getProfessorList();
        List<Course> courseList = department.getCourseList();
        List<Student> studentList = department.getStudentList();

        return new DepartmentAdminDTO(id, name, manager, professorList, courseList, studentList).toString();
    }

    @Transactional(readOnly = true)
    public String getDepartmentPublicDTO(String name) {
        Department department = departmentRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        String manager = department.getManager();
        List<String> courseNames = department.getCourseList().stream()
                .map(Course::getTitle)
                .collect(Collectors.toList());

        return new DepartmentPublicDTO(name , manager, courseNames).toString();
    }

    public Department updateDepartment(Long id, Department updatedDepartment) {
        return departmentRepository.findById(id).map(department -> {
            department.setManager(updatedDepartment.getManager());
            department.setName(updatedDepartment.getName());
            return departmentRepository.save(department);
        }).orElseThrow(() -> new RuntimeException("Department not found"));
    }

}
