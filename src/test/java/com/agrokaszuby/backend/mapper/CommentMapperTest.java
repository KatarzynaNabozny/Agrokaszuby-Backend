package com.agrokaszuby.backend.mapper;

import com.agrokaszuby.backend.domain.Comment;
import com.agrokaszuby.backend.domain.dto.CommentDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CommentMapperTest {

    @InjectMocks
    private CommentMapper commentMapper;

    @Test
    void mapToComment() {
        //Given
        commentMapper = new CommentMapper();
        CommentDTO commentDto = CommentDTO.builder().email("email").build();
        //When
        Comment comment = commentMapper.mapToComment(commentDto);
        //Then
        assertEquals("email", comment.getEmail());

    }

    @Test
    void mapToCommentDTO() {
        //Given
        commentMapper = new CommentMapper();
        Comment comment = new Comment();
        //When
        CommentDTO commentDto = commentMapper.mapToCommentDTO(comment);
        //Then
        assertEquals(null, commentDto.getEmail());

    }

    @Test
    void mapToCommentDTOList() {
        //Given
        commentMapper = new CommentMapper();
        Comment comment = new Comment(10L, "fitstName", "email","", null,null);
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);
        //When
        List<CommentDTO> commentDto = commentMapper.mapToCommentDtoList(commentList);
        //Then
        assertEquals(10, commentDto.get(0).getCommentId());
    }
}
