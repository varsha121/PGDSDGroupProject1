package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Follow;

import javax.transaction.Transactional;


@Repository
    public interface FollowRepository extends CrudRepository<Follow,Integer> {

        @Query(nativeQuery = true, value = "select id from  follow where user_id=?1")
        int findUser(int userid);

        @Transactional
        @Modifying
        @Query(nativeQuery = true, value = "insert into   follow(user_id,category_id) values (?2,?1)")
        void follow(int answerId, int userid);

        @Transactional
        @Modifying
        @Query(nativeQuery = true, value = "delete from  follow where user_id=?1")
        void unFollow(int userid);

        @Query(nativeQuery = true, value = "select id from  follow where category_id=?1")
        int findUserId(int id);
}

