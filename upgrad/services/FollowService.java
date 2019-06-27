
package org.upgrad.services;

public interface FollowService {


    int findUser(int userid);

    void follow(int categoryId, int userid);

    void unFollow(int userid);

    int findUserId(int id);
}