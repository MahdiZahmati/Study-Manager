package com.example.demo.controller;

import com.example.demo.DTO.ProfessorAdminDTO;
import com.example.demo.DTO.ProfessorPublicDTO;
import com.example.demo.model.Professor;
import com.example.demo.service.ProfessorService;
import org.hibernate.mapping.Value;
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
    public ResponseEntity<List<ProfessorAdminDTO>> getAllProfessorAdminDTOs() {
        return ResponseEntity.ok().body(professorService.getAllProfessorAdminDTO());
    }

    @GetMapping
    public ResponseEntity<List<ProfessorPublicDTO>> getAllProfessorPublicDTOs() {
        return ResponseEntity.ok().body(professorService.getAllProfessorPublicDTO());
    }

    @GetMapping("/admin/{email}")
    public ResponseEntity<String> getAdminDTOByEmail(@PathVariable(value = "email") String email) {
        Optional<Professor> professor = professorService.findByEmail(email);
        if (professor.isPresent()) {
            return ResponseEntity.ok().body(professorService.getProfessorPublicDTO(email).toString());
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/public/{email}")
    public ResponseEntity<String> getPublicDTOByEmail(@PathVariable(value = "email") String email) {
        Optional<Professor> professor = professorService.findByEmail(email);
        if (professor.isPresent()) {
            return ResponseEntity.ok().body(professorService.getProfessorAdminDTO(email).toString());
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
        return ResponseEntity.ok().body(professorService.save(professor));
    }

    @PostMapping("/{professorUsername}/offer/{courseTitle}")
    public ResponseEntity<Void> offerCourse(
            @PathVariable(value = "professorUsername") String professorUsername,
            @PathVariable(value = "courseTitle") String courseTitle) {
        professorService.offerCourse(professorUsername, courseTitle);
        return ResponseEntity.ok().build();
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