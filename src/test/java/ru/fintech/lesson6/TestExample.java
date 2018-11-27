package ru.fintech.lesson6;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.fintech.lesson6.TestClassL6.*;
import static ru.fintech.lesson6.TestClass.*;


public class TestExample {

    @BeforeEach
    public void runBeforeTest(){
        System.out.println("Test begin");
    }
    @AfterEach
    public void runAfterTest(){
        System.out.println("Test end");
    }

    @Test
    @Tag("triangle")
    public void testTriangleSquare() {
        System.out.println("Test triangle area");
        assertTrue(5.724==(Math.round((Double)(squareTriangle(3, 4.5, 6.6))*1000)/1000.0),"Wrong area");
        assertEquals(1.609,(Double)(squareTriangle(3, 3, 5.9)),0.001, "Wrong area!");
    }

    @Test
    @Tag("triangle")
    public void testTriangleIsExist() {
        System.out.println("Test triangle exist");
        assertThat("Triangle is not exist",squareTriangle(3,4.5,8.6),is(notNullValue()));
    }

    @ParameterizedTest(name = "\"{0}\" should be greater than 0")
    @ValueSource(doubles = { 2.1, -3.2 })
    @Tag("regTetragone")
    void testArgsRegTetragone(double argument) {
        System.out.println("Test square exist with side " + argument);
        assertThat("argument should be greater than 0",argument, greaterThan(0.0));
    }
    @ParameterizedTest
    @ValueSource(doubles = { 5.0, -6.5 })
    @Tag("regTetragone")
    void testRegTetragoneSquare(double argument) {
        System.out.println("Test square area with side " + argument );
        double result = testPow2positive(argument);
        assertThat(squareRegTetragone(argument),equalTo(result));
    }

    @ParameterizedTest(name = "Check area of circle with radius(''{0}'') = ''{1}''")
    @DisplayName("Check circleSquare function with method source")
    @MethodSource("testCircleSquare")
    @Tag("circle")
    public void testCircleSquareTest(double value, double expectedResult){
        System.out.println("Test circle area with radius " + value );
        double result = squareCirсle(value);
        assertThat(expectedResult,equalTo(result));
    }

    private static Stream<Arguments> testCircleSquare() {
        double value = new Random().nextDouble();
        return Stream.of(
                Arguments.of(2.0, testPow2positive(2.0)*Math.PI),
                Arguments.of(value, testPow2positive(value)*Math.PI));
    }




}
