package com.agrokaszuby.backend.service;

import com.agrokaszuby.backend.domain.CommentLog;
import com.agrokaszuby.backend.exception.CommentLogNotFoundException;
import com.agrokaszuby.backend.repository.CommentLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class CommentLogDBService {

    private final CommentLogRepository repository;

    public List<CommentLog> getAllCommentLogs() {
        return repository.findAll();
    }

    public CommentLog getCommentLog(final Long commentLogId) throws CommentLogNotFoundException {
        return repository.findById(commentLogId).orElseThrow(CommentLogNotFoundException::new);
    }
    
    public CommentLog saveCommentLog(final CommentLog commentLog) {
        return repository.save(commentLog);
    }

    public void deleteCommentLog(final Long commentLogId) throws CommentLogNotFoundException {
        try {
            repository.deleteById(commentLogId);
        } catch (Exception e) {
            throw new CommentLogNotFoundException();
        }
    }

}