package com.agrokaszuby.backend.service;

import com.agrokaszuby.backend.domain.QuestionLog;
import com.agrokaszuby.backend.exception.QuestionLogNotFoundException;
import com.agrokaszuby.backend.repository.QuestionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class QuestionLogDBService {

    private final QuestionLogRepository repository;

    public List<QuestionLog> getAllQuestionLogs() {
        return repository.findAll();
    }

    public QuestionLog getQuestionLog(final Long questionLogId) throws QuestionLogNotFoundException {
        return repository.findById(questionLogId).orElseThrow(QuestionLogNotFoundException::new);
    }

    public QuestionLog saveQuestionLog(final QuestionLog questionLog) {
        return repository.save(questionLog);
    }

    public void deleteQuestionLog(final Long questionLogId) throws QuestionLogNotFoundException {
        try {
            repository.deleteById(questionLogId);
        } catch (Exception e) {
            throw new QuestionLogNotFoundException();
        }
    }

}