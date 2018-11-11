package ru.fintech;
import static ru.fintech.TestClass.testPow2positive;
import static ru.fintech.l6_TestClass.*;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.BaseMatcher.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.fintech.TestClass.testPow2;

public class l6_TestExample {
    @Test
    @Tag("triangle")
    public void testTriangleSquare() {
        assertEquals(5.724,Math.round((Double)(squareTriangle(3, 4.5, 6.6))*1000)/1000.0,"Wrong square!");
        assertTrue(5.724==(Math.round((Double)(squareTriangle(3, 4.5, 6.6))*1000)/1000.0),"Wrong2");
    }

    @Test
    @Tag("triangle")
    public void testTriangleIsExist() {
        assertThat("Triangle is not exist",squareTriangle(3,4.5,8.6),is(notNullValue()));
    }

    @ParameterizedTest(name = "\"{0}\" should be greater than 0")
    @ValueSource(doubles = { 5.0, -6.5 })
    @Tag("regTetragone")
    void testArgsRegTetragone(double argument) {
        assertThat("argument should be greater than 0",argument, greaterThan(0.0));
    }
    @ParameterizedTest
    @ValueSource(doubles = { 5.0, -6.5 })
    @Tag("regTetragone")
    void testRegTetragoneSquare(double argument) {
        double result = testPow2positive(argument);
        assertThat(squareRegTetragone(argument),equalTo(result));
    }

}
