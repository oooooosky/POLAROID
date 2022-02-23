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

    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private MemberEntity memberEntity;

    @Column
    private int likeStatus;

}
