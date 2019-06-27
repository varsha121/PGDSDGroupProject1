package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.upgrad.models.Comment;
import org.upgrad.services.CommentService;
import org.upgrad.services.AnswerService;
import org.upgrad.services.NotificationService;
import org.upgrad.services.UserService;


import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    AnswerService answerService;
    @Autowired
    UserService userService;
    @Autowired
    NotificationService notificationService;




    @PostMapping("/api/comment")
    public ResponseEntity<?> giveComment(@RequestParam(value = "answerId") int answerId, String comment, HttpSession httpSession) {
        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint", HttpStatus.UNAUTHORIZED);
        }


        else{
            int userId = (userService.findUserByUsername((String) httpSession.getAttribute("username"))).getId();
            commentService.addComment(userId, answerId);

            notificationService.createNotification(userId, "User with userId " + userId
                    + " has commented your answer with answerId " + answerId);

            return new ResponseEntity<>("AnswerId " + answerId + " commented successfully!", HttpStatus.OK);
        }


    }


    @PutMapping("/api/comment/{commentId}")
    public ResponseEntity<?> editComment(@PathVariable(value = "commentId") int commentId, String comment, HttpSession httpSession) {
        int userId=(userService.findUserByUsername((String)httpSession.getAttribute("username"))).getId();


        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please Login first to acees this endpoint", HttpStatus.UNAUTHORIZED);
        }


        else if(userService.getRoleByUsername((String) httpSession.getAttribute("username")).equalsIgnoreCase("admin")
                        ||(userId==commentService.findUserIdfromComment(commentId))) {
                    commentService.updateComment(commentId, comment, new Date());
                return new ResponseEntity<>("Comment with commentId " + commentId + " edited successfully!", HttpStatus.OK);
            }

            else {
                return new ResponseEntity<>("You do not have rights to edit the comment successfully!", HttpStatus.FORBIDDEN);
            }
        }


    @DeleteMapping("/api/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "commentId") int commentId, HttpSession httpSession) {
        int userId=(userService.findUserByUsername((String)httpSession.getAttribute("username"))).getId();


        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please Login first to acees this endpoint", HttpStatus.UNAUTHORIZED);
        }

        else if(userService.getRoleByUsername((String) httpSession.getAttribute("username")).equalsIgnoreCase("admin")
                ||(userId==commentService.findUserIdfromComment(commentId))) {
            commentService.deleteComment(commentId );
                return new ResponseEntity<>("Comment with commentId " + commentId + " deleted successfully!", HttpStatus.OK);
            }

            else {
                return new ResponseEntity<>("You do not have rights to edit the comment successfully!", HttpStatus.FORBIDDEN);
            }
        }


    @GetMapping("/api/comment/all/{commentId}")
    public ResponseEntity<?> getAllCommentsByAnswer(@PathVariable(value = "answerId") int answerId, HttpSession httpSession) {
        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please Login first to acees this endpoint", HttpStatus.UNAUTHORIZED);
        }

        else {
            int userId = (userService.findUserByUsername((String) httpSession.getAttribute("username"))).getId();
            commentService.getAllComments(answerId);
            List<Comment> comments = commentService.getAllComments(answerId);
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }


    }
}
















