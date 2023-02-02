package com.example.calculator;

public class MathLogic {
    private double numLeft;
    private double numRight;
    private String operator;

    public MathLogic(){
    }

    double operation(double numLeft, double numRight, String operator){
        double result = 0;
        switch(operator){
            case "+":
                result = numLeft + numRight;
                return result;
            case "-":
                result = numLeft - numRight;
                return result;
            case "*":
                result = numLeft * numRight;
                return result;
            case "/":
                result= numLeft / numRight;
                return result;
        }
        return result;
    }
}
