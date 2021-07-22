package com.example.fakeapp.business;

import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ListMockTest {

    @Test
    void test3() {
        List<String> mock = mock(List.class);
        String data1 = "abc";
        String data2 = "abcd";
        when(mock.get(anyInt())).thenReturn(data1).thenReturn(data2);
        String expected1 = data1;
        String expected2 = data2;
        String actual1 = mock.get(0);
        String actual2 = mock.get(1);
        assertAll(
                () -> assertEquals(expected1, actual1),
                () -> assertEquals(expected2, actual2)
        );

        verify(mock).get(0);
        verify(mock).get(1);
        verify(mock, times(1)).get(1);
        verify(mock, atMost(1)).get(1);
        verify(mock, atLeast(1)).get(1);
        verify(mock, atLeastOnce()).get(1);
        verify(mock, atMostOnce()).get(1);
        verify(mock, never()).get(2);
    }

    @Test
    void test() {
        List mock = mock(List.class);
        when(mock.size()).thenReturn(5).thenReturn(10);
        int expected1 = 5;
        int expected2 = 10;
        assertEquals(expected1, mock.size());
        assertEquals(expected2, mock.size());
    }

    @Test
    void test2() {
        List mock = mock(List.class);
        String data1 = "abc";
        String data2 = "abcd";
        when(mock.get(anyInt())).thenReturn(data1).thenReturn(data2);
        String expected1 = data1;
        String expected2 = data2;
        assertAll(
                () -> assertEquals(expected1, mock.get(0)),
                () -> assertEquals(expected2, mock.get(1))
        );
    }
}
