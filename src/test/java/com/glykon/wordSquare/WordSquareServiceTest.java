package com.glykon.wordSquare;

import com.glykon.exception.InvalidInputException;
import com.glykon.validators.InputValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WordSquareServiceTest {

    WordSquareService underTest;
    @Mock
    InputValidator inputValidator;
    @Mock
    WordSquareSolverFactory wordSquareSolverFactory;
    @Mock
    WordSquareSolver wordSquareSolver;

    private final List<String> dictionary = Collections.emptyList();
    private final String input = "2 abcde";

    @BeforeEach
    public void setup() {
        underTest = new WordSquareService(dictionary, inputValidator, wordSquareSolverFactory);
    }

    @Test
    public void shouldValidateInput() throws Exception {
        commonStubbing();
        doNothing().when(inputValidator).validateInput(anyString());
        underTest.getWordSquaresForInput(input);

        verify(inputValidator).validateInput(input);
    }

    @Test
    public void serviceShouldThrowExceptionIfInputValidationFails() {
        doThrow(new InvalidInputException("some error")).when(inputValidator).validateInput(input);
        Exception e = assertThrows(Exception.class,
                () -> underTest.getWordSquaresForInput(input)
        );

        assertEquals("some error", e.getMessage());
    }

    @Test
    public void shouldReturnWordSquareSolveResults() throws Exception {
        commonStubbing();
        List<List<String>> result = underTest.getWordSquaresForInput(input);

        verify(wordSquareSolverFactory).buildWordSquareSolver(any(), anyList());
        assertNotNull(result);
    }

    private void commonStubbing() {
        when(wordSquareSolverFactory.buildWordSquareSolver(any(), anyList())).thenReturn(wordSquareSolver);
        when(wordSquareSolver.solveWordSquareWithTrie()).thenReturn(new ArrayList<>());
    }
}