package pro.sky.coursework2.examinerservice.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.coursework2.examinerservice.domain.Question;
import pro.sky.coursework2.examinerservice.exceptions.QuestionNotFoundException;
import pro.sky.coursework2.examinerservice.repositories.QuestionRepository;

import java.util.*;

@Service
public class MathQuestionServiceImpl {

    private Random random = new Random();
    private char[] signs = {'+', '-', '*', '/'};

    public Question getRandomQuestion() {
        int a = random.nextInt(100);
        int b = random.nextInt(100);
        int result;
        int signNum = random.nextInt(signs.length);
        if (b == 0 && signNum == 3) {
            signNum = 1;
        }

        switch (signNum) {
            case 0:
                result = a + b;
                break;
            case 1:
                result = a - b;
                break;
            case 2:
                result = a * b;
                break;
            case 3:
                result = a / b;
                break;
            default:
                result = 0;
        }

        Question question = new Question("" + a + signs[signNum] + b, " =" + result);
        return question;
    }

}
