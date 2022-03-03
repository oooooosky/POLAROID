package com.project.polaroid.repository;

import com.project.polaroid.entity.BoardEntity;
import com.project.polaroid.entity.LikeEntity;
import com.project.polaroid.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    LikeEntity findByBoardIdAndMemberId(BoardEntity boardId, MemberEntity memberId);

    void deleteByBoardIdAndMemberId(BoardEntity boardId, MemberEntity memberId);
}
