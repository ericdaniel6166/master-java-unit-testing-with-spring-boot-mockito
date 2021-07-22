package com.example.fakeapp.business;

import com.example.fakeapp.data.SomeDataService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//////junit 4
//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class SomeBusinessImplTest {
    @InjectMocks
    SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl();

    @Mock
    SomeDataService someDataService;

    @BeforeEach
    void setUp() {
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