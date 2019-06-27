
package org.upgrad.services;

import org.upgrad.models.Like;

import java.util.List;

public interface LikeService {
    int getUser(int username);

    void addLike(int answerId, int userid);

    void unLike(int userid);


    Like getLikes(int id);

      int getUserId( int id);
}

