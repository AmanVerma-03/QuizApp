package com.example.quizapp.service;

import com.example.quizapp.model.Question;
import com.example.quizapp.model.UserSession;
import com.example.quizapp.repository.QuestionRepository;
import com.example.quizapp.repository.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuizService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserSessionRepository userSessionRepository;

    public UserSession startNewSession(Long userId) {
        UserSession session = userSessionRepository.findByUserId(userId);
        if (session == null) {
            session = new UserSession();
            session.setUserId(userId);
            session.setTotalQuestions(0);
            session.setCorrectAnswers(0);
            userSessionRepository.save(session);
        }
        return session;
    }

    public Optional<Question> getRandomQuestion() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            return Optional.empty();
        }
        Random random = new Random();
        return Optional.of(questions.get(random.nextInt(questions.size())));
    }

    public void submitAnswer(Long userId, Long questionId, String selectedAnswer) {
        UserSession session = userSessionRepository.findByUserId(userId);
        if (session == null) {
            throw new IllegalArgumentException("Session not found for user");
        }

        Optional<Question> questionOpt = questionRepository.findById(questionId);
        if (questionOpt.isPresent()) {
            Question question = questionOpt.get();
            session.setTotalQuestions(session.getTotalQuestions() + 1);
            if (question.getCorrectAnswer().equalsIgnoreCase(selectedAnswer)) {
                session.setCorrectAnswers(session.getCorrectAnswers() + 1);
            }
            userSessionRepository.save(session);
        } else {
            throw new IllegalArgumentException("Question not found");
        }
    }

    public UserSession getSessionSummary(Long userId) {
        return userSessionRepository.findByUserId(userId);
    }
}