package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.upgrad.models.Question;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface QuestionRepository extends CrudRepository<Question,String> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into question_category(categoryid,userid) values(?1 ,?3)")
    void addQuestion(Set<Integer> categoryid,String question,int userid);



    @Query(nativeQuery = true,value="select * from question_category where categoryid=?1")
    List<Question> getAllQuestionsByCategory(int categoryid);


    @Query(nativeQuery = true,value="select * from question where user_id=?1")
    List<Question> getAllQuestionsByUser(int user_id);



    @Query(nativeQuery = true, value = "delete  from question where id=?1")
     int deleteQuestion(int questionId);

    @Query(nativeQuery = true,value="select id from question where user_id=?1")
    List<Integer> getAllQuestionIds(int userid);


    @Query(nativeQuery = true,value="select user_id from question where question_id=?1")
    int findUserIdfromQuestion(int questionId);

    @Query(nativeQuery = true,value="select category_id from question_category where question_id=?2 ")
    int getCategoryId(Integer id);

    @Query(nativeQuery = true,value="select * from question ")
    List<Question> getAllQuestions();
}
