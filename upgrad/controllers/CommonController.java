package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.upgrad.models.Question;
import org.upgrad.services.CategoryService;
import org.upgrad.services.QuestionService;
import org.upgrad.services.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class CommonController {

    @Autowired
    public QuestionService questionService;
    @Autowired
    public CategoryService categoryService;
    @Autowired
    public UserService userService;

    @GetMapping("/api/categories/all")
    public ResponseEntity<?> getAllCategories(HttpSession httpSession){
        int userid = (userService.findUserByUsername((String) httpSession.getAttribute("username"))).getId();
        List<Integer> questionids=questionService.getAllQuestionIds(userid);
        List<String> list=null;
        for(Integer id:questionids){
            int categoryid=questionService.getCategoryId(id);
            String Category=categoryService.getTitle(categoryid);
            list.add(Category);
            }
        return new ResponseEntity<>(list,HttpStatus.OK);


    }

    @GetMapping("/api/questions/all")
    public ResponseEntity<?> getAllQuestions(HttpSession httpSession){
        int userid=(userService.findUserByUsername((String)httpSession.getAttribute("username")).getId());
        List<Question>questions=questionService.getAllQuestionsByUser(userid);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

}
