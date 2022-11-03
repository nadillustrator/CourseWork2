package pro.sky.coursework2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.sky.coursework2.examinerservice.domain.Question;
import pro.sky.coursework2.examinerservice.exceptions.IncorrectNumberOfRequestedQuestionsException;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final Random random;
    private final JavaQuestionServiceImpl javaQuestionService;
    private final MathQuestionServiceImpl mathQuestionService;

    public ExaminerServiceImpl(JavaQuestionServiceImpl javaQuestionService,
                               MathQuestionServiceImpl mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
        this.random = new Random();
    }


    @Override
    public Collection<Question> getQuestions(int amount) {
        int amountOfJavaQuestions;
        if (javaQuestionService.getAll().size() > 0) {
            amountOfJavaQuestions = amount / 2;
        } else {
            amountOfJavaQuestions = 0;
        }

        Set<Question> result = new HashSet<>();
        while (result.size() < amountOfJavaQuestions) {
            result.add(javaQuestionService.getRandomQuestion());
        }
        while (result.size() < amount) {
            result.add(mathQuestionService.getRandomQuestion());
        }
        return result;
    }
}
