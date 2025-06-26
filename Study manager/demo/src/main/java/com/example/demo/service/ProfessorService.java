package com.example.demo.service;

import com.example.demo.model.Professor;
import com.example.demo.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Optional<Professor> findById(Long id) {
        return professorRepository.findById(id);
    }

    public Professor createProfessor(Professor professor){
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

    public void deleteProfessor(Long id) {
        professorRepository.deleteById(id);
    }



}
