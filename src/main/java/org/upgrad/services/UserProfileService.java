
package org.upgrad.services;

import org.upgrad.models.UserProfile;

import java.util.Date;

public interface UserProfileService {

    void addUserProfile(String username, String password,String email, String firstName, String lastName, String aboutMe, Date dob, String contactNumber, String country);

    UserProfile getUserProfile(int userId);
}




