package com.jobportal.jobportalbackend.service;

import com.jobportal.jobportalbackend.entity.User;
import java.util.List;
import com.jobportal.jobportalbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User createUser(User user)
    {
        return userRepository.save(user);
    }
    public User getUserById(Long id)
    {
        return userRepository.findById(id).orElse(null);
    }
    public User updateUser(Long id, User updatedUser)
    {
        User user = userRepository.findById(id).orElse(null);

        if(user != null)
        {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setRole(updatedUser.getRole());

            return userRepository.save(user);
        }

        return null;
    }
    public void deleteUser(Long id)
    {
        userRepository.deleteById(id);
    }
    public User getUserByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }
    public List<User> createUsers(List<User> users)
    {
        return userRepository.saveAll(users);
    }
    public User login(String email,String password)
    {
        return userRepository.findByEmailAndPassword(email,password);
    }
}
