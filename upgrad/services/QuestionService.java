package org.upgrad.services;

        import org.upgrad.models.Question;

        import java.util.List;
        import java.util.Set;

public interface QuestionService {


     void getAllCategories();

    void addQuestion(Set<Integer> categoryid, String question, int userid);

    List<Question> getAllQuestionsByCategory(int categoryid);
    List<Question> getAllQuestionsByUser(int user_id);

    int deleteQuestion(int questionId);



    List<Integer>  getAllQuestionIds(int userid);

    int getCategoryId(Integer id);

    int findUserIdfromQuestion(int questionId);

    List<Question> getAllQuestions();
}



