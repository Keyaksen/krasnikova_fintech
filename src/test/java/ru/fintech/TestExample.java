package ru.fintech;

import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static ru.fintech.TestClass.*;

public class TestExample {
    @Test
    public void testExampleSqrt(){
        double result = testSqrt(6.66);
        assertEquals("Wrong sqty!", result, 2.5806975801127883, 0);
    }

    @Test
    public void testExamplePow2(){
        double result = testPow2(6.66, 2.0);
        assertEquals("Wrong pow!", result, 44.3556, 0);

    }

    @Test
    public void testExampleCos(){
        double result = testCos(6.66);
        assertEquals("Wrong cos!", result, 0.9298414180167014, 0);
    }

    @Test
    public void testExampleFactorial1(){
        int result = testFactorial1(6);
        assertEquals("Wrong factorial1!", result, 720, 0);
    }

    @Test
    public void testExampleFactorial2(){
        int result = testFactorial2(6);
        assertEquals("Wrong factorial2!", result, 720, 0);
    }

}
