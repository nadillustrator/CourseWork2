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
        int size = javaQuestionService.getAll().size() + mathQuestionService.getAll().size();
        if (amount <= 0 || amount > size) {
            throw new IncorrectNumberOfRequestedQuestionsException("Некорректное число вопросов");
        }

        List<Question> allQuestions = new ArrayList<>();
        allQuestions.addAll(javaQuestionService.getAll());
        allQuestions.addAll(mathQuestionService.getAll());

        Set<Question> result = new HashSet<>();
        while (result.size() < amount) {
            result.add(allQuestions.get(random.nextInt(allQuestions.size())));
        }
        return result;
    }
}
