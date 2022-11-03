package pro.sky.coursework2.examinerservice.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.coursework2.examinerservice.domain.Question;
import pro.sky.coursework2.examinerservice.exceptions.QuestionNotFoundException;
import pro.sky.coursework2.examinerservice.exceptions.SuchQuestionAlreadyExistException;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.coursework2.examinerservice.service.TestConstants.*;
import static pro.sky.coursework2.examinerservice.service.TestConstants.question6;

class JavaQuestionRepositoryImplTest {

    private JavaQuestionRepositoryImpl repository = new JavaQuestionRepositoryImpl();

    @AfterEach
    public void afterEach() {
        repository = new JavaQuestionRepositoryImpl();
    }

    @Test
    void shouldReturnCorrectValueAddTest1() {
        Question expected = repository.add(question1);
        assertEquals(expected, question1);
    }

    @Test
    void shouldReturnCorrectValueAddTest2() {
        repository.add(question1.getQuestion(), question1.getAnswer());
        assertThat(repository.getAll())
                .isNotEmpty()
                .hasSize(1)
                .containsExactly(question1);
    }

    @Test
    void shouldReturnNullPointerExceptionAddTest3() {
        assertThrows(NullPointerException.class,
                () -> repository.add(questionWithNullFields.getQuestion(), questionWithNullFields.getAnswer()));
    }

    @Test
    void shouldRemoveQuestionCorrectlyRemoveTest1() {
        repository.add(question1);
        repository.add(question2);
        repository.add(question3);
        repository.remove(question2);
        assertThat(repository.getAll()).containsAll(Set.of(question1, question3));
    }

    @Test
    void shouldRemoveQuestionCorrectlyRemoveTest2() {
        repository.add(question3);
        repository.remove(question3);
        assertThat(repository.getAll()).isEmpty();
    }

    @Test
    void shouldReturnQuestionNotFoundExceptionRemoveTest3() {
        repository.add(question1);
        repository.add(question2);
        repository.add(question3);
        assertThrows(QuestionNotFoundException.class, () -> repository.remove(question5));
    }

    @Test
    void shouldReturnCorrectValueGetAll1() {
        repository.add(question1);
        repository.add(question2);
        repository.add(question3);
        repository.add(question4);
        repository.add(question5);
        repository.add(question6);
        assertThat(repository.getAll()).containsAll(Set.of(question1, question2, question3, question4, question5, question6));
    }

    @Test
    void shouldReturnCorrectValueGetAll2() {
        assertThat(repository.getAll()).isEmpty();
    }


    @ParameterizedTest
    @MethodSource("question1")
    public void add1Test(Question question) {
        repository.add(question);
        assertThatExceptionOfType(SuchQuestionAlreadyExistException.class)
                .isThrownBy(() -> repository.add(question));
        assertThat(repository.getAll()).containsExactly(question);
    }

    @ParameterizedTest
    @MethodSource("question2")
    public void add2Test(String question, String answer) {
        repository.add(new Question(question, answer));
        assertThatExceptionOfType(SuchQuestionAlreadyExistException.class)
                .isThrownBy(() -> repository.add(new Question(question, answer)));
        assertThat(repository.getAll()).containsExactlyInAnyOrder(new Question(question, answer));
    }

    @ParameterizedTest
    @MethodSource("question1")
    public void removeTest(Question question) {
        repository.add(question);
        repository.remove(question);
        assertThat(repository.getAll()).isEmpty();
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> repository.remove(question));
    }

    public static Stream<Arguments> question1() {
        return Stream.of(
                Arguments.of(new Question("Question", "Answer"))
        );
    }

    public static Stream<Arguments> question2() {
        return Stream.of(
                Arguments.of("Question2", "Answer2")
        );
    }
}