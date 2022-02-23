package com.project.polaroid.service;

import com.project.polaroid.dto.CommentSaveDTO;
import com.project.polaroid.entity.CommentEntity;
import com.project.polaroid.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository cr;

    @Override
    public Long save(CommentSaveDTO commentSaveDTO) {
        CommentEntity commentEntity = CommentEntity.toCommentEntity(commentSaveDTO);
        Long commentId = cr.save(commentEntity).getId();
        return commentId;
    }
}
