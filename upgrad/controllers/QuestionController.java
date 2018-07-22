
package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.upgrad.models.Question;
import org.upgrad.models.User;
import org.upgrad.services.CategoryService;
import org.upgrad.services.UserService;
import org.upgrad.services.QuestionService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;



@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;



    @PostMapping("/api/question")
    public ResponseEntity<?> createQuestion(@RequestParam(value = "categoryId") Set<Integer> categoryid,
                                            @RequestParam(value = "question") String question, HttpSession httpSession) {

        User user = userService.findUserByUsername((String) httpSession.getAttribute("username"));
        int userid = user.getId();
        questionService.addQuestion(categoryid, question, userid);

        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please login first to access the end point!", HttpStatus.UNAUTHORIZED);
        }

        else {
            return new ResponseEntity<>("Question added successfully!", HttpStatus.OK);
        }
    }

    @GetMapping("/api/question/all/{category}")
    public ResponseEntity<?> getAllQuestionsByCategory(@PathVariable(name = "categoryId") int categoryid, HttpSession httpSession) {

        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else {
            List<Question> questions = questionService.getAllQuestionsByCategory(categoryid);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }
    }
    @GetMapping("/api/question/all")
    public ResponseEntity<?> getAllQuestionsByUser( HttpSession httpSession) {
        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else {

            User user = userService.findUserByUsername((String) httpSession.getAttribute("username"));
            int user_id = user.getId();
            List<Question> questions = questionService.getAllQuestionsByCategory(user_id);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }
    }
    @DeleteMapping("/api/question/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable(name = "questionId") int questionId, HttpSession httpSession) {
        User user = userService.findUserByUsername((String) httpSession.getAttribute("username"));
        int user_id = user.getId();


        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else if (userService.getRoleByUsername((String) httpSession.getAttribute("username")).equalsIgnoreCase("admin")
                ||(user_id==questionService.findUserIdfromQuestion(questionId)))
                 {
            questionService.deleteQuestion(questionId);
            return new ResponseEntity<>("Question with " + questionId + " deleted successfully!", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("You do not have right to delete this question !", HttpStatus.FORBIDDEN);
        }
    }
}






