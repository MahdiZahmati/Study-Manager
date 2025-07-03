package com.example.demo.service;

import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService extends GenericService<Admin, Long> {

    private final AdminRepository adminRepository;

    public AdminService(JpaRepository<Admin, Long> repository, AdminRepository adminRepository) {
        super(repository);
        this.adminRepository = adminRepository;
    }

    public Optional<Admin> findByUserName(String userName) {
        return adminRepository.findByUserName(userName);
    }

    public Admin updateAdmin(String username, Admin updatedAdmin) {
        return adminRepository.findByUserName(username).map(admin -> {
            admin.setUserName(updatedAdmin.getUserName());
            admin.setPassword(updatedAdmin.getPassword());
            return adminRepository.save(admin);
        }).orElseThrow(() -> new RuntimeException("Admin not found"));
    }

}
