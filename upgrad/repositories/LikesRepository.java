package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Like;

import javax.transaction.Transactional;
import java.util.List;


@Repository
    public interface LikesRepository extends CrudRepository<Like,Integer> {
        @Query(nativeQuery = true, value = "select id from likes where user_id = upper(?1)")
        int getUser(int username);

        @Transactional
        @Modifying
        @Query(nativeQuery = true, value = "insert into  likes(user_id,answer_id)values (?2,?1)")
        void addLike(int answerId, int userid);

        @Transactional
        @Modifying
        @Query(nativeQuery = true, value = "delete from  likes where user_id=?1")
        void unLike(int userid);

        @Query(nativeQuery = true, value = "select * from  likes where user_id=?1")
        Like getLikes(int id);

        @Query(nativeQuery = true, value = "select * from  likes where answer_id=?1")
        int getUserId(int id);
}

