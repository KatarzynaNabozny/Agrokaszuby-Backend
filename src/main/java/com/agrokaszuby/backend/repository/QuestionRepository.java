package com.agrokaszuby.backend.repository;

import com.agrokaszuby.backend.domain.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {

    @Override
    List<Question> findAll();

    @Override
    Optional<Question> findById(Long questionId);

    List<Question> findAllByEmail(String email);

    @Override
    Question save(Question question);

    @Override
    void deleteById(Long questionId);

    void deleteQuestionBySubjectAndEmail(String subject, String email);

    void deleteQuestionBySubject(String subject);

    void deleteQuestionByEmail(String email);

}