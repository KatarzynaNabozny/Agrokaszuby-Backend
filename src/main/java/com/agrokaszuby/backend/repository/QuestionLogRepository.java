package com.agrokaszuby.backend.repository;

import com.agrokaszuby.backend.domain.QuestionLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface QuestionLogRepository extends CrudRepository<QuestionLog, Long> {

    @Override
    List<QuestionLog> findAll();

    @Override
    Optional<QuestionLog> findById(Long questionLogId);

    @Override
    QuestionLog save(QuestionLog questionLog);

    @Override
    void deleteById(Long questionLogId);

}