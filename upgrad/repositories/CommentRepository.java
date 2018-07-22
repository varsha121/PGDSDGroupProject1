
package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Comment;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
@Repository
public interface CommentRepository extends CrudRepository<Comment,String> {


    @Transactional
    @Modifying
    @Query(nativeQuery = true ,value = "insert  into comment(user_id ,answer_id) values(?1,?2)")
    void addComment(int user_id, int answer_id);

    @Query(nativeQuery = true , value ="select* from comment where answer_id=?1" )
    List<Comment> getAllComments(int answerid);

    @Query(nativeQuery = true , value = "select user_id from comment where id=?1")
    int findUserIdfromComment(int commentId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true , value ="insert into comment(comment_id,comment) values(?1,?2)")
    void updateComment(int commentid, String comment, Date date);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value= "delete  from comment where id=?1")
    void deleteComment(int commentId);
}