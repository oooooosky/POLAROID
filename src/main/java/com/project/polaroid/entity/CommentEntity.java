package com.project.polaroid.entity;

import com.project.polaroid.dto.CommentSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "comment_table")
public class CommentEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column
    private String commentContents;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardEntity boardId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity memberId;

    public static CommentEntity toCommentEntity(CommentSaveDTO commentSaveDTO) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentContents(commentSaveDTO.getCommentContents());
        return commentEntity;
    }
}
