package com.example.demo.service;

import com.example.demo.DTO.ProfessorAdminDTO;
import com.example.demo.DTO.ProfessorPublicDTO;
import com.example.demo.model.Course;
import com.example.demo.model.Department;
import com.example.demo.model.Professor;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.ProfessorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService extends GenericService<Professor, Long> {

    private final ProfessorRepository professorRepository;
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;

    public ProfessorService(ProfessorRepository professorRepository, CourseRepository courseRepository, DepartmentRepository departmentRepository) {
        super(professorRepository);
        this.professorRepository = professorRepository;
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
    }

    public Optional<Professor> findByEmail(String email) {
        return professorRepository.findByEmail(email);
    }

    private ProfessorPublicDTO mapToPublicDTO(Professor professor) {
        String professorFirsName= professor.getFirstName();
        String professorLastName= professor.getLastName();
        String email= professor.getEmail();
        String department = professor.getDepartment().getName();

        return new ProfessorPublicDTO(professorFirsName, professorLastName, email, department);
    }

    private ProfessorAdminDTO mapToAdminDTO(Professor professor) {
        String professorFirsName= professor.getFirstName();
        String professorLastName= professor.getLastName();
        String email= professor.getEmail();
        long nationalId = professor.getNationalId();
        String phoneNumber = professor.getPhoneNumber();
        String address = professor.getAddress();
        String city = professor.getCity();
        List<Course> courseList = professor.getCourseList();
        Department department = professor.getDepartment();
        List<Student> studentList = professor.getStudentList();

        return new ProfessorAdminDTO(professorFirsName, professorLastName, email, nationalId, phoneNumber,
                address, city, courseList, department, studentList);
    }

    @Transactional(readOnly = true)
    public List<ProfessorAdminDTO> getAllProfessorAdminDTO() {
        return findAll().stream().map(this::mapToAdminDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProfessorPublicDTO> getAllProfessorPublicDTO() {
        return findAll().stream().map(this::mapToPublicDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProfessorPublicDTO getProfessorPublicDTO(String email) {
        Professor professor = professorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Professor not found"));

        return mapToPublicDTO(professor);
    }

    @Transactional(readOnly = true)
    public ProfessorAdminDTO getProfessorAdminDTO(String email) {
        Professor professor = professorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Professor not found"));

        return mapToAdminDTO(professor);
    }

    @Transactional
    public void offerCourse(String professorUsername, String courseTitle) {
        Professor professor = professorRepository.findByUsername(professorUsername)
                .orElseThrow(() -> new RuntimeException("Professor not found"));

        Course course = courseRepository.findByTitle(courseTitle)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (!course.getDepartment().getId().equals(professor.getDepartment().getId())) {
            throw new IllegalArgumentException(
                    "Course (title=" + courseTitle + ") does not belong to Professor's department"
            );
        }

        professor.getCourseList().add(course);
        course.getProfessorList().add(professor);

        professorRepository.save(professor);
        courseRepository.save(course);
    }

    @Transactional
    public Professor assignToDepartment(String professorUsername, String departmentName) {
        Professor professor = professorRepository.findByUsername(professorUsername)
                .orElseThrow(() -> new RuntimeException("Professor not found"));
        Department department = departmentRepository.findByName(departmentName)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        if (professor.getDepartment() != null) {
            throw new IllegalStateException(
                    "Professor (username=" + professorUsername + ") already assigned to Department (name="
                            + professor.getDepartment().getName() + ")"
            );
        }

        professor.setDepartment(department);
        department.getProfessorList().add(professor);

        return professorRepository.save(professor);
    }
    
    public Professor updateProfessor(Long id, Professor updatedProfessor) {
        return professorRepository.findById(id).map(professor -> {
            professor.setFirstName(updatedProfessor.getFirstName());
            professor.setLastName(updatedProfessor.getLastName());
            professor.setEmail(updatedProfessor.getEmail());
            professor.setNationalId(updatedProfessor.getNationalId());
            professor.setPhoneNumber(updatedProfessor.getPhoneNumber());
            professor.setAddress(updatedProfessor.getAddress());
            professor.setCity(updatedProfessor.getCity());
            return professorRepository.save(professor);
        }).orElseThrow(() -> new RuntimeException("Professor not found"));
    }

}
