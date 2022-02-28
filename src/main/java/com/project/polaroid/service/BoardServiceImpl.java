package com.project.polaroid.service;

import com.project.polaroid.dto.BoardDetailDTO;
import com.project.polaroid.dto.BoardPagingDTO;
import com.project.polaroid.dto.BoardSaveDTO;
import com.project.polaroid.dto.PhotoDetailDTO;
import com.project.polaroid.entity.BoardEntity;
import com.project.polaroid.entity.PhotoEntity;
import com.project.polaroid.page.PagingConst;
import com.project.polaroid.repository.BoardRepository;
import com.project.polaroid.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository br;
    private final PhotoRepository pr;

    @Override
    public List<BoardDetailDTO> findAll() {
        List<BoardEntity> boardEntityList = br.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<BoardDetailDTO> boardDetailDTOList = BoardDetailDTO.toBoardDetailDTOList(boardEntityList);
        return boardDetailDTOList;
    }

    @Override
    public Long save(BoardSaveDTO boardSaveDTO) {
        BoardEntity boardEntity = BoardEntity.toBoardEntity(boardSaveDTO);
        Long boardId = br.save(boardEntity).getId();
        return boardId;
    }

    @Override
    public void saveFile(Long boardId, MultipartFile boardFile) throws IOException {
        String boardFilename = boardFile.getOriginalFilename();
        boardFilename = System.currentTimeMillis() + "-" + boardFilename;
        String savePath = "/Users/sky/EclipseJava/source/SpringBoot/Polaroid/src/main/resources/static/upload/" + boardFilename;
        if (!boardFile.isEmpty()) {
            boardFile.transferTo(new File(savePath));
        }
        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.setBoardId(br.findById(boardId).get());
        photoEntity.setBoardFilename(boardFilename);
        pr.save(photoEntity);
    }

    @Override
    public BoardDetailDTO findById(Long boardId) {
        return BoardDetailDTO.toBoardDetailDTO(br.findById(boardId).get());
    }

    @Override
    public Page<BoardPagingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();
        page = (page == 1) ? 0 : (page - 1);
        Page<BoardEntity> boardEntities = br.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        Page<BoardPagingDTO> boardList = boardEntities.map(
                board -> new BoardPagingDTO(
                        board.getId(),
                        board.getBoardWriter(),
                        board.getBoardContents(),
                        PhotoDetailDTO.toPhotoDetailDTOList(board.getPhotoEntity()))
        );
        return boardList;
    }

}
