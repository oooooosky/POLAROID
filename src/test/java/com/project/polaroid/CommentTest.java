package com.project.polaroid;

import com.project.polaroid.dto.CommentSaveDTO;
import com.project.polaroid.service.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CommentTest {

    @Autowired
    private CommentService cs;

//    @Test
//    @Transactional
//    @Rollback
//    @DisplayName("댓글작성 테스트")
//    public void commentTest() {
////        Long boardId = bs.save(new BoardSaveDTO("테스트작성자", "테스트비밀번호", "테스트제목", "테스트내용"));
////        Long commentId = cs.save(new CommentSaveDTO(boardId, "댓글작성자", "댓글내용"));
////        assertThat(commentId).isNotNull();
////        assertThat(cs.save(new CommentSaveDTO(bs.save(new BoardSaveDTO("테스트작성자", "테스트비밀번호", "테스트제목", "테스트내용")), "댓글작성자", "댓글내용"))).isNotNull()
//        Long commentId = cs.save(new CommentSaveDTO(1L, "댓글작성자", "댓글내용"));
//        assertThat(commentId).isNotNull();
//    }

}
