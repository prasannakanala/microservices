package com.cognizant.question.controller;

import com.cognizant.question.model.Question;
import com.cognizant.question.questionService.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("question")
public class QuestionController {
    QuestionService quizservice;
    @Autowired
    public QuestionController(QuestionService quizservice) {
        this.quizservice = quizservice;
    }

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>>getAllQuestions()
    {
        return quizservice.getAllQuestions();
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getByCategory(@PathVariable String category)
    {
        return quizservice.getByCategory(category);

    }
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question)
    {
        return quizservice.addQuestion(question);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id)
    {
        return quizservice.deleteQuestion(id);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable Integer id, @RequestBody Question question) {
        return quizservice.updateQuestion(id, question);
    }


}


