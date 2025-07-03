package com.example.demo.service;

import com.example.demo.model.Professor;
import com.example.demo.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
