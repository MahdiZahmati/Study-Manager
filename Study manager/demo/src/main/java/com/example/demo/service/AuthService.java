package com.example.demo.service;

import com.example.demo.DTO.LoginDTO;
import com.example.demo.DTO.SignUpDTO;
import com.example.demo.enums.Role;
import com.example.demo.model.Professor;
import com.example.demo.model.Student;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public AuthService(UserRepository userRepo,
                       PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @Transactional
    public void register(SignUpDTO dto) {
        if(userRepo.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        User user;
        if(dto.getRole() == Role.STUDENT) {
            Student s = new Student();
            s.setFirstName(dto.getFirstName());
            s.setLastName(dto.getLastName());
            s.setEmail(dto.getEmail());
            s.setNationalId(dto.getNationalId());
            s.setPhoneNumber(dto.getPhoneNumber());
            s.setAddress(dto.getAddress());
            s.setCity(dto.getCity());
            user = s;
        } else {
            Professor p = new Professor();
            p.setFirstName(dto.getFirstName());
            p.setLastName(dto.getLastName());
            p.setEmail(dto.getEmail());
            p.setNationalId(dto.getNationalId());
            p.setPhoneNumber(dto.getPhoneNumber());
            p.setAddress(dto.getAddress());
            p.setCity(dto.getCity());
            user = p;
        }
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        userRepo.save(user);
    }

    public String login(LoginDTO dto) {
        User user = userRepo.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("user not found"));

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("wrong password");
        }

        return "login successful. welcome " + user.getFirstName();
    }
}