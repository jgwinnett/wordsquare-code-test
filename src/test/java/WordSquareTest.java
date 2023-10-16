import com.glykon.WordSquare;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Stream;

import static org.apache.commons.collections4.CollectionUtils.isEqualCollection;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WordSquareTest {

    private WordSquare wordSquare;

    @BeforeEach
    public void setUp() {
        this.wordSquare = new WordSquare();
    }

    @DisplayName("Solves problem examples when given whole words")
    @ParameterizedTest
    @MethodSource("expectedSquares")
    public void shouldDoSomething(List<String> input) {

        List<String> expected = new ArrayList<>(input);
        List<String> randomisedInput = new ArrayList<>(Collections.unmodifiableList(input));
        Collections.shuffle(randomisedInput);

        List<List<String>> result = wordSquare.solveWordSquare(randomisedInput);

        assertTrue(isEqualCollection(result.getFirst(), expected));
    }

    @ParameterizedTest
    @MethodSource("provideChallengeIO")
    @Disabled
    public void shouldCalculateVoidSquare(String input, List<String> expectedWords) {
        when(wordSquare.getWords(input)).thenReturn(expectedWords);
        List<String> result = wordSquare.getWords(input);
        Assertions.assertIterableEquals(expectedWords, result);
    }

    private static Stream<Arguments> provideChallengeIO() {
        return Stream.of(
                Arguments.of("4 eeeeddoonnnsssrv", List.of("rose", "oven", "send", "ends")),
                Arguments.of("4 aaccdeeeemmnnnoo", List.of("moan", "once", "acme", "need")),
                Arguments.of("5 aaaeeeefhhmoonssrrrrttttw", List.of("feast", "earth", "armor", "stone", "threw")),
                Arguments.of("5 aabbeeeeeeeehmosrrrruttvv", List.of("heart", "ember", "above", "revue", "trees")),
                Arguments.of("7 aaaaaaaaabbeeeeeeedddddggmmlloooonnssssrrrruvvyyy", List.of("bravado", "renamed", "analogy", "valuers", "amoebas", "degrade", "odyssey"))
                );
    }

    private static Stream<Arguments> expectedSquares() {
        return Stream.of(
                Arguments.of(List.of("rose", "oven", "send", "ends")),
                Arguments.of( List.of("moan", "once", "acme", "need")),
                Arguments.of(List.of("feast", "earth", "armor", "stone", "threw")),
                Arguments.of(List.of("heart", "ember", "above", "revue", "trees")),
                Arguments.of(List.of("bravado", "renamed", "analogy", "valuers", "amoebas", "degrade", "odyssey"))
        );
    }

}
