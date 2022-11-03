package pro.sky.coursework2.examinerservice.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.coursework2.examinerservice.domain.Question;
import pro.sky.coursework2.examinerservice.exceptions.QuestionNotFoundException;
import pro.sky.coursework2.examinerservice.repositories.QuestionRepository;

import java.util.*;

@Service
public class JavaQuestionServiceImpl implements QuestionService {

    private Random RANDOM = new Random();

    private final QuestionRepository questionsRepository;

    public JavaQuestionServiceImpl(@Qualifier("javaQuestionRepositoryImpl") QuestionRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    @Override
    public Question add(String question, String answer) {
        return questionsRepository.add(question, answer);
    }

    @Override
    public Question add(Question question) {
        return questionsRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return questionsRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return questionsRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        if (questionsRepository.getAll().isEmpty()) {
            throw new QuestionNotFoundException("Ни одного вопроса не найдено");
        }
        int numberOfQuestion = RANDOM.nextInt(questionsRepository.getAll().size());
        Question question = getAll().stream()
                .skip(numberOfQuestion)
                .filter(Objects::nonNull)
                .findFirst().orElseThrow();
        return question;
    }


}
