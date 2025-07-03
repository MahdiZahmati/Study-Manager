package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends GenericService<User, Long> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUser(Long id, User userUpdate) {
        return userRepository.findById(id).map(user -> {
            user.setFirstName(userUpdate.getFirstName());
            user.setLastName(userUpdate.getLastName());
            user.setEmail(userUpdate.getEmail());
            user.setCity(userUpdate.getCity());
            user.setNationalId(userUpdate.getNationalId());
            user.setPhoneNumber(userUpdate.getPhoneNumber());
            user.setAddress(userUpdate.getAddress());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("user not found"));
    }

}
