package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.Answer;
import org.upgrad.repositories.AnswerRepository;

import java.util.Date;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    private AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }


    @Override
    public void addAnswer(int userid, String answer, int questionid) {
        answerRepository.addAnswer(userid,answer,questionid);
    }

    @Override
    public void deleteAnswer(int answerId) {
         answerRepository.deleteAnswer(answerId);
    }

    @Override
    public List<Answer> getAllAnswersByUser(int userId) {
        return answerRepository.getAllAnswersByUser(userId);
    }



    @Override
    public void updateAnswer(int answerId, String answer, Date date) { answerRepository.updateAnswer(answerId, answer, date); }

    @Override
    public int findUserIdfromAnswer(int answerId
    ) {
        return  answerRepository.findUserIdfromAnswer(answerId);
    }

    @Override
    public List<Answer> getAllAnswersToQuestion(int questionId) { return  answerRepository.getAllAnswersToQuestion(questionId); }

}
