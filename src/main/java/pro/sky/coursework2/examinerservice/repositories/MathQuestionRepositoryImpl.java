package pro.sky.coursework2.examinerservice.repositories;

import org.springframework.stereotype.Repository;
import pro.sky.coursework2.examinerservice.domain.Question;
import pro.sky.coursework2.examinerservice.exceptions.QuestionNotFoundException;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MathQuestionRepositoryImpl implements QuestionRepository {
    private final Set<Question> questions;

    public MathQuestionRepositoryImpl() {
        this.questions = new HashSet<>();
    }

    @PostConstruct
    public void init() {
        add(new Question("2*1=", "2"));
        add(new Question("2*2=", "4"));
        add(new Question("2*3=", "6"));
        add(new Question("2*4=", "8"));
        add(new Question("2*5=", "10"));
        add(new Question("2*7=", "12"));
        add(new Question("2*8=", "14"));
        add(new Question("2*6=", "16"));
        add(new Question("2*9=", "18"));
        add(new Question("2*10=", "20"));
    }

    @Override
    public Question add(String question, String answer) {
        if (question.isEmpty() || answer.isEmpty()) {
            throw new NullPointerException();
        }
        Question newQuestion = new Question(question, answer);
        questions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
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
