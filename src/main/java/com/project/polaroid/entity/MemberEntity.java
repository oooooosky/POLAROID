package com.project.polaroid.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="member_table")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "memberId")
    private Long id;

    @Column(unique = true, length = 50)
    private String memberEmail;

    // 암호화되어 저장되어 길이가 김
    @Column(length = 70)
    private String memberPw;

    @Column(unique = true, length = 20)
    private String memberNickname;

    @Column(length = 11)
    private String memberPhone;

    @Column(length = 100)
    private String memberAddress;

    @Column(length = 100)
    private String memberFilename;

    @Column(length = 20)
    private String memberRole;

    @Column(length = 20)
    private String memberProvider;

    @Column(length = 50)
    private String memberProviderId;

    @Column(length = 30)
    private String  memberCheckmail;

    // 팔로우 테이블
    @OneToMany(mappedBy = "memberId", cascade = CascadeType.ALL, orphanRemoval = true,  fetch = FetchType.LAZY)
    private List<FollowEntity> followEntityList = new ArrayList<>();

    // 판매자 권한 테이블
    @OneToMany(mappedBy = "memberId", cascade = CascadeType.ALL, orphanRemoval = true,  fetch = FetchType.LAZY)
    private List<SellerEntity> sellerEntityList = new ArrayList<>();

    // 보드 테이블
    @OneToMany(mappedBy = "memberId", cascade = CascadeType.ALL, orphanRemoval = true,  fetch = FetchType.LAZY)
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberId", cascade = CascadeType.ALL, orphanRemoval = true,  fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberId", cascade = CascadeType.ALL, orphanRemoval = true,  fetch = FetchType.LAZY)
    private List<LikeEntity> likeEntityList = new ArrayList<>();

    @Builder
    public MemberEntity(String memberEmail, String memberPw, String memberFilename, String memberRole, String memberProvider, String memberProviderId) {
        this.memberEmail = memberEmail;
        this.memberPw = memberPw;
        this.memberFilename = memberFilename;
        this.memberRole = memberRole;
        this.memberProvider = memberProvider;
        this.memberProviderId = memberProviderId;
    }


}
