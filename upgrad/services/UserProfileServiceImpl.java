package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.UserProfile;
import org.upgrad.repositories.UserProfileRepository;

import java.util.Date;

@Service
public class UserProfileServiceImpl implements UserProfileService{

private UserProfileRepository userProfileRepository;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public void addUserProfile( String username, String password,String email,String firstName, String lastName, String aboutMe, Date dob, String contactNumber, String country) {
        userProfileRepository.addUserProfile(  username,password,email,firstName, lastName, aboutMe, dob, contactNumber,
                country);
    }

    @Override
    public UserProfile getUserProfile(int userId) {
        return this.userProfileRepository.getUserProfile(userId);
    }
}
