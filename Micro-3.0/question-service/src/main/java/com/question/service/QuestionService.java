package com.question.service;

import com.question.entity.Question;

import java.util.List;

public interface QuestionService {
    Question createQuestion(Question question);

    List<Question> getAllQuestions();

    Question getQuestionById(Long questionId);

    List<Question> getQuestionByQuizId(Long quizId);
}
