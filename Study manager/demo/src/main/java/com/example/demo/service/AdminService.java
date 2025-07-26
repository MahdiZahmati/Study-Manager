package com.example.demo.service;

import com.example.demo.DTO.AdminDTO;
import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService extends GenericService<Admin, Long> {

    private final AdminRepository adminRepository;

    public AdminService(JpaRepository<Admin, Long> repository, AdminRepository adminRepository) {
        super(repository);
        this.adminRepository = adminRepository;
    }

    public Optional<Admin> findByUserName(String username) {
        return adminRepository.findByUserName(username);
    }

    @Transactional(readOnly = true)
    public List<AdminDTO> getAllAdmins() {
        return findAll().stream()
                .map(admin -> {
                    Long id = admin.getId();
                    String name = admin.getUserName();
                    String password = admin.getPassword();

                    return new AdminDTO(id, name, password);
                        }
                ).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AdminDTO getAdminDTO(String username) {
        Admin admin = adminRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        Long id = admin.getId();
        String password = admin.getPassword();

        return new AdminDTO(id, username, password);
    }

    public Admin updateAdmin(String username, Admin updatedAdmin) {
        return adminRepository.findByUserName(username).map(admin -> {
            admin.setUserName(updatedAdmin.getUserName());
            admin.setPassword(updatedAdmin.getPassword());
            return adminRepository.save(admin);
        }).orElseThrow(() -> new RuntimeException("Admin not found"));
    }

}
