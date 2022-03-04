package com.project.polaroid.entity;

import com.project.polaroid.dto.BoardSaveDTO;
import com.project.polaroid.dto.BoardUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(length = 2000)
    private String boardContents;

    @OneToMany(mappedBy = "boardId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PhotoEntity> photoEntity = new ArrayList<>();

    @OneToMany(mappedBy = "boardId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "boardId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LikeEntity> likeEntityList = new ArrayList<>();

    @OneToOne(mappedBy = "boardEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private GoodsEntity goodsEntity;

    @ManyToOne(fetch = FetchType.LAZY) //LAZY 부하 ↓
    @JoinColumn(name = "member_id") // 부모테이블의 pk 컬럼이름
    private MemberEntity memberId;

    public static BoardEntity toBoardEntity(BoardSaveDTO boardSaveDTO,MemberEntity memberEntity) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setMemberId(memberEntity);
        boardEntity.setBoardContents(boardSaveDTO.getBoardContents());
        return boardEntity;
    }

    public static BoardEntity toUpdateBoardEntity(BoardUpdateDTO boardUpdateDTO, MemberEntity memberEntity) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardUpdateDTO.getBoardId());
        boardEntity.setMemberId(memberEntity);
        boardEntity.setBoardContents(boardUpdateDTO.getBoardContents());
        return boardEntity;
    }
}
