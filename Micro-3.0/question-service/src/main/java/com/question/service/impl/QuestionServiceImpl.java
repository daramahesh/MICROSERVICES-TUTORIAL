package com.question.service.impl;

import com.question.entity.Question;
import com.question.repo.QuestionRepo;
import com.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    @Override
    public Question createQuestion(Question question) {
        return questionRepo.save(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    @Override
    public Question getQuestionById(Long questionId) {
        return questionRepo.findById(questionId).orElseThrow(() -> new RuntimeException("Question not found !!!"));
    }

    @Override
    public List<Question> getQuestionByQuizId(Long quizId) {
        return questionRepo.findByQuizId(quizId);
    }
}
