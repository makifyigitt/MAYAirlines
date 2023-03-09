package com.may.MAYAirlines.service;

import com.may.MAYAirlines.core.exception.ErrorCode;
import com.may.MAYAirlines.core.exception.UserNotFoundException;
import com.may.MAYAirlines.core.exception.UsernameExistException;
import com.may.MAYAirlines.dto.UserCreateDTO;
import com.may.MAYAirlines.dto.UserDTO;
import com.may.MAYAirlines.dto.UserUpdateDTO;
import com.may.MAYAirlines.entity.User;
import com.may.MAYAirlines.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    public User findById(int id){
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    public UserDTO getById(int id) {
        return new UserDTO(findById(id));
    }

    public List<UserDTO> getAllActiveUsers() {
        return userRepository.getAllActiveCustomers()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    public List<UserDTO> getAllInactiveUsers() {
        return userRepository.getAllInactiveCustomers()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }
//TODO hem customer hem user da username kontrollü yapılmalı.
    public void create(UserCreateDTO from) {
        if (userRepository.findByUsername(from.getUsername()).isPresent())
            throw new UsernameExistException(ErrorCode.USERNAME_IS_USED);
        else{
            User newUser = new User();
            newUser.setUsername(from.getUsername());
            newUser.setPassword(from.getPassword());
            newUser.setFirstName(from.getFirstName());
            newUser.setSurname(from.getSurname());
            newUser.setEmail(from.getEmail());
            newUser.setPhoneNumber(from.getPhoneNumber());
            newUser.setAddress(from.getAddress());
            userRepository.save(newUser);
        }
    }

    public void update(UserUpdateDTO from) {
        User user = findById(from.getId());
        user.setFirstName(from.getFirstName());
        user.setSurname(from.getSurname());
        user.setEmail(from.getEmail());
        user.setPhoneNumber(from.getPhoneNumber());
        user.setAddress(from.getAddress());
        user.setUpdateDate(new Date());
        userRepository.save(user);
    }

    public void makeActive(int id) {
        User user = findById(id);
        user.setStatus(true);
        userRepository.save(user);
    }

    public void inactive(int id) {
        User user = findById(id);
        user.setStatus(false);
        userRepository.save(user);
    }
}
