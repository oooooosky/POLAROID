package com.project.polaroid.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="follow_table")
public class FollowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "follow_id")
    private Long followId;

    @Column (name = "follow_following")
    private Long followFollowing;

    @ManyToOne(fetch = FetchType.LAZY) //LAZY 부하 ↓
    @JoinColumn(name = "member_id") // 부모테이블의 pk 컬럼이름
    private MemberEntity memberId;
}
