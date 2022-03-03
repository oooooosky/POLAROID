package com.project.polaroid.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "like_table")
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberId;

    public static LikeEntity toLikeEntity(MemberEntity memberEntity, BoardEntity boardEntity){
        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setMemberId(memberEntity);
        likeEntity.setBoardId(boardEntity);
        return likeEntity;
    }

}
