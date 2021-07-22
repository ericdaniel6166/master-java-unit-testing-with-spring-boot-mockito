package com.example.fakeapp.business;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SpyTest {

    @Test
    void testSpy() {
        ArrayList<String> spyArrayList = spy(ArrayList.class);
        String data = "Something";
        spyArrayList.add(data);
        System.out.println(spyArrayList.get(0));

    }

    @Test
    void testMock() {
        ArrayList<String> mockArrayList = mock(ArrayList.class);
        String data = "Something";
        mockArrayList.add(data);
        System.out.println(mockArrayList.get(0));

    }


}
