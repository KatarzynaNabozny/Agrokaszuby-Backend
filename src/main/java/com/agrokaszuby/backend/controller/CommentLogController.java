package com.agrokaszuby.backend.controller;

import com.agrokaszuby.backend.domain.CommentLog;
import com.agrokaszuby.backend.domain.dto.CommentLogDTO;
import com.agrokaszuby.backend.exception.CommentLogNotFoundException;
import com.agrokaszuby.backend.mapper.CommentLogMapper;
import com.agrokaszuby.backend.service.CommentLogDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agrokaszuby/backend/comment_log")
public class CommentLogController {

    private final CommentLogDBService service;
    private final CommentLogMapper mapper;

    @GetMapping
    public ResponseEntity<List<CommentLogDTO>> getComments() {
        List<CommentLog> commentLogs = service.getAllCommentLogs();
        return ResponseEntity.ok(mapper.mapToCommentLogDtoList(commentLogs));
    }

    @GetMapping(value = "/{commentLogId}")
    public ResponseEntity<CommentLogDTO> getCommentLog(@PathVariable Long commentLogId) throws CommentLogNotFoundException {
        return ResponseEntity.ok(mapper.mapToCommentLogDTO(
                service.getCommentLog(commentLogId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCommentLog(@RequestBody CommentLogDTO commentLogDTO) {
        CommentLog comment = mapper.mapToCommentLog(commentLogDTO);
        service.saveCommentLog(comment);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{commentLogId}")
    public ResponseEntity<Void> deleteCommentLog(@PathVariable Long commentLogId) throws CommentLogNotFoundException {
        service.deleteCommentLog(commentLogId);
        return ResponseEntity.ok().build();
    }

}
