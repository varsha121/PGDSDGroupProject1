
package org.upgrad.services;


import org.springframework.stereotype.Service;
import org.upgrad.models.User;
import org.upgrad.repositories.UserRepository;

import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;



    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(String username, String password, String email) {
        userRepository.addUser(username, password, email);

    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public String getPasswordByUsername(String username) {
        return userRepository.getPasswordByUsername(username);
    }

    @Override
    public String getRoleByUsername(String username) {
        return userRepository.getRoleByUsername(username);
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteUser(userId);
    }

    @Override
    public List<User> getAllUsers() { return userRepository.getAllUsers();}

    @Override
    public int findUserId(String userName) {
        return userRepository.findUserId(userName);
    }

}
