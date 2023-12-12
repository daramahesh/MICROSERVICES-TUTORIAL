package com.quiz.service.impl;

import com.quiz.entity.Quiz;
import com.quiz.repo.QuizRepo;
import com.quiz.service.QuestionClient;
import com.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuestionClient questionClient;

    @Override
    public Quiz createQuiz(Quiz quiz) {
        return quizRepo.save(quiz);
    }

    @Override
    public List<Quiz> getAllQuiz() {
        final List<Quiz> quizzes = quizRepo.findAll();
        return quizzes.stream().peek(quiz -> quiz.setQuestions(questionClient.getQuestionByQuizId(quiz.getQuizId()))).collect(Collectors.toList());
    }

    @Override
    public Quiz getQuizById(Long quizId) {
        final Quiz quiz = quizRepo.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found !!!"));
        quiz.setQuestions(questionClient.getQuestionByQuizId(quiz.getQuizId()));
        return quiz;
    }
}
