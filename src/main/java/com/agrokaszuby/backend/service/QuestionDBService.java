package com.agrokaszuby.backend.service;

import com.agrokaszuby.backend.domain.Question;
import com.agrokaszuby.backend.exception.QuestionNotFoundException;
import com.agrokaszuby.backend.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class QuestionDBService {

    private final QuestionRepository repository;

    public List<Question> getAllQuestions() {
        return repository.findAll();
    }

    public Question getQuestion(final Long questionId) throws QuestionNotFoundException {
        return repository.findById(questionId).orElseThrow(QuestionNotFoundException::new);
    }

    public List<Question> getQuestionByEmail(final String email) {
        return repository.findAllByEmail(email);
    }

    public Question saveQuestion(final Question question) {
        return repository.save(question);
    }

    public void deleteQuestion(final Long questionId) throws QuestionNotFoundException {
        try {
            repository.deleteById(questionId);
        } catch (Exception e) {
            throw new QuestionNotFoundException();
        }
    }

    public void deleteQuestionBySubjectAndEmail(final String subject, final String email) throws QuestionNotFoundException {
        try {
            repository.deleteQuestionBySubjectAndEmail(subject, email);
        } catch (Exception e) {
            throw new QuestionNotFoundException();
        }
    }

    public void deleteQuestionBySubject(final String subject) throws QuestionNotFoundException {
        try {
            repository.deleteQuestionBySubject(subject);
        } catch (Exception e) {
            throw new QuestionNotFoundException();
        }
    }

    public void deleteQuestionByEmail(final String email) throws QuestionNotFoundException {
        try {
            repository.deleteQuestionByEmail(email);
        } catch (Exception e) {
            throw new QuestionNotFoundException();
        }
    }

}