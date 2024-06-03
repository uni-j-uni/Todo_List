package com.example.todo_list.Service;

import com.example.todo_list.Domain.User;
import com.example.todo_list.Dto.UserDto;
import com.example.todo_list.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    // Create
    public void createUser(UserDto userDto){
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setName(userDto.getName());
        user.setPasswd(userDto.getPasswd());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
    }

    // Read
    public List<User> getAllUsers() { return userRepository.findAll(); }
    public Optional<User> getUserById(Long id) { return userRepository.findById(id); }

    // Update
    @Transactional
    public User updateUser(Long id, UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (userDto.getName() != null) user.setName(userDto.getName());
            if (userDto.getPasswd()!= null) user.setPasswd(userDto.getPasswd());
            if (userDto.getEmail() != null) user.setEmail(userDto.getEmail());
            return userRepository.save(user);
        }
        else throw new RuntimeException("User not found with id " + id);
    }

    // Delete
    @Transactional
    public void deleteUser(Long id){ userRepository.deleteById(id); }
}
