package com.example.demo.controller;

import com.example.demo.DTO.StudentAdminDTO;
import com.example.demo.DTO.StudentPublicDTO;
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

    @GetMapping("/admin")
    public ResponseEntity<List<StudentAdminDTO>> getAllStudentAdminDTOs() {
        return ResponseEntity.ok().body(studentService.getAllStudentAdminDTOs());
    }

    @GetMapping("/public")
    public ResponseEntity<List<StudentPublicDTO>> getAllStudentPublicDTOs() {
        return ResponseEntity.ok().body(studentService.getAllStudentPublicDTOs());
    }

    @GetMapping("/admin/{email}")
    public ResponseEntity<String> getPublicDTOByEmail(@PathVariable(value = "email") String email) {
        Optional<Student> student = studentService.findByEmail(email);
        if (student.isPresent()) {
            return ResponseEntity.ok().body(studentService.getStudentPublicDTO(email).toString());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/public/{email}")
    public ResponseEntity<String> getAdminDTOByEmail(@PathVariable(value = "email") String email) {
        Optional<Student> student = studentService.findByEmail(email);
        if (student.isPresent()) {
            return ResponseEntity.ok().body(studentService.getStudentAdminDTO(email).toString());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Student> creatStudent(@RequestBody Student student) {
        return ResponseEntity.ok().body(studentService.save(student));
    }

    @PostMapping("/{username}/courses/{title}")
    public ResponseEntity<Void> enroll(
            @PathVariable(value = "username") String username,
            @PathVariable(value = "title") String title) {
        studentService.enroll(username, title);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{username}/gpa")
    public ResponseEntity<Double> getStudentGPA(@PathVariable String username) {
        double ave = studentService.getStudentAve(username);
        return ResponseEntity.ok().body(ave);
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
