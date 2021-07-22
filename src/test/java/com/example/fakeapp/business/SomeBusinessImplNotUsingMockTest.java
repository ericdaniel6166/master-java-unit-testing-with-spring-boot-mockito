package com.example.fakeapp.business;

import com.example.fakeapp.data.SomeDataService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SomeBusinessImplNotUsingMockTest {

    SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl();
    SomeDataService someDataService = mock(SomeDataService.class);

    @BeforeEach
    void setUp() {
        someBusinessImpl.setSomeDataService(someDataService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void calculateSumUsingDataService_basic() {
        int[] data = {1, 2, 3};
        when(someDataService.retrieveAllData()).thenReturn(data);
        int expected = someBusinessImpl.calculateSumUsingDataService();
        int actual = 6;
        assertEquals(expected, actual);
    }

    @Test
    void calculateSumUsingDataService_empty() {
        int[] data = {};
        when(someDataService.retrieveAllData()).thenReturn(data);
        int expected = someBusinessImpl.calculateSumUsingDataService();
        int actual = 0;
        assertEquals(expected, actual);
    }


}