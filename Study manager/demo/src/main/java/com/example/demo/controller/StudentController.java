package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok().body(studentService.findAll());
    }

    @GetMapping("/admin/{email}")
    public ResponseEntity<String> getPublicDTOByEmail(@PathVariable String email) {
        Optional<Student> student = studentService.findByEmail(email);
        if (student.isPresent()) {
            return ResponseEntity.ok().body(studentService.getStudentPublicDTO(email));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/public/{email}")
    public ResponseEntity<String> getAdminDTOByEmail(@PathVariable String email) {
        Optional<Student> student = studentService.findByEmail(email);
        if (student.isPresent()) {
            return ResponseEntity.ok().body(studentService.getStudentAdminDTO(email));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Student> creatStudent(@RequestBody Student student) {
        return ResponseEntity.ok().body(studentService.save(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updateStudent) {
        return ResponseEntity.ok().body(studentService.updateStudent(id, updateStudent));
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
    }

}
