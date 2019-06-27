
package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.upgrad.services.CategoryService;
import org.upgrad.services.UserProfileService;
import org.upgrad.services.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserProfileService userProfileService;

    @DeleteMapping("/api/admin/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "userId") int userId, HttpSession httpSession) {

        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else if
                (userService.getRoleByUsername((String) httpSession.getAttribute("username")).
                        equalsIgnoreCase("admin")) {
            userService.deleteUser(userId);
            return new ResponseEntity<>("User with userId " + userId + " deleted successfully!", HttpStatus.OK);
        }

        else {
            return new ResponseEntity<>("You do not have rights to delete a user!", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/api/admin/users/all")
    public ResponseEntity<?> getAllUsers(HttpSession httpSession) {

        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else if (userService.getRoleByUsername((String) httpSession.getAttribute("username"))
                .equalsIgnoreCase("admin")) {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        }

        else {
            return new ResponseEntity<>("You do not have rights to access all users!", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/api/admin/category")
    public ResponseEntity<?> createCategory(@RequestParam(value = "categoryTitle") String categoryTitle, @RequestParam(value = "categoryDescription") String categoryDescription,
                                            HttpSession httpSession) {

        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }


        else if (userService.getRoleByUsername((String) httpSession.getAttribute("username"))
                .equalsIgnoreCase("admin")) {
            categoryService.addCategory(categoryTitle, categoryDescription);
            return new ResponseEntity<>(categoryTitle + " category added successfully.", HttpStatus.OK);
        }


        else {
            return new ResponseEntity<>("You do not have rights to add categories.", HttpStatus.UNAUTHORIZED);
        }
    }
}