package pro.sky.coursework2.examinerservice.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public MathQuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping(path = "/add")
    public ResponseEntity<Question> add(@RequestParam String question,
                                        @RequestParam String answer) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    @GetMapping(path = "/remove")
    public ResponseEntity<Question> remove(@RequestParam String question,
                                           @RequestParam String answer) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    @GetMapping()
    public ResponseEntity<Collection<Question>> getQuestions() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }
}
