

package org.upgrad.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.Question;
import org.upgrad.repositories.QuestionRepository;


import java.util.List;
import java.util.Set;


@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;


    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void getAllCategories() {

    }

    @Override
    public void addQuestion(Set<Integer> categoryid, String question, int userid) {
        questionRepository.addQuestion(categoryid, question, userid);
    }



    @Override
    public List<Question> getAllQuestionsByCategory(int categoryid) {

        return questionRepository.getAllQuestionsByCategory(categoryid);

    }

    @Override
    public List<Question> getAllQuestionsByUser(int user_id) {
        return   questionRepository.getAllQuestionsByUser(user_id);

    }

    @Override
    public int deleteQuestion(int questionId) {
        return questionRepository.deleteQuestion(questionId);
    }



    @Override
    public List<Integer> getAllQuestionIds(int userid) {
        return questionRepository.getAllQuestionIds(userid);
    }

    @Override
    public int getCategoryId(Integer id) {
        return questionRepository.getCategoryId(id);
    }

    @Override
    public int findUserIdfromQuestion(int questionId) { return   questionRepository.findUserIdfromQuestion(questionId); }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.getAllQuestions ();
    }
}


