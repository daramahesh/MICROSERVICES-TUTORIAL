package com.jonsnow.service.impl;

import com.jonsnow.entities.Question;
import com.jonsnow.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl {
    @Autowired
    private QuestionRepository repository;
    public void addQuestion(Question question) {
        this.repository.save(question);
    }

    public List<Question> getAllQuestions() {
        return this.repository.findAll();
    }

    public List<Question> getAllQuestionsByCategory(String category) {
        return this.repository.findByCategory(category);
    }
}
