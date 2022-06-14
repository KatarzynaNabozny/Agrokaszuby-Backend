package com.agrokaszuby.backend.service;

import com.agrokaszuby.backend.domain.Comment;
import com.agrokaszuby.backend.exception.CommentNotFoundException;
import com.agrokaszuby.backend.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class CommentDBService {

    private final CommentRepository repository;

    public List<Comment> getAllComments() {
        return repository.findAll();
    }

    public Comment getComment(final Long commentId) throws CommentNotFoundException {
        return repository.findById(commentId).orElseThrow(CommentNotFoundException::new);
    }

    public List<Comment> getCommentByEmail(final String email) {
        return repository.findAllByEmail(email);
    }

    public Comment saveComment(final Comment comment) {
        return repository.save(comment);
    }

    public void deleteComment(final Long commentId) throws CommentNotFoundException {
        try {
            repository.deleteById(commentId);
        } catch (Exception e) {
            throw new CommentNotFoundException();
        }
    }

    public void deleteCommentBySubjectAndEmail(final String subject, final String email) throws CommentNotFoundException {
        try {
            repository.deleteCommentBySubjectAndEmail(subject, email);
        } catch (Exception e) {
            throw new CommentNotFoundException();
        }
    }

    public void deleteCommentBySubject(final String subject) throws CommentNotFoundException {
        try {
            repository.deleteCommentBySubject(subject);
        } catch (Exception e) {
            throw new CommentNotFoundException();
        }
    }

    public void deleteCommentByEmail(final String email) throws CommentNotFoundException {
        try {
            repository.deleteCommentByEmail(email);
        } catch (Exception e) {
            throw new CommentNotFoundException();
        }
    }

}