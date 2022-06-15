package com.agrokaszuby.backend.mapper;

import com.agrokaszuby.backend.domain.CommentLog;
import com.agrokaszuby.backend.domain.dto.CommentLogDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CommentLogMapperTest {

    @InjectMocks
    private CommentLogMapper commentLogMapper;

    @Test
    void mapToCommentLog() {
        //Given
        commentLogMapper = new CommentLogMapper();
        CommentLogDTO commentLogDto = CommentLogDTO.builder().email("email").build();
        //When
        CommentLog commentLog = commentLogMapper.mapToCommentLog(commentLogDto);
        //Then
        assertEquals("email", commentLog.getEmail());

    }

    @Test
    void mapToCommentLogDTO() {
        //Given
        commentLogMapper = new CommentLogMapper();
        CommentLog commentLog = new CommentLog();
        //When
        CommentLogDTO commentLogDto = commentLogMapper.mapToCommentLogDTO(commentLog);
        //Then
        assertEquals(null, commentLogDto.getEmail());

    }

    @Test
    void mapToCommentLogDTOList() {
        //Given
        commentLogMapper = new CommentLogMapper();
        CommentLog commentLog = new CommentLog(10L, "email", null, Boolean.TRUE, null);
        List<CommentLog> commentLogList = new ArrayList<>();
        commentLogList.add(commentLog);
        //When
        List<CommentLogDTO> commentLogDto = commentLogMapper.mapToCommentLogDtoList(commentLogList);
        //Then
        assertEquals(10, commentLogDto.get(0).getCommentLogId());
    }
}
