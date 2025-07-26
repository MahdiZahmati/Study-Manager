package com.example.demo.service;

import com.example.demo.DTO.DepartmentAdminDTO;
import com.example.demo.DTO.DepartmentPublicDTO;
import com.example.demo.model.Course;
import com.example.demo.model.Department;
import com.example.demo.model.Professor;
import com.example.demo.model.Student;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService extends GenericService<Department, Long>{

    private final DepartmentRepository departmentRepository;
    private final StudentRepository studentRepository;

    public DepartmentService(DepartmentRepository departmentRepository, StudentRepository studentRepository) {
        super(departmentRepository);
        this.departmentRepository = departmentRepository;
        this.studentRepository = studentRepository;
    }

    public Optional<Department> findByName(String name) {
        return departmentRepository.findByName(name);
    }

    private DepartmentAdminDTO mapToAdminDTO(Department department) {
        Long id = department.getId();
        String name = department.getName();
        Professor manager = department.getManager();
        List<Professor> professorList = department.getProfessorList();
        List<Course> courseList = department.getCourseList();
        List<Student> studentList = department.getStudentList();

        return new DepartmentAdminDTO(id, name, manager, professorList, courseList, studentList);
    }

    private DepartmentPublicDTO mapToPublicDTO(Department department) {
        String name = department.getName();
        Professor manager = department.getManager();
        List<String> courseNames = department.getCourseList().stream()
                .map(Course::getTitle)
                .collect(Collectors.toList());

        return new DepartmentPublicDTO(name , manager, courseNames);
    }

    @Transactional(readOnly = true)
    public List<DepartmentAdminDTO> getAllDepartmentAdminDTO() {
        return findAll().stream().map(this::mapToAdminDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DepartmentPublicDTO> getAllDepartmentPublicDTO() {
        return findAll().stream().map(this::mapToPublicDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DepartmentAdminDTO getDepartmentAdminDTO(String name) {
        Department department = departmentRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        return mapToAdminDTO(department);
    }

    @Transactional(readOnly = true)
    public DepartmentPublicDTO getDepartmentPublicDTO(String name) {
        Department department = departmentRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        return mapToPublicDTO(department);
    }

    public Double getAverageForDepartment(String name) {
        Department department = departmentRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        List<Student> students = department.getStudentList();

        List<Double> ave = students.stream()
                .map(Student::getAverage)
                .toList();

        if (ave.isEmpty()) {
            return 0.0;
        }

        double average = ave.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

        return average;
    }


    public Department updateDepartment(Long id, Department updatedDepartment) {
        return departmentRepository.findById(id).map(department -> {
            department.setManager(updatedDepartment.getManager());
            department.setName(updatedDepartment.getName());
            return departmentRepository.save(department);
        }).orElseThrow(() -> new RuntimeException("Department not found"));
    }

}
