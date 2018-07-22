package org.upgrad.services;

import org.upgrad.models.Comment;

import java.util.Date;
import java.util.List;

public interface CommentService {
    void addComment(int userId, int answerId);


    void deleteComment(int commentId);

    List<Comment> getAllComments(int answerId);

    int findUserIdfromComment(int commentId);

    void updateComment(int commentId, String comment, Date date);

}
