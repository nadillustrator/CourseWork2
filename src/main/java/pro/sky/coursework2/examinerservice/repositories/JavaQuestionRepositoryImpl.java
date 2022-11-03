package pro.sky.coursework2.examinerservice.repositories;

import org.springframework.stereotype.Repository;
import pro.sky.coursework2.examinerservice.domain.Question;
import pro.sky.coursework2.examinerservice.exceptions.QuestionNotFoundException;
import pro.sky.coursework2.examinerservice.exceptions.SuchQuestionAlreadyExistException;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class JavaQuestionRepositoryImpl implements QuestionRepository {

    private final Set<Question> questions;

    public JavaQuestionRepositoryImpl() {
        this.questions = new HashSet<>();
    }

    @PostConstruct
    public void init() {
        add(new Question("Вопрос 1?", "Ответ 1."));
        add(new Question("Вопрос 2?", "Ответ 2."));
        add(new Question("Вопрос 3?", "Ответ 3."));
        add(new Question("Вопрос 4?", "Ответ 4."));
        add(new Question("Вопрос 5?", "Ответ 5."));
        add(new Question("Вопрос 6?", "Ответ 6."));
        add(new Question("Вопрос 7?", "Ответ 7."));
        add(new Question("Вопрос 8?", "Ответ 8."));
        add(new Question("Вопрос 9?", "Ответ 9."));
        add(new Question("Вопрос 10?", "Ответ 10."));
    }

    @Override
    public Question add(String question, String answer) {
        if (question.isEmpty() || answer.isEmpty()) {
            throw new NullPointerException();
        }
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (questions.contains(question)) {
            throw new SuchQuestionAlreadyExistException("Вопрос " + question + " уже добавлен.");
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (questions.contains(question)) {
            questions.remove(question);
            return question;
        }
        throw new QuestionNotFoundException("Вопрос " + question.getQuestion() + " не найден.");
    }

    @Override
    public Collection<Question> getAll() {
        return new HashSet<>(questions);
    }

}
