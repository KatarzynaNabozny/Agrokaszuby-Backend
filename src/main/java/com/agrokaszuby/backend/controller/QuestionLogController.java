package com.agrokaszuby.backend.controller;

import com.agrokaszuby.backend.domain.QuestionLog;
import com.agrokaszuby.backend.domain.dto.QuestionLogDTO;
import com.agrokaszuby.backend.exception.QuestionLogNotFoundException;
import com.agrokaszuby.backend.mapper.QuestionLogMapper;
import com.agrokaszuby.backend.service.QuestionLogDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agrokaszuby/backend/question_log")
public class QuestionLogController {

    private final QuestionLogDBService service;
    private final QuestionLogMapper mapper;

    @GetMapping
    public ResponseEntity<List<QuestionLogDTO>> getQuestions() {
        List<QuestionLog> questionLogs = service.getAllQuestionLogs();
        return ResponseEntity.ok(mapper.mapToQuestionLogDtoList(questionLogs));
    }

    @GetMapping(value = "/{questionLogId}")
    public ResponseEntity<QuestionLogDTO> getQuestionLog(@PathVariable Long questionLogId) throws QuestionLogNotFoundException {
        return ResponseEntity.ok(mapper.mapToQuestionLogDTO(
                service.getQuestionLog(questionLogId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createQuestionLog(@RequestBody QuestionLogDTO questionLogDTO) {
        QuestionLog question = mapper.mapToQuestionLog(questionLogDTO);
        service.saveQuestionLog(question);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{questionLogId}")
    public ResponseEntity<Void> deleteQuestionLog(@PathVariable Long questionLogId) throws QuestionLogNotFoundException {
        service.deleteQuestionLog(questionLogId);
        return ResponseEntity.ok().build();
    }

}
