package pro.sky.coursework2.examinerservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.coursework2.examinerservice.domain.Question;
import pro.sky.coursework2.examinerservice.exceptions.QuestionNotFoundException;
import pro.sky.coursework2.examinerservice.repositories.MathQuestionRepositoryImpl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static pro.sky.coursework2.examinerservice.service.TestConstants.*;
import static pro.sky.coursework2.examinerservice.service.TestConstants.question1;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceImplTest {

    @Mock
    private MathQuestionRepositoryImpl mathQuestionRepository;

    @InjectMocks
    private MathQuestionServiceImpl mathQuestionService;

    private Set<Question> mathQuestions;

    @BeforeEach
    public void beforeEach() {
        mathQuestions = new HashSet<>();
    }


    @Test
    void shouldReturnCorrectValueGetRandomQuestion1() {
        mathQuestions.addAll(
                Stream.of(
                        question1,
                        question2,
                        question3,
                        question4,
                        question5,
                        question6,
                        question7,
                        question8,
                        question9,
                        question10
                ).collect(Collectors.toSet())
        );

        when(mathQuestionRepository.getAll()).thenReturn(mathQuestions);
        assertThat(mathQuestionService.getRandomQuestion()).isNotNull().isIn(mathQuestions);
    }

    @Test
    void shouldReturnCorrectValueGetRandomQuestion2() {
        mathQuestions.addAll(
                Stream.of(
                        question1
                ).collect(Collectors.toSet())
        );

        when(mathQuestionRepository.getAll()).thenReturn(mathQuestions);
        assertThat(mathQuestionService.getRandomQuestion()).isEqualTo(question1);
    }


    @Test
    void shouldReturnQuestionNotFoundExceptionGetRandomQuestion2() {
        when(mathQuestionRepository.getAll()).thenReturn(mathQuestions);
        assertThrows(QuestionNotFoundException.class, () -> mathQuestionService.getRandomQuestion());
    }
}