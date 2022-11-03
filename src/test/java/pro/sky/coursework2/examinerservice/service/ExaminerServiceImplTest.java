package pro.sky.coursework2.examinerservice.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.coursework2.examinerservice.domain.Question;
import pro.sky.coursework2.examinerservice.exceptions.IncorrectNumberOfRequestedQuestionsException;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static pro.sky.coursework2.examinerservice.service.TestConstants.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionServiceImpl javaQuestionServiceImpl;
    @Mock
    private MathQuestionServiceImpl mathQuestionServiceImpl;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private final Set<Question> javaQuestions = new HashSet<>();
    private final Set<Question> mathQuestions = new HashSet<>();

    @BeforeEach
    public void beforeEach() {
        javaQuestions.clear();
        mathQuestions.clear();

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

        mathQuestions.addAll(
                Stream.of(
                        mathQuestion1,
                        mathQuestion2,
                        mathQuestion3,
                        mathQuestion4,
                        mathQuestion5
                ).collect(Collectors.toSet())
        );

        when(javaQuestionServiceImpl.getAll()).thenReturn(javaQuestions);
        when(mathQuestionServiceImpl.getAll()).thenReturn(mathQuestions);
    }

    @ParameterizedTest
    @MethodSource("params")
    public void getQuestionsPositiveTest2(int amount) {
        assertThat(examinerService.getQuestions(amount))
                .hasSize(amount)
                .containsAnyElementsOf(Stream.concat(javaQuestions.stream(), mathQuestions.stream())
                        .collect(Collectors.toSet()));
    }

    @ParameterizedTest
    @MethodSource("negativeParams")
    public void getQuestionsNegativeTest(int amount) {
        assertThatExceptionOfType(IncorrectNumberOfRequestedQuestionsException.class)
                .isThrownBy(() -> examinerService.getQuestions(amount));
    }

    public static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(3),
                Arguments.of(6),
                Arguments.of(8),
                Arguments.of(11),
                Arguments.of(15)
        );
    }

    public static Stream<Arguments> negativeParams() {
        return Stream.of(
                Arguments.of(-5),
                Arguments.of(-1),
                Arguments.of(0),
                Arguments.of(999)
        );
    }

    public static class MyRandom extends Random {

    }

}