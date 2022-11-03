package pro.sky.coursework2.examinerservice.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.coursework2.examinerservice.domain.Question;
import pro.sky.coursework2.examinerservice.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping(path = "/math")
public class MathQuestionController {
    private final QuestionService service;

    public MathQuestionController(@Qualifier("mathQuestionServiceImpl") QuestionService service) {
        this.service = service;
    }

    @GetMapping(path = "/add")
    public Question add(@RequestParam String question,
                        @RequestParam String answer) {
        return service.add(question, answer);
    }

    @GetMapping(path = "/remove")
    public Question remove(@RequestParam String question,
                           @RequestParam String answer) {
        return service.remove(new Question(question, answer));
    }

    @GetMapping()
    public Collection<Question> getQuestions() {
        return service.getAll();
    }
}
