package com.project.polaroid.dto;

import com.project.polaroid.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDetailDTO {

    private Long boardId;
    private String boardWriter;
    private String boardContents;
    private int boardView;
    private int boardLike;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private List<PhotoDetailDTO> photo;
//    private List

    public static BoardDetailDTO toBoardDetailDTO(BoardEntity boardEntity) {
        BoardDetailDTO boardDetailDTO = new BoardDetailDTO();
        boardDetailDTO.setBoardId(boardEntity.getId());
        boardDetailDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDetailDTO.setBoardContents(boardEntity.getBoardContents());
        boardDetailDTO.setBoardView(boardEntity.getBoardView());
        boardDetailDTO.setBoardLike(boardEntity.getBoardLike());
        boardDetailDTO.setCreateTime(boardEntity.getCreateTime());
        boardDetailDTO.setUpdateTime(boardEntity.getUpdateTime());
        boardDetailDTO.setPhoto(PhotoDetailDTO.toPhotoDetailDTOList(boardEntity.getPhotoEntity()));
        return boardDetailDTO;
    }


    public static List<BoardDetailDTO> toBoardDetailDTOList(List<BoardEntity> boardEntityList) {
        List<BoardDetailDTO> boardDetailDTOList = new ArrayList<>();
        for (BoardEntity b: boardEntityList) {
            boardDetailDTOList.add(toBoardDetailDTO(b));
        }
        return boardDetailDTOList;
    }

}
