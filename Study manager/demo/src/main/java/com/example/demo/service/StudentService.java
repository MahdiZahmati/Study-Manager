package com.example.demo.service;

import com.example.demo.DTO.ProfessorPublicDTO;
import com.example.demo.DTO.StudentAdminDTO;
import com.example.demo.DTO.StudentPublicDTO;
import com.example.demo.model.Course;
import com.example.demo.model.Department;
import com.example.demo.model.Professor;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService extends GenericService<Student, Long> {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        super(studentRepository);
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Optional<Student> findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    private StudentAdminDTO mapToAdminDTO(Student student) {

        long id = student.getId();
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        String email = student.getEmail();
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
                average, studentCode, department, courseList, professorList);
    }

    private StudentPublicDTO mapToPublicDTO(Student student) {

        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        String email = student.getEmail();
        double average = student.getAverage();
        String departmentName = student.getDepartment().getName();

        return new StudentPublicDTO(firstName, lastName, email, average, departmentName);
    }

    @Transactional(readOnly = true)
    public List<StudentAdminDTO> getAllStudentAdminDTOs() {
        return findAll().stream().map(this::mapToAdminDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<StudentPublicDTO> getAllStudentPublicDTOs() {
        return findAll().stream().map(this::mapToPublicDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public StudentAdminDTO getStudentAdminDTO(String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        return mapToAdminDTO(student);
    }

    @Transactional(readOnly = true)
    public StudentPublicDTO getStudentPublicDTO(String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        return mapToPublicDTO(student);
    }

    @Transactional
    public void enroll(String studentUsername, String title) {
        Student student = studentRepository.findByUsername(studentUsername)
                .orElseThrow(() -> new RuntimeException("student not found"));
        Course course = courseRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("course not found"));

        student.getCourseList().add(course);
        course.getStudentList().add(student);

        studentRepository.save(student);
        courseRepository.save(course);
    }

    public double getStudentAve(String studentUsername) {
        Student student = studentRepository.findByUsername(studentUsername)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return student.getAverage();
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

    //methods

}
