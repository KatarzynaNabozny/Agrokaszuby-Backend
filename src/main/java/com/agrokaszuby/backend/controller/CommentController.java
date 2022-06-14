package com.agrokaszuby.backend.controller;

import com.agrokaszuby.backend.domain.Comment;
import com.agrokaszuby.backend.domain.dto.CommentDTO;
import com.agrokaszuby.backend.exception.CommentNotFoundException;
import com.agrokaszuby.backend.mapper.CommentMapper;
import com.agrokaszuby.backend.service.CommentDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agrokaszuby/backend/comment")
public class CommentController {

    private final CommentDBService service;
    private final CommentMapper mapper;

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getComments() {
        List<Comment> comments = service.getAllComments();
        return ResponseEntity.ok(mapper.mapToCommentDtoList(comments));
    }

    @GetMapping(value = "/search/id/{commentId}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable Long commentId) throws CommentNotFoundException {
        return ResponseEntity.ok(mapper.mapToCommentDTO(
                service.getComment(commentId)));
    }

    @GetMapping(value = "/search/email/{email}")
    public ResponseEntity<List<CommentDTO>> getCommentsByEmail(@PathVariable String email) {
        List<Comment> comments = service.getCommentByEmail(email);
        return ResponseEntity.ok(mapper.mapToCommentDtoList(comments));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = mapper.mapToComment(commentDTO);
        service.saveComment(comment);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO commentDto) {
        Comment comment = mapper.mapToComment(commentDto);
        Comment savedComment = service.saveComment(comment);
        return ResponseEntity.ok(mapper.mapToCommentDTO(savedComment));
    }

    @DeleteMapping(value = "/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) throws CommentNotFoundException {
        service.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/delete/subject_email")
    public ResponseEntity<Void> deleteCommentBySubjectAndEmail(
            @RequestParam(name = "subject") String subject,
            @RequestParam(name = "email") String email)
            throws CommentNotFoundException {
        service.deleteCommentBySubjectAndEmail(subject, email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/delete/subject")
    public ResponseEntity<Void> deleteCommentBySubject(
            @RequestParam(name = "subject") String subject)
            throws CommentNotFoundException {
        service.deleteCommentBySubject(subject);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/delete/email")
    public ResponseEntity<Void> deleteCommentByEmail(
            @RequestParam(name = "email") String email)
            throws CommentNotFoundException {
        service.deleteCommentByEmail(email);
        return ResponseEntity.ok().build();
    }
}
