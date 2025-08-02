package com.cognizant.question.questionService;

import com.cognizant.question.QuestionDao.QuestionDao;
import com.cognizant.question.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

     public ResponseEntity<List<Question>> getAllQuestions()
    {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();;
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Question>> getByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();;
        }
        return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.BAD_REQUEST);
        }

    public ResponseEntity<String> addQuestion(Question question)
    {
        questionDao.save(question);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }
    public ResponseEntity<String> deleteQuestion(Integer id)
    {
        questionDao.deleteById(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }
    public ResponseEntity<String> updateQuestion(Integer id, Question updatedQuestion) {
        if (questionDao.existsById(id)) {
            Question existingQuestion = questionDao.findById(id).get();
            existingQuestion.setQuestionTitle(updatedQuestion.getQuestionTitle());
            existingQuestion.setOption1(updatedQuestion.getOption1());
            existingQuestion.setOption2(updatedQuestion.getOption2());
            existingQuestion.setOption3(updatedQuestion.getOption3());
            existingQuestion.setOption4(updatedQuestion.getOption4());
            existingQuestion.setRightAnswer(updatedQuestion.getRightAnswer());
            existingQuestion.setDifficultylevel(updatedQuestion.getDifficultylevel());
            existingQuestion.setCategory(updatedQuestion.getCategory());

            questionDao.save(existingQuestion);
            return new ResponseEntity<>("Question updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
        }
    }


}
