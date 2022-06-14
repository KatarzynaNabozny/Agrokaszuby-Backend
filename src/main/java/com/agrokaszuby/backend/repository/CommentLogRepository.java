package com.agrokaszuby.backend.repository;

import com.agrokaszuby.backend.domain.CommentLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CommentLogRepository extends CrudRepository<CommentLog, Long> {

    @Override
    List<CommentLog> findAll();

    @Override
    Optional<CommentLog> findById(Long commentLogId);
    
    @Override
    CommentLog save(CommentLog commentLog);

    @Override
    void deleteById(Long commentLogId);
    

}