package org.upgrad.services;

import org.upgrad.models.Answer;

import java.util.Date;
import java.util.List;

public interface AnswerService {
    void addAnswer(int userid, String answer, int questionid);

    void deleteAnswer(int answerId);

    List<Answer> getAllAnswersByUser(int userId);



    void updateAnswer(int answerId, String answer, Date date);



    List<Answer> getAllAnswersToQuestion(int questionId);





    int findUserIdfromAnswer(int id);
}
