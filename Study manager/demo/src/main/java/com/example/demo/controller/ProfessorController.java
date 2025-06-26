package com.example.demo.controller;

import com.example.demo.model.Professor;
import com.example.demo.service.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/professor")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public List<Professor> getAllProfessors() {
        return professorService.findAll();
    }

    @GetMapping("/{id}")
    public Professor getById(@PathVariable Long id) {
        return professorService.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor not found"));
    }

    @PostMapping
    public Professor createProfessor(@RequestBody Professor professor) {
        return professorService.createProfessor(professor);
    }

    @PutMapping("/{id}")
    public Professor updateProfessor(@PathVariable Long id ,@RequestBody Professor professor) {
        return professorService.updateProfessor(id, professor);
    }

    @DeleteMapping("/{id}")
    public void deleteProfessor(@PathVariable Long id) {
        professorService.deleteProfessor(id);
    }

}