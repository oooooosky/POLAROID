package com.project.polaroid.repository;

import com.project.polaroid.entity.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<FollowEntity,Long> {

    @Query(value = "SELECT count(a) FROM FollowEntity a WHERE a.followFollowing= :memberId ")
    public int followerCount(Long memberId);

    @Query(value = "SELECT count(a) FROM FollowEntity a WHERE a.memberId.id= :memberId ")
    public int followingCount(Long memberId);


}
