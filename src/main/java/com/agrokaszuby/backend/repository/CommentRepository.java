package com.agrokaszuby.backend.repository;

import com.agrokaszuby.backend.domain.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    @Override
    List<Comment> findAll();

    @Override
    Optional<Comment> findById(Long commentId);

    List<Comment> findAllByEmail(String email);

    @Override
    Comment save(Comment comment);

    @Override
    void deleteById(Long commentId);

    void deleteCommentBySubjectAndEmail(String subject, String email);

    void deleteCommentBySubject(String subject);

    void deleteCommentByEmail(String email);

}