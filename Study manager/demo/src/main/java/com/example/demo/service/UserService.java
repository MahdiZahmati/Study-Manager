package com.example.demo.service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.model.*;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private UserDTO mapToDTO(User user) {

        Long id = user.getId();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        long nationalId = user.getNationalId();
        String phoneNumber = user.getPhoneNumber();
        String address = user.getAddress();
        String city = user.getCity();

        return new UserDTO(id, firstName, lastName, email, nationalId, phoneNumber, address, city);
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUserDTOs() {
        return findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDTO getUserDTO(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        return mapToDTO(user);
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
