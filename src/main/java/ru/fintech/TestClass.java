package ru.fintech;

public abstract class TestClass {

    public static double testSqrt(double num) {
        return Math.sqrt(num);
    }

    public static double testPow2positive(double num) {

        if (num>0){
            return Math.pow(num,2.0);}
        else return 0.0;
    }
    public static double testPow2(double num) {return Math.pow(num,2.0);}

    public static double testCos(double num) {
        return Math.cos(num);
    }

    public static int testFactorial1(int count) {
        int result = 1;
        for (int i = 1; i <=count; i ++){
            result = result*i;
        }
        return result;
    }


    public static int testFactorial2(int count) {
        int result;
        if (count==1)
            return 1;
        result = count*testFactorial2(count-1);
        return result;
    }

}
