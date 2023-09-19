package com.jonsnow.controller;

import com.jonsnow.entities.Question;
import com.jonsnow.service.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/question")
public class QuestionController {
    @Autowired
    private QuestionServiceImpl questionService;

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        questionService.addQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).body("created successfully");
    }

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> allQuestions = this.questionService.getAllQuestions();
        return ResponseEntity.status(HttpStatus.OK).body(allQuestions);
    }
    @GetMapping("/allQuestions/{category}")
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable String category) {
        List<Question> allQuestions = this.questionService.getAllQuestionsByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(allQuestions);
    }
}
