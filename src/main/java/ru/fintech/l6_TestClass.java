package ru.fintech;

import static ru.fintech.TestClass.*;

public abstract class l6_TestClass {

    public static double squareTriangle(double a, double b, double c) {
        double result;
        double p = (a+b+c)/2;
        result = testSqrt(p*(p-a)*(p-b)*(p-c));
        return result;
    }

    public static double squareRegTetragone(double a) {
        double result;
        result = testPow2(a);
        return result;
    }

    public static double squareCirсle(double r) {
        double result;
        result = Math.PI*testPow2(r);
        return result;
    }

}
