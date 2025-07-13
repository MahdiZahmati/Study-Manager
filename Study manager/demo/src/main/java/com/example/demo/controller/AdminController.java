package com.example.demo.controller;

import com.example.demo.model.Admin;
import com.example.demo.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        return ResponseEntity.ok().body(adminService.findAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity<String> getByUsername(@PathVariable String username) {
        Optional<Admin> admin = adminService.findByUserName(username);
        if (admin.isPresent()) {
            return ResponseEntity.ok().body(adminService.getAdminDTO(username));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createAdmin(@RequestBody Admin admin) {
        return ResponseEntity.ok().body(adminService.save(admin).toString());
    }

    @PutMapping("/{username}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable String username, @RequestBody Admin updatedAdmin) {
        return ResponseEntity.ok().body(adminService.save(updatedAdmin));
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable long id) {
        adminService.delete(id);
    }
}
