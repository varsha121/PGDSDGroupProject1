
package org.upgrad.services;

import org.upgrad.models.Answer;
import org.upgrad.models.User;

import java.util.Date;
import java.util.List;

public interface UserService {

   void addUser(String username, String password, String email);

    User findUserByUsername(String username);
     User findUserByEmail(String email);

    String getPasswordByUsername(String username);

    String getRoleByUsername(String username);

    void deleteUser(int userId);
     List<User> getAllUsers();


    int findUserId(String userName);


}