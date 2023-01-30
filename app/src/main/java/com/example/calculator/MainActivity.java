package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MathLogic mathLogic = new MathLogic();
    Button calcOne, calcTwo, calcThree, calcFour, calcFive, calcSix, calcSeven, calcEight, calcNine, calcZero;
    Button calcDivide, calcMultiply, calcAdd, calcSubtract, calcDecimal, calcCalculate;
    Button calcNegPos, calcClear, calcRemove;
    TextView calcResult, calcHistory;


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
                    mathSymbols(calcResult, "+");
                    break;
                case R.id.calcSubtract:
                    mathSymbols(calcResult, "-");
                    break;
                case R.id.calcMultiply:
                    mathSymbols(calcResult, "*");
                    break;
                case R.id.calcDivide:
                    mathSymbols(calcResult, "/");
                    break;
                case R.id.calcNegPos:
                    negPos(calcResult);
                    break;
                case R.id.calcDecimal:
                    mathSymbols(calcResult, ".");
                    break;
                case R.id.calcRemove:
                    delete(calcResult);
                    break;
                case R.id.calcClear:
                    clear(calcResult);
                    break;
            }
        }
    };

    public void stringNum(TextView button, int num) {
        zeroCheck();
        String s = String.valueOf(num);
        button.setText(button.getText() + s);
    }

    //TODO: Fix to allow multiple of the symbol as long as they arent next to each other
    //TODO: Check for symbols of different kinds next to each other
    //TODO: Add function to multiply by -1
    public void mathSymbols(TextView button, String symbol) {
        zeroCheck();
        if (button.getText().toString().contains(symbol)) {
        } else {
            button.setText(button.getText() + symbol);
        }
    }

    //TODO: Check there is at least one character to delete
    public void delete(TextView calcResult) {
        if (calcResult.length() > 0) {
            calcResult.setText(calcResult.getText().subSequence(0, calcResult.length() - 1));
        }
        if (calcResult.length() == 0) {
            calcResult.setText("0");
        }
    }

    public void clear(TextView calcResult) {
        calcResult.setText("0");
    }

    //TODO: Decimal numbers crash program...Mod 0 check for whole numbers?
    public void negPos(TextView calcResult){
        String temp = calcResult.getText().toString();
        int result = Integer.parseInt(temp);
        result = result * -1;
        temp = String.valueOf(result);
        calcResult.setText(temp);
    }

    public void zeroCheck() {
        if (calcResult.getText().length() == 1 && calcResult.getText().toString().charAt(0) == '0') {
            calcResult.setText("");
        }
    }
}