package hu.nye.progtech.torpedo.service.input;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;


import java.io.BufferedReader;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class UserInteractionTest {
    private static final String INPUT = "something";

    private UserInteraction test;
    @Mock
    private BufferedReader bufferedReader;

    @BeforeEach
    public void setUp(){
        test = new UserInteraction(bufferedReader);
    }
    @Test
    void readPlayerName() throws IOException {
        given(bufferedReader.readLine()).willReturn(INPUT);
        //when
        String result = test.readInput();
        //then
        assertEquals(INPUT, result);
    }

    @Test
    void readInput() throws IOException {
        doThrow(IOException.class).when(bufferedReader).readLine();
        //when
        String result = test.readInput();
        //then
        assertNull(result);
    }
}