package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok().body(departmentService.findAll());
    }

    @GetMapping("/admin/{name}")
    public ResponseEntity<String> getAdminDTOByName(@PathVariable String name) {
        Optional<Department> department = departmentService.findByName(name);
        if (department.isPresent()) {
            return ResponseEntity.ok().body(departmentService.getDepartmentAdminDTO(name));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/public/{name}")
    public ResponseEntity<String> getPublicDTOByName(@PathVariable String name) {
        Optional<Department> department = departmentService.findByName(name);
        if (department.isPresent()) {
            return ResponseEntity.ok().body(departmentService.getDepartmentPublicDTO(name));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        return ResponseEntity.ok().body(departmentService.save(department));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        return ResponseEntity.ok().body(departmentService.updateDepartment(id, department));
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.delete(id);
    }

}
