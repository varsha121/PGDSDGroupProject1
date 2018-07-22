
    package org.upgrad.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.Answer;
import org.upgrad.services.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

    @Controller
    public class AnswerController {

        @Autowired
        QuestionService questionService;
        @Autowired
        UserService userService;
        @Autowired
        AnswerService answerService;
        @Autowired
        NotificationService notificationService;





        @PostMapping("/api/answer")
        public ResponseEntity<?> createAnswer(@RequestParam(value = "question_id") int questionid,
                                              @RequestParam(value = "answer") String answer, HttpSession httpSession) {


            if (httpSession.getAttribute("username") == null) {
                return new ResponseEntity<>("Please login first to access the end point!", HttpStatus.UNAUTHORIZED);
            } else {
                int userId=(userService.findUserByUsername((String)httpSession.getAttribute("username"))).getId();
                answerService.addAnswer(userId, answer,questionid);

                notificationService.createNotification(userId, "User with userId " + userId
                        + " has answered your question with questionId " + questionid);
                return new ResponseEntity<>("Answer to questionid" +questionid+ "added successfully!", HttpStatus.OK);
            }
        }



        @GetMapping("/api/answer/{answerId}")
        public ResponseEntity<?> editAnswer(@PathVariable(name = "answerId") int answerId,String answer, HttpSession httpSession) {
            int userId=(userService.findUserByUsername((String)httpSession.getAttribute("username"))).getId();

            if (httpSession.getAttribute("username") == null) {
                return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
            }

            else if(userService.getRoleByUsername((String) httpSession.getAttribute("username")).equalsIgnoreCase("admin")
                        ||(userId==answerService.findUserIdfromAnswer(answerId))) {
                answerService.updateAnswer(answerId, answer, new Date());
                return new ResponseEntity<>("Answer with answerId " + answerId + " edited successfully.", HttpStatus.OK);
            }

            else{
                return new ResponseEntity<>("You do not have rights to edit this answer.", HttpStatus.FORBIDDEN);
            }
        }
        @GetMapping("/api/answer/all/{questionId}")
        public ResponseEntity<?> getAllAnswersToQuestion(@PathVariable(name= "questionId")int questionId , HttpSession httpSession) {


            if (httpSession.getAttribute("username") == null) {
                return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
            }


            else {

                List<Answer> answers = answerService.getAllAnswersToQuestion(questionId);

                return new ResponseEntity<>(answers, HttpStatus.OK);
            }
        }
        @GetMapping("/api/answer/all")
        public ResponseEntity<?> getAllAnswersByUser(HttpSession httpSession) {


            if (httpSession.getAttribute("username") == null) {
                return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
            }

            else {
                int userId=(userService.findUserByUsername((String)httpSession.getAttribute("username"))).getId();
                List<Answer> answers = answerService.getAllAnswersByUser(userId);
                return new ResponseEntity<>(answers, HttpStatus.OK);
            }

        }


        @DeleteMapping( "/api/answer/{answerId}")
            public ResponseEntity<?> deleteAnswer(@PathVariable(name = "answerid") int answerId, HttpSession httpSession) {
            int userId=(userService.findUserByUsername((String)httpSession.getAttribute("username"))).getId();
                if (httpSession.getAttribute("username") == null) {
                    return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
                }


                else if (userService.getRoleByUsername((String) httpSession.getAttribute("username")).equalsIgnoreCase("admin")
                ||((userId==answerService.findUserIdfromAnswer(answerId))) )
                {
                    answerService.deleteAnswer(answerId);
                    return new ResponseEntity<>("Answer with " + answerId + " deleted successfully!", HttpStatus.OK);
                }


                else {
                    return new ResponseEntity<>("You do not have right to delete this answer !", HttpStatus.FORBIDDEN);
                }
            }
        }





