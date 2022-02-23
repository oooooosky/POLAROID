package com.project.polaroid.controller;

import com.project.polaroid.dto.CommentSaveDTO;
import com.project.polaroid.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment/*")
public class CommentController {

    private final CommentService cs;

    @GetMapping("save")
    public String save(@ModelAttribute CommentSaveDTO commentSaveDTO) {
        Long commentId = cs.save(commentSaveDTO);
        return null;
    }

}
