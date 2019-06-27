
package org.upgrad.repositories;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.UserProfile;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface UserProfileRepository  extends CrudRepository<UserProfile, String> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into users (firstName,lastName,aboutMe,dob,contactNumber,country) values (?1, ?2, ?3, ?4,?5,?6,?7)")
    void addUserProfile( String username, String password,String email,String firstName, String lastName, String aboutMe, Date dob, String contactNumber, String country);
    @Query(nativeQuery = true, value = "select * from user_profile where user_id=1")
    UserProfile getUserProfile(int userId);
}