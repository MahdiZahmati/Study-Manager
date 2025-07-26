package com.example.demo.controller;

import com.example.demo.DTO.DepartmentAdminDTO;
import com.example.demo.DTO.DepartmentPublicDTO;
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
    public ResponseEntity<List<DepartmentAdminDTO>> getAllDepartmentAdminDTOs() {
        return ResponseEntity.ok().body(departmentService.getAllDepartmentAdminDTO());
    }

    @GetMapping
    public ResponseEntity<List<DepartmentPublicDTO>> getAllDepartmentPublicDTOs() {
        return ResponseEntity.ok().body(departmentService.getAllDepartmentPublicDTO());
    }

    @GetMapping("/admin/{name}")
    public ResponseEntity<String> getAdminDTOByName(@PathVariable(value = "name") String name) {
        Optional<Department> department = departmentService.findByName(name);
        if (department.isPresent()) {
            return ResponseEntity.ok().body(departmentService.getDepartmentAdminDTO(name).toString());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/public/{name}")
    public ResponseEntity<String> getPublicDTOByName(@PathVariable(value = "name") String name) {
        Optional<Department> department = departmentService.findByName(name);
        if (department.isPresent()) {
            return ResponseEntity.ok().body(departmentService.getDepartmentPublicDTO(name).toString());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{name}/average-gpa")
    public ResponseEntity<String> getAverageGpa(@PathVariable String name) {
        double average = departmentService.getAverageForDepartment(name);
        return ResponseEntity.ok().body("Average GPA of department " + name + " is: " + average);
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
