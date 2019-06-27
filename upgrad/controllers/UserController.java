
package org.upgrad.controllers;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.upgrad.models.Notification;
import org.upgrad.models.User;

import org.upgrad.services.NotificationService;
import org.upgrad.services.UserProfileService;
import org.upgrad.services.UserService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserProfileService userProfileService;
    @Autowired
    NotificationService notificationService;

    @PostMapping("/api/user/signup")
    public ResponseEntity<?>userSignUp(
            @RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName", defaultValue = "null") String lastName,
                                        @RequestParam(value = "username") String username, @RequestParam(value = "email") String email,
                                        @RequestParam(value = "password") String password, @RequestParam(value = "country") String country,
                                        @RequestParam(value = "aboutMe", defaultValue = "null") String aboutMe,
                                        @RequestParam(value = "dateOfBirth") Date dob, @RequestParam(value = "phoneNumber", defaultValue = "null") String phoneNumber) {

        if (userService.findUserByUsername(username) != null) {
            return new ResponseEntity<>("Try any other Username,this Username has already been taken.", HttpStatus.FORBIDDEN);
        }

        if (userService.findUserByEmail(email) != null) {
            return new ResponseEntity<>("This user has already been registered,try with any other emailId.", HttpStatus.FORBIDDEN);
        }

        String sha256hex = Hashing.sha256()
                .hashString(password, Charsets.US_ASCII)
                .toString();

        userProfileService.addUserProfile(username, sha256hex, email, firstName, lastName, aboutMe, dob, phoneNumber, country);
         userService.addUser(username,sha256hex,email);
        return new ResponseEntity<>(username + " successfully registered", HttpStatus.OK);
    }

    @PostMapping("/api/user/login")
    public ResponseEntity<?> userSignIn(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password,
                                        HttpSession httpSession) {

        String sha256hex = Hashing.sha256()
                .hashString(password, Charsets.US_ASCII)
                .toString();
        if (!userService.getPasswordByUsername(username).equals(sha256hex)) {
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
        }

        else if (userService.getRoleByUsername(username).equalsIgnoreCase("admin")) {
            httpSession.setAttribute("username", username);
            return new ResponseEntity<>("You have logged in as admin!", HttpStatus.OK);
        }

        else {
            httpSession.setAttribute("username", username);
            return new ResponseEntity<>("You have logged in successfully!", HttpStatus.OK);
        }
    }

    @PostMapping("/api/user/logout")
    public ResponseEntity<?> userSignOut(HttpSession httpSession) {

        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("You are currently not logged in", HttpStatus.UNAUTHORIZED);
        }

        else {
            httpSession.removeAttribute("username");
            return new ResponseEntity<>("You have logged out successfully!", HttpStatus.OK);
        }
    }

    @GetMapping("/api/user/userProfile/{userId}")
    public ResponseEntity<?> getUserProfile(@PathVariable(value = "userId") int userId, HttpSession httpSession) {

        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint", HttpStatus.UNAUTHORIZED);
        }

        else {
            if (userProfileService.getUserProfile(userId) == null) {
                return new ResponseEntity<>("User Profile not found!", HttpStatus.NOT_FOUND);
            }

            else {
                return new ResponseEntity<>(userProfileService.getUserProfile(userId), HttpStatus.OK);
            }
        }
    }

    @GetMapping("/api/user/notification/new")
    public ResponseEntity<?> getNewNotifications(HttpSession httpSession) {
        User user=userService.findUserByUsername((String) httpSession.getAttribute("username"));
        List<Notification> notifications = notificationService.getNewNotifications(user.getId());

        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else {

            return new ResponseEntity<>(notifications,HttpStatus.OK);
        }
    }

    @GetMapping("/api/user/notification/all")
    public ResponseEntity getAllNotifications(HttpSession httpSession) {
        User user=userService.findUserByUsername((String) httpSession.getAttribute("username"));
        List<Notification> notifications = notificationService.getAllNotifications(user.getId());

        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }
        else  {
            return new ResponseEntity<>(notifications, HttpStatus.OK);
        }
    }
}