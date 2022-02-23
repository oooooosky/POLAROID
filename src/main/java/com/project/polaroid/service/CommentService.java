package com.project.polaroid.service;

import com.project.polaroid.dto.CommentSaveDTO;

public interface CommentService {
    Long save(CommentSaveDTO commentSaveDTO);
}
