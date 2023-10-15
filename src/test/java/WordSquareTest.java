import com.glykon.WordSquare;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WordSquareTest {

    @Mock
    private WordSquare wordSquare;

    @BeforeEach
    public void setUp() {
        //this.wordSquare = new WordSquare();
    }

    @DisplayName("Demos something")
    @Test
    public void shouldDoSomething() {
        assertTrue(true);
    }

    @ParameterizedTest
    @MethodSource("provideChallengeIO")
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

}
