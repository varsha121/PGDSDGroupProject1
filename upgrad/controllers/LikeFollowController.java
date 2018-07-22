package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.upgrad.services.*;

import javax.servlet.http.HttpSession;

@Controller
public class LikeFollowController {
    @Autowired
    public AnswerService answerService;
    @Autowired
    public CategoryService categoryService;
    @Autowired
    public LikeService likeService;
    @Autowired
    FollowService followService;
    @Autowired
    public UserService userService;
    @Autowired
    public NotificationService notificationService;


    @PostMapping("/api/like/{answerId}")
    public ResponseEntity<?> giveLikes(@PathVariable(name = "answewrId") int answerId, HttpSession  httpSession) {
        int userid = userService.findUserByUsername((String) httpSession.getAttribute("username")).getId();


        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }
            else if (likeService.getUser(userid) != 0) {
                return new ResponseEntity<>("You have already liked this answer!", HttpStatus.OK);
            }


            else  {
                likeService.addLike(answerId, userid);
                int userId=answerService.findUserIdfromAnswer(answerId);
            notificationService.createNotification(userId, "User with userId " + userId
                    + " has liked your answer with answerId " + answerId);
                return new ResponseEntity<>(answerId + "liked successfully", HttpStatus.OK);
            }

        }
    @DeleteMapping("/api/like/{answerId}")
     public ResponseEntity<?> unLike(@PathVariable(name="anwserId")int answerId,HttpSession httpSession){
        int userid = userService.findUserByUsername((String) httpSession.getAttribute("username")).getId();


        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }


        else if (likeService.getUser(userid) == 0) {
            return new ResponseEntity<>("You have not liked this answer", HttpStatus.OK);
        }
        else{
            likeService.unLike( userid);
            return new ResponseEntity<>("You have unliked the answer with"+answerId+" successfully", HttpStatus.OK);
        }
    }
    @PostMapping("/api/follow/{CategoryId}")
    public ResponseEntity<?> addFollowCategory(@PathVariable(name = "categoryId") int categoryId, HttpSession  httpSession) {
        int userid = userService.findUserByUsername((String) httpSession.getAttribute("username")).getId();


        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }
            else if (followService.findUser(userid) != 0) {
                return new ResponseEntity<>("You have already followed this category!", HttpStatus.OK);
            }
            else  {
                followService.follow(categoryId, userid);
                return new ResponseEntity<>(categoryId + "liked successfully", HttpStatus.OK);
            }
        }

    @DeleteMapping("/api/follow/{CategoryId}")
    public ResponseEntity<?> unFollow(@PathVariable(name = "categoryId") int categoryId, HttpSession  httpSession) {
        int userid = userService.findUserByUsername((String) httpSession.getAttribute("username")).getId();


        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }
        else if (followService.findUser(userid) == 0) {
            return new ResponseEntity<>("You have not liked this answer", HttpStatus.OK);
        }
        else  {
            followService.unFollow( userid);
            return new ResponseEntity<>("You have unfollowed the category with"+categoryId+" successfully", HttpStatus.OK);
        }
    }
}
