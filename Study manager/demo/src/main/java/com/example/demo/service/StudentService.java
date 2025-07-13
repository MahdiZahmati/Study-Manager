package com.example.demo.service;

import com.example.demo.DTO.ProfessorPublicDTO;
import com.example.demo.DTO.StudentAdminDTO;
import com.example.demo.DTO.StudentPublicDTO;
import com.example.demo.model.Course;
import com.example.demo.model.Department;
import com.example.demo.model.Professor;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService extends GenericService<Student, Long> {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        super(studentRepository);
        this.studentRepository = studentRepository;
    }

    public Optional<Student> findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public String getStudentAdminDTO(String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        long id = student.getId();
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        long nationalId = student.getNationalId();
        String phoneNumber = student.getPhoneNumber();
        String address = student.getAddress();
        String city = student.getCity();
        double average = student.getAverage();
        String studentCode = student.getStudentCode();
        Department department = student.getDepartment();
        List<Course> courseList = student.getCourseList();
        List<Professor> professorList = student.getProfessorList();

        return new StudentAdminDTO(id, firstName, lastName, email, nationalId, phoneNumber, address, city,
                average, studentCode, department, courseList, professorList).toString();
    }
    @Transactional(readOnly = true)
    public String getStudentPublicDTO(String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        double average = student.getAverage();
        String departmentName = student.getDepartment().getName();

        return new StudentPublicDTO(firstName, lastName, email, average, departmentName).toString();
    }

    public Student updateStudent(Long id ,Student updatedStudent) {
        return studentRepository.findById(id).map(student -> {
            student.setStudentCode(updatedStudent.getStudentCode());
            student.setFirstName(updatedStudent.getFirstName());
            student.setLastName(updatedStudent.getLastName());
            student.setEmail(updatedStudent.getEmail());
            student.setNationalId(updatedStudent.getNationalId());
            student.setPhoneNumber(updatedStudent.getPhoneNumber());
            student.setAddress(updatedStudent.getAddress());
            student.setCity(updatedStudent.getCity());
            student.setAverage(updatedStudent.getAverage());
            return studentRepository.save(student);
        }).orElseThrow(() -> new RuntimeException("Student not found"));
    }

}
