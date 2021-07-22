package com.example.fakeapp.business;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ArgumentCaptorTest {

    @Test
    void testArgumentCaptor() {
        List mockList = mock(List.class);
        String data = "Something";
        mockList.add(data);

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockList).add(stringArgumentCaptor.capture());

        assertEquals(data, stringArgumentCaptor.getValue());
    }

    @Test
    void testMultipleArgumentCaptor2() {
        List mockList = mock(List.class);
        String data1 = "Something1";
        String data2 = "Something2";
        mockList.add(data1);
        mockList.add(data2);

        List<String> expected = new ArrayList<>();
        expected.add(data1);
        expected.add(data2);

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockList, times(2)).add(stringArgumentCaptor.capture());

        List<String> actual = stringArgumentCaptor.getAllValues();

        assertEquals(expected,actual);

    }

    @Test
    void testMultipleArgumentCaptor() {
        List mockList = mock(List.class);
        String data1 = "Something1";
        String data2 = "Something2";
        mockList.add(data1);
        mockList.add(data2);

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockList, times(2)).add(stringArgumentCaptor.capture());

        List<String> stringList = stringArgumentCaptor.getAllValues();

        assertAll(
                () -> assertEquals(data1, stringList.get(0)),
                () -> assertEquals(data2, stringList.get(1))
        );

    }

}
