package com.agrokaszuby.backend.mapper;

import com.agrokaszuby.backend.domain.Comment;
import com.agrokaszuby.backend.domain.dto.CommentDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentMapper {

    public Comment mapToComment(final CommentDTO commentDTO) {
        return new Comment(
                commentDTO.getCommentId(),
                commentDTO.getFromName(),
                commentDTO.getEmail(),
                commentDTO.getContent(),
                commentDTO.getSubject(),
                commentDTO.getDate()
        );
    }

    public CommentDTO mapToCommentDTO(final Comment comment) {
        return new CommentDTO(
                comment.getCommentId(),
                comment.getFromName(),
                comment.getEmail(),
                comment.getContent(),
                comment.getSubject(),
                comment.getDate()
        );
    }

    public List<CommentDTO> mapToCommentDtoList(final List<Comment> commentList) {
        return commentList.stream()
                .map(this::mapToCommentDTO)
                .collect(Collectors.toList());
    }
}