package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

//TODO: Check there is at least one character to delete
//TODO: If there is only 1 character and it is an operator, set to 0
//TODO: If there is no right character, crashes
//TODO: Fix to allow multiple of the symbol as long as they arent next to each other
//TODO: Check for symbols of different kinds next to each other
//TODO: Add function to multiply by -1
//TODO: If removing text and just a - symbol is remaining set to 0
public class MainActivity extends AppCompatActivity {

    MathLogic mathLogic = new MathLogic();
    double leftSide;
    double rightSide;
    DecimalFormat df = new DecimalFormat("0");
    Button calcOne, calcTwo, calcThree, calcFour, calcFive, calcSix, calcSeven, calcEight, calcNine, calcZero;
    Button calcDivide, calcMultiply, calcAdd, calcSubtract, calcDecimal, calcCalculate;
    Button calcNegPos, calcClear, calcRemove;
    TextView calcResult, calcHistory;

    String operator = "z";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calcZero = findViewById(R.id.calcZero);
        calcOne = findViewById(R.id.calcOne);
        calcTwo = findViewById(R.id.calcTwo);
        calcThree = findViewById(R.id.calcThree);
        calcFour = findViewById(R.id.calcFour);
        calcFive = findViewById(R.id.calcFive);
        calcSix = findViewById(R.id.calcSix);
        calcSeven = findViewById(R.id.calcSeven);
        calcEight = findViewById(R.id.calcEight);
        calcNine = findViewById(R.id.calcNine);
        calcDivide = findViewById(R.id.calcDivide);
        calcMultiply = findViewById(R.id.calcMultiply);
        calcAdd = findViewById(R.id.calcAdd);
        calcSubtract = findViewById(R.id.calcSubtract);
        calcDecimal = findViewById(R.id.calcDecimal);
        calcCalculate = findViewById(R.id.calcCalculate);
        calcNegPos = findViewById(R.id.calcNegPos);
        calcClear = findViewById(R.id.calcClear);
        calcRemove = findViewById(R.id.calcRemove);
        calcResult = findViewById(R.id.calcResult);
        calcHistory = findViewById(R.id.calcHistory);

        calcZero.setOnClickListener(onButtonClicked);
        calcOne.setOnClickListener(onButtonClicked);
        calcTwo.setOnClickListener(onButtonClicked);
        calcThree.setOnClickListener(onButtonClicked);
        calcFour.setOnClickListener(onButtonClicked);
        calcFive.setOnClickListener(onButtonClicked);
        calcSix.setOnClickListener(onButtonClicked);
        calcSeven.setOnClickListener(onButtonClicked);
        calcEight.setOnClickListener(onButtonClicked);
        calcNine.setOnClickListener(onButtonClicked);
        calcDivide.setOnClickListener(onButtonClicked);
        calcMultiply.setOnClickListener(onButtonClicked);
        calcAdd.setOnClickListener(onButtonClicked);
        calcSubtract.setOnClickListener(onButtonClicked);
        calcDecimal.setOnClickListener(onButtonClicked);
        calcCalculate.setOnClickListener(onButtonClicked);
        calcNegPos.setOnClickListener(onButtonClicked);
        calcClear.setOnClickListener(onButtonClicked);
        calcRemove.setOnClickListener(onButtonClicked);


    }

    public View.OnClickListener onButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //stringNum will check for 0 in the first position and also append the string
            switch (v.getId()) {
                case R.id.calcOne:
                    stringNum(calcResult, 1);
                    break;
                case R.id.calcTwo:
                    stringNum(calcResult, 2);
                    break;
                case R.id.calcThree:
                    stringNum(calcResult, 3);
                    break;
                case R.id.calcFour:
                    stringNum(calcResult, 4);
                    break;
                case R.id.calcFive:
                    stringNum(calcResult, 5);
                    break;
                case R.id.calcSix:
                    stringNum(calcResult, 6);
                    break;
                case R.id.calcSeven:
                    stringNum(calcResult, 7);
                    break;
                case R.id.calcEight:
                    stringNum(calcResult, 8);
                    break;
                case R.id.calcNine:
                    stringNum(calcResult, 9);
                    break;
                case R.id.calcZero:
                    stringNum(calcResult, 0);
                    break;
                case R.id.calcAdd:
                    mathSymbols(calcResult, calcHistory, "+");
                    break;
                case R.id.calcSubtract:
                    mathSymbols(calcResult, calcHistory, "-");
                    break;
                case R.id.calcMultiply:
                    mathSymbols(calcResult, calcHistory, "*");
                    break;
                case R.id.calcDivide:
                    mathSymbols(calcResult, calcHistory, "/");
                    break;
                case R.id.calcNegPos:
                    negPos(calcResult);
                    break;
                case R.id.calcDecimal:
                    mathSymbols(calcResult, calcHistory, ".");
                    break;
                case R.id.calcRemove:
                    delete(calcResult);
                    break;
                case R.id.calcClear:
                    clear(calcResult, calcHistory);
                    break;
                case R.id.calcCalculate:
                    saveHistory(calcResult, calcHistory);
                    finishOperation(leftSide, rightSide, operator, calcHistory);
                    break;
            }
        }
    };

    //Sets the text every time a button is pressed
    public void stringNum(TextView calcResult, double num) {
        zeroCheck();
        String s = df.format(num);
        calcResult.setText(calcResult.getText().toString() + s);
    }

    //Pass to MathLogic, sets the result as the text.
    public void finishOperation(double leftSide, double rightSide, String operator, TextView calcHistory) {
        double result = mathLogic.operation(leftSide, rightSide, operator);
        if (leftSide == 0 || rightSide == 0) {
            calcResult.setText("NaN");
            return;
        }
        //Checks if there is going to be a decimal place
        if (leftSide % rightSide != 0) {
            DecimalFormat dfTwo = new DecimalFormat("0.00");
            String s = dfTwo.format(result);
            calcResult.setText(s);
            calcHistory.setText(s);
        } else {
            String s = df.format(result);
            calcResult.setText(s);
            calcHistory.setText(s);
        }
    }

    //Sets operator equal to the most recent symbol and sets the left side + resets the text
    public void mathSymbols(TextView calcResult, TextView calcHistory, String symbol) {
        operator = symbol;
        calcHistory.setText(calcHistory.getText().toString() + "" + calcResult.getText().toString() + "" + symbol);
        leftSide = Double.parseDouble(calcResult.getText().toString());
        calcResult.setText("0");
    }


    //Multiples by 1 to change from negative to positive
    public void negPos(TextView calcResult) {
        double result = Double.parseDouble(calcResult.getText().toString());
        result = result * -1;
        String s = df.format(result);
        calcResult.setText(s);

    }

    //Checks if a 0 is in the 1 position and the length is only 1. Cannot have two 0's next to each other in the 1s position
    public void zeroCheck() {
        if (calcResult.getText().length() == 1 && calcResult.getText().toString().charAt(0) == '0') {
            calcResult.setText("");
        }
    }

    //Erases calcResult and calcHistory when C is pressed
    public void clear(TextView calcResult, TextView calcHistory) {
        calcResult.setText("0");
        calcHistory.setText("");
        leftSide = 0;
        rightSide = 0;
    }

    //Deletes just one character
    public void delete(TextView calcResult) {
        if (calcResult.length() > 0) {
            calcResult.setText(calcResult.getText().subSequence(0, calcResult.length() - 1));
        }
        if (calcResult.length() == 0) {
            calcResult.setText("0");
        }
        if (calcResult.getText().toString().charAt(0) == 'N'){
            calcResult.setText("0");
        }
        if (calcResult.getText().toString().charAt(0) == '-'){
            calcResult.setText("0");
        }
    }

    //Saves the history to a small text field above
    public void saveHistory(TextView calcResult, TextView calcHistory) {
        calcHistory.setText(calcHistory.getText() + " " + calcResult.getText());
        rightSide = Double.parseDouble(calcResult.getText().toString());
        System.out.println("Left side: " + leftSide);
        System.out.println("Right side: " + rightSide);
    }
}