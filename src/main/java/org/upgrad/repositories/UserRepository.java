
package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    @Query(nativeQuery = true, value = "select * from users where upper(username) = upper(?1)")
    User findUserByUsername(String username);

    @Query(nativeQuery = true, value = "select * from users where upper(email) = upper(?1)")
    User findUserByEmail(String email);

    @Query(nativeQuery = true, value = "select password from users where upper(username) = upper(?1)")
    String getPasswordByUsername(String username);

    @Query(nativeQuery = true, value = "select role from users where upper(username) = upper(?1)")
    String getRoleByUsername(String username);

    @Query(nativeQuery = true, value = "select * from users")
    ArrayList<User> getAllUsers();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into users (username, password, email) values (?1, ?2, ?3)")
    void addUser( String username, String password, String email);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from users where id = ?1")
    void deleteUser(int userId);

    @Query(nativeQuery = true,value="select id from users where username=?1")
    int findUserId(String userName);


}