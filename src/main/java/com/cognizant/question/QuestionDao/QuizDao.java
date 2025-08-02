package com.cognizant.question.QuestionDao;

import com.cognizant.question.model.Question;
import com.cognizant.question.model.Quiz;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizDao extends JpaRepository<Quiz,Integer> {

}
