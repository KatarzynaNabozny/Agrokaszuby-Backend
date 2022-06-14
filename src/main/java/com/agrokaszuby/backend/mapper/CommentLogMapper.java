package com.agrokaszuby.backend.mapper;

import com.agrokaszuby.backend.domain.CommentLog;
import com.agrokaszuby.backend.domain.dto.CommentLogDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentLogMapper {

    public CommentLog mapToCommentLog(final CommentLogDTO commentLogDTO) {
        return new CommentLog(
                commentLogDTO.getCommentLogId(),
                commentLogDTO.getEmail(),
                commentLogDTO.getEvent(),
                commentLogDTO.getSuccessful(),
                commentLogDTO.getDate()
        );
    }

    public CommentLogDTO mapToCommentLogDTO(final CommentLog commentLog) {
        return new CommentLogDTO(
                commentLog.getCommentLogId(),
                commentLog.getEmail(),
                commentLog.getEvent(),
                commentLog.getSuccessful(),
                commentLog.getDate()
        );
    }

    public List<CommentLogDTO> mapToCommentLogDtoList(final List<CommentLog> commentLogList) {
        return commentLogList.stream()
                .map(this::mapToCommentLogDTO)
                .collect(Collectors.toList());
    }
}