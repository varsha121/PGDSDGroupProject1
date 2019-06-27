package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.Comment;
import org.upgrad.repositories.CommentRepository;

import java.util.Date;
import java.util.List;


@Service
public class CommentServiceImp implements CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImp(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    @Override
    public void addComment(int userId, int answerId) { commentRepository.addComment(userId ,answerId); }

    @Override
    public void deleteComment(int commentId) { commentRepository.deleteComment(commentId); }



    @Override
    public List<Comment> getAllComments(int answerId) { return this.commentRepository.getAllComments(answerId); }

    @Override
    public int findUserIdfromComment(int commentId) { return this.commentRepository.findUserIdfromComment(commentId); }

    @Override
    public void updateComment(int commentId, String comment, Date date) { commentRepository.updateComment(commentId ,comment ,date); }

}

