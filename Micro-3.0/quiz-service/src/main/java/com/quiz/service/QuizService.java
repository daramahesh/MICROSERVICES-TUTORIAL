package com.quiz.service;

import com.quiz.entity.Quiz;

import java.util.List;

public interface QuizService {
    Quiz createQuiz(Quiz quiz);

    List<Quiz> getAllQuiz();

    Quiz getQuizById(Long quizId);
}
