package com.example.demo.service;

import com.example.demo.DTO.DepartmentAdminDTO;
import com.example.demo.DTO.ProfessorAdminDTO;
import com.example.demo.DTO.ProfessorPublicDTO;
import com.example.demo.model.Course;
import com.example.demo.model.Department;
import com.example.demo.model.Professor;
import com.example.demo.model.Student;
import com.example.demo.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService extends GenericService<Professor, Long> {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        super(professorRepository);
        this.professorRepository = professorRepository;
    }

    public Optional<Professor> findByEmail(String email) {
        return professorRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public String getProfessorPublicDTO(String email) {
        Professor professor = professorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        String professorFirsName= professor.getFirstName();
        String professorLastName= professor.getLastName();
        String department = professor.getDepartment().getName();

        return new ProfessorPublicDTO(professorFirsName, professorLastName, email, department).toString();
    }

    @Transactional(readOnly = true)
    public String getProfessorAdminDTO(String email) {
        Professor professor = professorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        String professorFirsName= professor.getFirstName();
        String professorLastName= professor.getLastName();
        long nationalId = professor.getNationalId();
        String phoneNumber = professor.getPhoneNumber();
        String address = professor.getAddress();
        String city = professor.getCity();
        List<Course> courseList = professor.getCourseList();
        Department department = professor.getDepartment();
        List<Student> studentList = professor.getStudentList();

        return new ProfessorAdminDTO(professorFirsName, professorLastName, email, nationalId, phoneNumber,
                address, city, courseList, department, studentList).toString();
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
