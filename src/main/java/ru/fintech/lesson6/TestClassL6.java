package ru.fintech.lesson6;

import static ru.fintech.lesson6.TestClass.*;

public abstract class TestClassL6 {

    public static Object squareTriangle(double a, double b, double c) {
        double result;
        double p = (a+b+c)/2;
        if (triangleIsExist(a,b,c)){
        result = testSqrt(p*(p-a)*(p-b)*(p-c));
        return result;}
        else return (null);
    }

    public static boolean triangleIsExist(double a, double b, double c) {
        if (((a>0)&(b>0)&(c>0))&((a+b>c)&(a+c>b)&(b+c>a)))
        return true;
    else return false;
    }

    public static double squareRegTetragone(double a) {return testPow2positive(a);}

    public static double squareCirсle(double r) {return Math.PI*testPow2positive(r);}


}
