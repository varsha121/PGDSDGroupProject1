package org.upgrad.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.repositories.FollowRepository;


@Service
public class FollowServiceImp implements FollowService {
    private final FollowRepository followRepository;

    @Autowired
    public FollowServiceImp(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }




    @Override
    public int findUser(int userid) {
        return followRepository.findUser(userid);
    }

    @Override
    public void follow(int categoryId, int userid)
    { followRepository.follow(categoryId,userid);; }

    @Override
    public void unFollow(int userid)
    {
        followRepository.unFollow(userid);
    }

    @Override
    public int findUserId(int id) {
        return followRepository.findUserId(id);
    }
}
