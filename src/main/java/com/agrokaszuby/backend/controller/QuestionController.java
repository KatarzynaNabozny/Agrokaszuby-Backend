package com.agrokaszuby.backend.controller;

import com.agrokaszuby.backend.domain.Question;
import com.agrokaszuby.backend.domain.dto.QuestionDTO;
import com.agrokaszuby.backend.exception.QuestionNotFoundException;
import com.agrokaszuby.backend.mapper.QuestionMapper;
import com.agrokaszuby.backend.service.QuestionDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agrokaszuby/backend/question")
public class QuestionController {

    private final QuestionDBService service;
    private final QuestionMapper mapper;

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> getQuestions() {
        List<Question> questions = service.getAllQuestions();
        return ResponseEntity.ok(mapper.mapToQuestionDtoList(questions));
    }

    @GetMapping(value = "/search/id/{questionId}")
    public ResponseEntity<QuestionDTO> getQuestion(@PathVariable Long questionId) throws QuestionNotFoundException {
        return ResponseEntity.ok(mapper.mapToQuestionDTO(
                service.getQuestion(questionId)));
    }

    @GetMapping(value = "/search/email/{email}")
    public ResponseEntity<List<QuestionDTO>> getQuestionsByEmail(@PathVariable String email) {
        List<Question> questions = service.getQuestionByEmail(email);
        return ResponseEntity.ok(mapper.mapToQuestionDtoList(questions));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createQuestion(@RequestBody QuestionDTO questionDTO) {
        Question question = mapper.mapToQuestion(questionDTO);
        service.saveQuestion(question);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<QuestionDTO> updateQuestion(@RequestBody QuestionDTO questionDto) {
        Question question = mapper.mapToQuestion(questionDto);
        Question savedQuestion = service.saveQuestion(question);
        return ResponseEntity.ok(mapper.mapToQuestionDTO(savedQuestion));
    }

    @DeleteMapping(value = "/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) throws QuestionNotFoundException {
        service.deleteQuestion(questionId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/delete/subject_email")
    public ResponseEntity<Void> deleteQuestionBySubjectAndEmail(
            @RequestParam(name = "subject") String subject,
            @RequestParam(name = "email") String email)
            throws QuestionNotFoundException {
        service.deleteQuestionBySubjectAndEmail(subject, email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/delete/subject")
    public ResponseEntity<Void> deleteQuestionBySubject(
            @RequestParam(name = "subject") String subject)
            throws QuestionNotFoundException {
        service.deleteQuestionBySubject(subject);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/delete/email")
    public ResponseEntity<Void> deleteQuestionByEmail(
            @RequestParam(name = "email") String email)
            throws QuestionNotFoundException {
        service.deleteQuestionByEmail(email);
        return ResponseEntity.ok().build();
    }
}
