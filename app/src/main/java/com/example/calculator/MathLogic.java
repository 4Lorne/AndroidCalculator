package com.example.calculator;

public class MathLogic {
    private double numLeft;
    private double numRight;

    public MathLogic(){
    }

    int subtract(int numLeft,int numRight){
        numLeft = numLeft - numRight;
        return numLeft;
    }
}
