package com.example.quizapp.controller;

import com.example.quizapp.model.Question;
import com.example.quizapp.model.UserSession;
import com.example.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/start")
    public ResponseEntity<UserSession> startQuiz(@RequestParam Long userId) {
        UserSession session = quizService.startNewSession(userId);
        return ResponseEntity.ok(session);
    }

    @GetMapping("/question")
    public ResponseEntity<Question> getRandomQuestion() {
        Optional<Question> question = quizService.getRandomQuestion();
        return question.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitAnswer(@RequestParam Long userId, @RequestParam Long questionId, @RequestParam String answer) {
        quizService.submitAnswer(userId, questionId, answer);
        return ResponseEntity.ok("Answer submitted successfully");
    }

    @GetMapping("/summary")
    public ResponseEntity<UserSession> getSessionSummary(@RequestParam Long userId) {
        UserSession session = quizService.getSessionSummary(userId);
        if (session == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(session);
    }
}