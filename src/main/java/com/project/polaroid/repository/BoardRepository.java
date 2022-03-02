package com.project.polaroid.repository;

import com.project.polaroid.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {


    Page<BoardEntity> findByBoardContentsContaining(String keyword, Pageable pageable);

    List<BoardEntity> findByBoardContents(String keyword);
}
