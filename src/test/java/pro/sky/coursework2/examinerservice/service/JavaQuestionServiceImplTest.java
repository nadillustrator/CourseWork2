package pro.sky.coursework2.examinerservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.coursework2.examinerservice.domain.Question;
import pro.sky.coursework2.examinerservice.exceptions.QuestionNotFoundException;
import pro.sky.coursework2.examinerservice.repositories.JavaQuestionRepositoryImpl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pro.sky.coursework2.examinerservice.service.TestConstants.*;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceImplTest {
    @Mock
    private JavaQuestionRepositoryImpl javaQuestionRepository;

    @InjectMocks
    private JavaQuestionServiceImpl javaQuestionService;

    private Set<Question> javaQuestions;

    @BeforeEach
    public void beforeEach() {
        javaQuestions = new HashSet<>();
    }


    @Test
    void shouldReturnCorrectValueGetRandomQuestion1() {
        javaQuestions.addAll(
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

        when(javaQuestionRepository.getAll()).thenReturn(javaQuestions);
        assertThat(javaQuestionService.getRandomQuestion()).isNotNull().isIn(javaQuestions);
    }

    @Test
    void shouldReturnCorrectValueGetRandomQuestion2() {
        javaQuestions.addAll(
                Stream.of(
                        question1
                ).collect(Collectors.toSet())
        );

        when(javaQuestionRepository.getAll()).thenReturn(javaQuestions);
        assertThat(javaQuestionService.getRandomQuestion()).isEqualTo(question1);
    }


    @Test
    void shouldReturnQuestionNotFoundExceptionGetRandomQuestion2() {
        when(javaQuestionRepository.getAll()).thenReturn(javaQuestions);
        assertThrows(QuestionNotFoundException.class, () -> javaQuestionService.getRandomQuestion());
    }
}