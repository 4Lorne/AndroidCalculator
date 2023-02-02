package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

//TODO: Check there is at least one character to delete
//TODO: If there is only 1 character and it is an operator, set to 0
//TODO: If there is no right character, crashes
//TODO: Fix to allow multiple of the symbol as long as they arent next to each other
//TODO: Check for symbols of different kinds next to each other
//TODO: Add function to multiply by -1
//TODO: If removing text and just a - symbol is remaining set to 0
//TODO: Add app icon
//TODO: Check if right side is null
public class MainActivity extends AppCompatActivity {

    MathLogic mathLogic = new MathLogic();
    Double leftSide = null;
    Double rightSide = null;
    DecimalFormat df = new DecimalFormat("0");
    Button calcOne, calcTwo, calcThree, calcFour, calcFive, calcSix, calcSeven, calcEight, calcNine, calcZero;
    Button calcDivide, calcMultiply, calcAdd, calcSubtract, calcDecimal, calcCalculate;
    Button calcNegPos, calcClear, calcRemove;
    TextView calcResult, calcHistory;

    String operator = "z";
    int nan = R.string.nan;

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
                    stringNum(calcResult, 1, calcHistory);
                    break;
                case R.id.calcTwo:
                    stringNum(calcResult, 2, calcHistory);
                    break;
                case R.id.calcThree:
                    stringNum(calcResult, 3, calcHistory);
                    break;
                case R.id.calcFour:
                    stringNum(calcResult, 4, calcHistory);
                    break;
                case R.id.calcFive:
                    stringNum(calcResult, 5, calcHistory);
                    break;
                case R.id.calcSix:
                    stringNum(calcResult, 6, calcHistory);
                    break;
                case R.id.calcSeven:
                    stringNum(calcResult, 7, calcHistory);
                    break;
                case R.id.calcEight:
                    stringNum(calcResult, 8, calcHistory);
                    break;
                case R.id.calcNine:
                    stringNum(calcResult, 9, calcHistory);
                    break;
                case R.id.calcZero:
                    stringNum(calcResult, 0, calcHistory);
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
                    setDecimal(calcResult, '.');
                    break;
                case R.id.calcRemove:
                    delete(calcResult, calcHistory);
                    break;
                case R.id.calcClear:
                    clear(calcResult, calcHistory);
                    break;
                case R.id.calcCalculate:
                    finishOperation(leftSide, operator, calcResult, calcHistory);
                    break;
            }
        }
    };

    public void setDecimal(TextView calcResult, char decimal) {
        zeroCheck();
        if (calcResult.getText().toString().contains(".")) {
        } else {
            calcResult.setText(calcResult.getText().toString() + decimal);
        }
    }

    //Sets the text every time a button is pressed
    public void stringNum(TextView calcResult, double num, TextView calcHistory) {
        zeroCheck();
        if (calcResult.getText().toString().length() >= 8){
            Toast.makeText(getApplicationContext(),"Numbers are limited to 8 digits.",Toast.LENGTH_SHORT).show();
            return;
        }
        if (calcResult.getText().toString().contains("NaN")) {
            calcHistory.setText("");
            calcResult.setText("");
        }
        String s = df.format(num);
        calcResult.setText(calcResult.getText().toString() + s);
        calcHistory.setText(calcHistory.getText().toString() + s);
    }

    //Pass to MathLogic, sets the result as the text.
    public void finishOperation(Double leftSide, String operator, TextView calcResult, TextView calcHistory) {
        if (calcResult.getText().toString().charAt(0) == '.' && calcResult.length() == 1) {
            return;
        }

        //If equals is pressed before any other numbers
        if (leftSide == null) {
            calcResult.setText("0");
            return;
        }

        double rightSide = Double.parseDouble(calcResult.getText().toString());


        //If right side has no value when equals is pressed
        if (rightSide == 0.0) {
            calcResult.setText(nan);
            return;
        }

        //Checks for division by 0
        if (leftSide == 0 && operator.equals("/")) {
            calcResult.setText(nan);
            return;
        }

        double result = mathLogic.operation(leftSide, rightSide, operator);

        if (result > 99999999){
            Toast.makeText(getApplicationContext(),"Numbers are limited to 8 digits.",Toast.LENGTH_SHORT).show();
            calcResult.setText("99999999");
            return;
        }
        //Checks if there is going to be a decimal place
        if (leftSide % rightSide != 0 && operator.equals("/")) {
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
        if (calcResult.getText().toString().charAt(0) == '.' && calcResult.length() == 1) {
            return;
        }

        operator = symbol;
        leftSide = Double.parseDouble(calcResult.getText().toString());
        calcHistory.setText(calcResult.getText().toString() + symbol);
        calcResult.setText("0");
    }


    //Multiples by 1 to change from negative to positive
    public void negPos(TextView calcResult) {
        //Checks if 0 is in the first position
        if (calcResult.getText().length() == 1 && calcResult.getText().toString().charAt(0) == '0') {
            return;
        }
        double result = Double.parseDouble(calcResult.getText().toString());
        result = result * -1;
        String s = df.format(result);
        calcResult.setText(s);
    }

    //Checks if a 0 is in the 1 position and the length is only 1. Cannot have two 0's next to each other in the 1s position
    public void zeroCheck() {
        if (calcResult.getText().length() == 1 && calcResult.getText().toString().charAt(0) == '0') {
            calcResult.setText("");
            calcHistory.setText("");
        }
    }

    //Erases calcResult and calcHistory when C is pressed
    public void clear(TextView calcResult, TextView calcHistory) {
        calcResult.setText("0");
        calcHistory.setText("");
        leftSide = null;
        rightSide = null;
    }

    //Deletes just one character
    public void delete(TextView calcResult, TextView calcHistory) {
        if (calcResult.length() > 0) {
            calcResult.setText(calcResult.getText().subSequence(0, calcResult.length() - 1));
        }
        if (calcResult.length() == 0) {
            calcResult.setText("0");
        }
        if (calcResult.getText().toString().charAt(0) == 'N') {
            calcResult.setText("0");
        }
        if (calcResult.getText().toString().charAt(0) == '-') {
            calcResult.setText("0");
        }
        if (calcHistory.length() > 0) {
            calcHistory.setText(calcHistory.getText().subSequence(0, calcHistory.length() - 1));
        }
    }
}