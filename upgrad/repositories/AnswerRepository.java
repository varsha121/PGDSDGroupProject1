package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Answer;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<Answer,String>
{
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into  answer(ans,user_id,question_id) values (?2,?1,?3)")
    void addAnswer(int userid, String answer, int questionid);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from answer where id=?1")
    void deleteAnswer(int answerId);

    @Query(nativeQuery = true, value = "select * from answer where user_id=?1")
    List<Answer> getAllAnswersByUser(int userId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true , value= "update answer set ans = ?2, modifiedon = ?3 where id = ?1 ")
    void updateAnswer(int answerId, String answer, Date date);

    @Query(nativeQuery = true ,value ="select *from answer where id=?1")
    int findUserIdfromAnswer(int answerId);

    @Query(nativeQuery = true , value = "select * from answer where question_id=?1")
    List<Answer> getAllAnswersToQuestion(int questionId);



}
