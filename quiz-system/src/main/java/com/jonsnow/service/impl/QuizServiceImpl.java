package com.jonsnow.service.impl;

import com.jonsnow.dto.QuestionWrapper;
import com.jonsnow.dto.QuizResponse;
import com.jonsnow.entities.Question;
import com.jonsnow.entities.Quiz;
import com.jonsnow.repository.QuestionRepository;
import com.jonsnow.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl {
    @Autowired
    private QuizRepository quizrepository;
    @Autowired
    private QuestionRepository questionRepository;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = this.questionRepository.findRandomByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        this.quizrepository.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Quiz quiz = quizrepository.findById(id).orElseThrow(()->new RuntimeException());
        List<Question> questions = quiz.getQuestions();
        List<QuestionWrapper> wrapperList = new ArrayList<>();
        for(Question q: questions) {
            QuestionWrapper questionWrapper = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            wrapperList.add(questionWrapper);
        }
        return new ResponseEntity<>(wrapperList,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScore(int id, List<QuizResponse> responses) {
        Quiz quiz = this.quizrepository.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;
                int i =0;
        for(QuizResponse response:responses) {

            if(response.getResponse().equals(questions.get(i).getRightAnswer())) {
                right+=5;
            }
            if(!response.getResponse().equals(questions.get(i).getRightAnswer())) {
                right--;
            }
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
