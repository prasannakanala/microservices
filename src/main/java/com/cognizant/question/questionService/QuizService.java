package com.cognizant.question.questionService;

import com.cognizant.question.QuestionDao.QuestionDao;
import com.cognizant.question.QuestionDao.QuizDao;
import com.cognizant.question.model.Question;
import com.cognizant.question.model.QuestionWrapper;
import com.cognizant.question.model.Quiz;
import com.cognizant.question.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category,int numQ,String title)
    {
        List<Question> questions=questionDao.findRandomQuestionsByCategory(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions); // make sure 'questions' is not null or empty
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quizOptional = quizDao.findById(id);
        if (quizOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Quiz quiz = quizOptional.get();
        List<QuestionWrapper> wrappers = new ArrayList<>();
        for (Question q : quiz.getQuestions()) {
            wrappers.add(new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4()));
        }
        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> questions =quiz.getQuestions();
int right=0;
int i=0;
        for(Response response:responses)
        {
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;
                i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
