package com.example.SpringPostgresRedis.service;

import com.example.SpringPostgresRedis.model.User;
import com.example.SpringPostgresRedis.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @CacheEvict(value = "users" , allEntries = true)
    public boolean addUser(User user) {
        try{
            userRepository.save(user);
            return true;
        }
        catch (Exception exception){
            return false;
        }

    }

    @CachePut(value = "users")
    public List<User> getUsers() {
       return userRepository.findAll();
    }

    @CachePut(cacheNames = "users", key = "#id")
    public User getOneUser(Long id) throws Exception {
        try {
            return userRepository.findUserById(id);
        }
        catch (Exception e){
            throw new Exception();
        }
    }

    @CacheEvict(value = "users", allEntries = true)
    public boolean deleteUserById(Long id) {
        try{
            userRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @CachePut(value = "users")
    @Transactional
    public boolean updateUser(Long id, User user) {
        try{
            User user2 = userRepository.findUserById(id);

            user2.setFirstname(user.getFirstname());
            user2.setLastname(user.getLastname());
            user2.setEmail(user.getEmail());

            userRepository.save(user2);

            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
