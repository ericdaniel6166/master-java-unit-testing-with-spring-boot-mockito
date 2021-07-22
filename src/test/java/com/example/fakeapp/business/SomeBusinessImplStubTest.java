package com.example.fakeapp.business;

import com.example.fakeapp.data.SomeDataService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SomeDataServiceStub implements SomeDataService{
    @Override
    public int[] retrieveAllData() {
        return new int[]{1,2,3};
    }
}

class SomeBusinessImplStubTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void calculateSumUsingDataService_basic() {
        SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl();
        someBusinessImpl.setSomeDataService(new SomeDataServiceStub());
        int expected = someBusinessImpl.calculateSumUsingDataService();
        int actual = 6;
        assertEquals(expected, actual);
    }

    @Test
    void calculateSum_basic() {
        SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl();
        int expected = someBusinessImpl.calculateSum(new int[]{1, 2, 3});
        int actual = 6;
        assertEquals(expected, actual);
    }

    @Test
    void calculateSum_empty() {
        SomeBusinessImpl someBusinessImplTest = new SomeBusinessImpl();
        int expected = someBusinessImplTest.calculateSum(new int[]{});
        int actual = 0;
        assertEquals(expected, actual);
    }

}