package com.project.polaroid.entity;

import com.project.polaroid.dto.MemberUpdateDTO;
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
    @Column (name = "member_id")
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

    @Column
    private int memberMessage;

    @Column
    private int memberFollow;

    // 팔로우 테이블
    @OneToMany(mappedBy = "followMy", cascade = CascadeType.ALL, orphanRemoval = true,  fetch = FetchType.LAZY)
    private List<FollowEntity> memberFollowMy = new ArrayList<>();

    @OneToMany(mappedBy = "followYour", cascade = CascadeType.ALL, orphanRemoval = true,  fetch = FetchType.LAZY)
    private List<FollowEntity> memberFollowYour = new ArrayList<>();

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

    // 굿즈 테이블
    @OneToMany(mappedBy = "goodsWriter", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<GoodsEntity> goodsEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<GoodsCommentEntity> goodsCommentEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<GoodsLikeEntity> goodsLikeEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PayEntity> payEntityList = new ArrayList<>();

    @Builder
    public MemberEntity(String memberEmail, String memberPw, String memberFilename, String memberRole, String memberProvider, String memberProviderId, String memberCheckmail) {
        this.memberEmail = memberEmail;
        this.memberPw = memberPw;
        this.memberFilename = memberFilename;
        this.memberRole = memberRole;
        this.memberProvider = memberProvider;
        this.memberProviderId = memberProviderId;
        this.memberCheckmail = memberCheckmail;
    }

    public static MemberEntity UpdateDTOtoEntity(MemberUpdateDTO memberUpdateDTO){
        MemberEntity member=new MemberEntity();
        member.setMemberFilename(memberUpdateDTO.getMemberFilename());
        member.setMemberAddress(memberUpdateDTO.getMemberAddress());
        member.setMemberNickname(memberUpdateDTO.getMemberNickname());
        member.setMemberPhone(memberUpdateDTO.getMemberPhone());
        return member;
    }


}
