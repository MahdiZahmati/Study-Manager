package com.example.demo.controller;

import com.example.demo.DTO.ProfessorPublicDTO;
import com.example.demo.model.Professor;
import com.example.demo.service.ProfessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/professor")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public ResponseEntity<List<Professor>> getAllProfessors() {
        return ResponseEntity.ok().body(professorService.findAll());
    }

    @GetMapping("/admin/{email}")
    public ResponseEntity<String> getAdminDTOByEmail(@PathVariable String email) {
        Optional<Professor> professor = professorService.findByEmail(email);
        if (professor.isPresent()) {
            return ResponseEntity.ok().body(professorService.getProfessorPublicDTO(email));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/public/{email}")
    public ResponseEntity<String> getPublicDTOByEmail(@PathVariable String email) {
        Optional<Professor> professor = professorService.findByEmail(email);
        if (professor.isPresent()) {
            return ResponseEntity.ok().body(professorService.getProfessorAdminDTO(email));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
        return ResponseEntity.ok().body(professorService.save(professor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable Long id ,@RequestBody Professor professor) {
        return ResponseEntity.ok().body(professorService.updateProfessor(id, professor));
    }

    @DeleteMapping("/{id}")
    public void deleteProfessor(@PathVariable Long id) {
        professorService.delete(id);
    }

}