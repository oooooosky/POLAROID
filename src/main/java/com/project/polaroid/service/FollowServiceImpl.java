package com.project.polaroid.service;

import com.project.polaroid.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{

    private final FollowRepository followRepository;


    // 팔로워 팔로잉 카운트
    @Override
    @Transactional
    public ArrayList<Integer> followCount(Long memberId) {
        int follower =followRepository.followerCount(memberId);
        int following =followRepository.followingCount(memberId);
        ArrayList<Integer> followCount=new ArrayList<>();
        followCount.add(follower);
        followCount.add(following);
        return followCount;
    }
}
