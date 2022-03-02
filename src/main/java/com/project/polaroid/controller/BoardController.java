package com.project.polaroid.controller;

import com.project.polaroid.dto.BoardDetailDTO;
import com.project.polaroid.dto.BoardPagingDTO;
import com.project.polaroid.dto.BoardSaveDTO;
import com.project.polaroid.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {

    private final BoardService bs;

    @GetMapping
    public String main(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<BoardPagingDTO> boardList = bs.paging(pageable);
        model.addAttribute("boardList", boardList);

        System.out.println("boardList.getContent() = " + boardList.getContent()); // 요청 페이지에 들어있는 데이터, toString이 없기 때문에 주소값이 출력
        System.out.println("boardList.getTotalElements() = " + boardList.getTotalElements()); // 전체 글 갯수
        System.out.println("boardList.getNumber() = " + boardList.getNumber()); // JPA 기준 요청 페이지
        System.out.println("boardList.getTotalPages() = " + boardList.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardList.getSize() = " + boardList.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("boardList.hasPrevious() = " + boardList.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("boardList.isFirst() = " + boardList.isFirst()); // 첫 페이지인지 여부
        System.out.println("boardList.isLast() = " + boardList.isLast()); // 마지막 페이지인지 여부

//        Long boardId = 1000000000000L;
//
//        for(BoardPagingDTO b: boardList) {
//            if(b.getBoardId() <= boardId) {
//                boardId = b.getBoardId();
//            }
//        }
//
//        System.out.println("boardId = " + boardId);
//
//        model.addAttribute("boardId", boardId);

        return "board/main";
    }

    @GetMapping("save")
    public String saveForm() {
        return "board/save";
    }

    @PostMapping("save")
    public String save(@ModelAttribute BoardSaveDTO boardSaveDTO) throws IOException {
        System.out.println("boardSaveDTO = " + boardSaveDTO);
        Long boardId = bs.save(boardSaveDTO);
        for (MultipartFile b: boardSaveDTO.getBoardFile()) {
            bs.saveFile(boardId, b);
        }
        return "redirect:/board/";
    }

    @GetMapping("{boardId}")
    public String findById(@PathVariable Long boardId, Model model) {
        System.out.println("boardId = " + boardId);
        BoardDetailDTO boardDetailDTO = bs.findById(boardId);
        System.out.println("boardDetailDTO.getPhoto() = " + boardDetailDTO.getPhoto());
        model.addAttribute("board", boardDetailDTO);
        model.addAttribute("imageSize", boardDetailDTO.getPhoto().size());
        System.out.println("boardDetailDTO.getPhoto().size() = " + boardDetailDTO.getPhoto().size());
        return "board/findById";
    }

    @GetMapping("search/{keyword}")
    public String search(@PathVariable String keyword, @PageableDefault(page =1) Pageable pageable, Model model) {
        keyword = "#"+keyword;
        System.out.println("keyword = " + keyword);
        Page<BoardPagingDTO> boardList = bs.search(keyword, pageable);
        model.addAttribute("keyword", keyword);

        model.addAttribute("boardList", boardList);

        System.out.println("boardList.getContent() = " + boardList.getContent()); // 요청 페이지에 들어있는 데이터, toString이 없기 때문에 주소값이 출력
        System.out.println("boardList.getTotalElements() = " + boardList.getTotalElements()); // 전체 글 갯수
        System.out.println("boardList.getNumber() = " + boardList.getNumber()); // JPA 기준 요청 페이지
        System.out.println("boardList.getTotalPages() = " + boardList.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardList.getSize() = " + boardList.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("boardList.hasPrevious() = " + boardList.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("boardList.isFirst() = " + boardList.isFirst()); // 첫 페이지인지 여부
        System.out.println("boardList.isLast() = " + boardList.isLast()); // 마지막 페이지인지 여부

        return "board/search";
    }

//    @PostMapping("tag")
//    public ResponseEntity tag(@RequestParam String keyword) {
//        System.out.println("keyword = " + keyword);
//        List<BoardDetailDTO> boardDetailDTOList = bs.findByTag(keyword);
//        if (boardDetailDTOList.size() != 0) {
//            return new ResponseEntity(HttpStatus.OK);
//        } else {
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//    }
    

}
