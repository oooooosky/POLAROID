package com.project.polaroid.entity;

import com.project.polaroid.dto.BoardSaveDTO;
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

    @Column
    private String boardWriter;

    @Column(length = 2000)
    private String boardContents;

    @Column
    private int boardView;

    @Column
    private int boardLike;

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PhotoEntity> photoEntity = new ArrayList<>();

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntity = new ArrayList<>();

    @OneToOne(mappedBy = "boardEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private GoodsEntity goodsEntity;

    public static BoardEntity toBoardEntity(BoardSaveDTO boardSaveDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardSaveDTO.getBoardWriter());
        boardEntity.setBoardContents(boardSaveDTO.getBoardContents());
        return boardEntity;
    }
}
