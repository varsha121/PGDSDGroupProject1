
package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.Like;
import org.upgrad.repositories.LikesRepository;

import java.util.List;

@Service
public class LikeServiceImp implements LikeService {
    private final LikesRepository likeRepository;

    @Autowired
    public LikeServiceImp(LikesRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public int getUser(int userid) {
        return likeRepository.getUser(userid);
    }

    @Override
    public void addLike(int answerId, int userid) {
        likeRepository.addLike(answerId, userid);
    }

    @Override
    public void unLike(int userid) {
        likeRepository.unLike(userid);
    }

    @Override
    public Like getLikes(int id) {
        return likeRepository.getLikes(id);
    }

    @Override
    public int getUserId(int id) {

        return likeRepository.getUserId(id);
    }


}