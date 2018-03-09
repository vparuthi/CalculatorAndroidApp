package com.example.veraj.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Objects;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "com.example.veraj.calculator";

    Button[][] buttons = new Button [5][4];
    float first_value = 0;
    float second_value = 0;
    int ifirst_value = 0;
    int isecond_value = 0;
    float power_value = 10;
    float decimal_value = 1;
    boolean second_value_check = true;
    int operation_type = 0; // used to tell what operation is being conducted, 0 is simply when a number is pressed
    boolean check = true; // to make sure only numbered button presses enter the number else if and not operation symbols
    boolean equals_count = false; //when the equals button is clicked more than once to repeat the operation



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i =0; i<5; i++){
            for (int j =0; j<4; j++){
                String id = "b" + i + j;
                int resID = getResources().getIdentifier(id, "id", getPackageName());
                buttons [i][j] = findViewById(resID);
                buttons [i][j].setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View view) {

        check = true;
        boolean decimal_reset = false; //this is set to true once the decimal button is clicked and used to reset the power_vale and decimal_value
        TextView screen = (TextView)findViewById(R.id.screen);
        Button button = (Button)view;
        String screen_text = "";
        double double_value = 0;
        String buttonId = "b43";

        String button_text = button.getText().toString();
        Log.i(TAG, "Button pressed:" + button_text);

        if (Objects.equals(getResources().getResourceEntryName(view.getId()), "b43")) {
            final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
            MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 20);
            myAnim.setInterpolator(interpolator);
            button.startAnimation(myAnim);
        }
        switch (button_text) {
            case "*":
                screen.setText(screen_text);
                second_value_check = !second_value_check;
                operation_type = 1;
                check = false;
                decimal_reset = true;
                second_value = 0;
                isecond_value = 0;
                break;
            case "/":
                screen.setText(screen_text);
                second_value_check = !second_value_check;
                operation_type = 2;
                check = false;
                decimal_reset = true;
                second_value = 0;
                isecond_value = 0;
                break;
            case "=":
                screen.setText(screen_text);

                switch (operation_type) {
                    case 1:
                        first_value = first_value * second_value;
                        break;
                    case 2:
                        first_value = first_value / second_value;
                        break;
                    case 3:
                        first_value = first_value + second_value;
                        break;
                    case 4:
                        first_value = first_value - second_value;
                        break;
                }
                second_value_check = !second_value_check;

                if (checkInt(first_value)){
                     ifirst_value = Math.round(first_value);
                     screen_text = Integer.toString(ifirst_value);
                     screen.setText(screen_text);
                }
                else{
                    screen_text = Float.toString(first_value);
                    screen.setText(screen_text);
                }


                if (equals_count) {
                    second_value = 0;
                }
                equals_count = true;
                check = false;
                decimal_reset = true;
                break;
            case "+":
                screen.setText(screen_text);
                second_value_check = !second_value_check;
                operation_type = 3;
                check = false;
                decimal_reset = true;
                second_value = 0;
                isecond_value = 0;
                break;
            case "-":
                screen.setText(screen_text);
                second_value_check = !second_value_check;
                operation_type = 4;
                check = false;
                decimal_reset = true;
                second_value = 0;
                isecond_value = 0;
                break;
            case "%":
                screen.setText(screen_text);
                second_value_check = !second_value_check;
                first_value = first_value / 100;
                screen_text = Float.toString(first_value);
                screen.setText(screen_text);
                check = false;
                decimal_reset = true;
                second_value = 0;
                isecond_value = 0;
                break;
            case ".":
                if (first_value ==0  || (second_value ==0 && second_value_check == false)){
                    screen_text = "0.";
                    screen.setText(screen_text);
                }
                decimal_value = 10;
                power_value = 1;
                check = false;
                break;
            case "+/-":
                //decimal_value = -1;
                if (second_value_check) {
                    if (checkInt(first_value)){
                        ifirst_value = ifirst_value * -1;
                        first_value = ifirst_value;
                        screen_text = Integer.toString(ifirst_value);
                        screen.setText(screen_text);
                    }
                    else {
                        first_value = first_value *-1;
                        screen_text = Float.toString(first_value);
                        screen.setText(screen_text);
                    }
                }
                else {

                    if (checkInt(second_value)){
                        isecond_value = isecond_value * -1;
                        second_value = isecond_value;
                        screen_text = Integer.toString(isecond_value);
                        screen.setText(screen_text);
                    }
                    else {
                        second_value = second_value *-1;
                        screen_text = Float.toString(second_value);
                        screen.setText(screen_text);
                    }
                }
                check = false;
                break;
            case "√":
                screen.setText(screen_text);
                if (second_value_check) {
                    double_value = first_value;
                    double_value = java.lang.Math.sqrt(double_value);
                    first_value = (float) double_value;
                    screen_text = Float.toString(first_value);
                    screen.setText(screen_text);
                } else {
                    double_value = second_value;
                    double_value = java.lang.Math.sqrt(double_value);
                    second_value = (float) double_value;
                    screen_text = Float.toString(second_value);
                    screen.setText(screen_text);
                }
                second_value = 0;
                isecond_value = 0;
                check = false;
                break;
            case "del":
                if (second_value_check) {
                    if (checkInt(first_value)){
                        screen_text = Integer.toString(ifirst_value);
                    }
                    else {
                        screen_text = Float.toString(first_value);
                    }
                    char before_delete[] = screen_text.toCharArray();
                    char after_delete[] = new char[screen_text.length()-1];
                    for (int i = 0; i < screen_text.length() - 1; i++) {
                        after_delete[i] = before_delete[i];
                    }
                    if (checkInt(first_value)){
                        ifirst_value = Integer.parseInt(new String(after_delete));
                        screen_text = Integer.toString(ifirst_value);
                        first_value = ifirst_value;
                        screen.setText(screen_text);
                    }
                    else {
                        first_value = Float.parseFloat(new String(after_delete));
                        screen_text = Float.toString(first_value);
                        screen.setText(screen_text);
                    }
                }
                else {
                    if (checkInt(second_value)){
                        screen_text = Integer.toString(isecond_value);
                    }
                    else {
                        screen_text = Float.toString(second_value);
                    }
                    char before_delete[] = screen_text.toCharArray();
                    char after_delete[] = new char[screen_text.length()-1];
                    for (int i = 0; i < screen_text.length() - 1; i++) {
                        after_delete[i] = before_delete[i];
                    }
                    if (checkInt(second_value)){
                        isecond_value = Integer.parseInt(new String(after_delete));
                        screen_text = Integer.toString(isecond_value);
                        screen.setText(screen_text);
                        second_value = isecond_value;
                    }
                    else {
                        second_value = Float.parseFloat(new String(after_delete));
                        screen_text = Float.toString(second_value);
                        screen.setText(screen_text);
                    }
                }
                check = false;
                break;
        }

        if (decimal_reset){
            decimal_value = 1;
            power_value = 10;
        }
        if (check){

            if (second_value_check) {
                first_value = first_value * power_value + (Float.parseFloat(button_text)/decimal_value);
                Log.i(TAG, "First value: " + power_value);
                screen_text = Float.toString(first_value);
                screen.setText(screen_text);
//                if (checkInt(first_value)){
//                    screen.setText(convertToInt(ifirst_value, first_value, screen_text));
//                }

                if ((first_value%1) == 0) {
                    ifirst_value = Math.round(first_value);
                    screen_text = Integer.toString(ifirst_value);
                    screen.setText(screen_text);
                    first_value = ifirst_value;
                }
            }
            else{
                second_value = second_value * power_value + (Float.parseFloat(button_text)/decimal_value);
                Log.i(TAG, "Second value: " + second_value);
                screen_text = Float.toString(second_value);
                screen.setText(screen_text);

//                if (checkInt(second_value)){
//                    screen.setText(convertToInt(isecond_value, second_value, screen_text));
//                }
                if ((second_value%1) == 0) {
                    isecond_value = Math.round(second_value);
                    screen_text = Integer.toString(isecond_value);
                    screen.setText(screen_text);
                    second_value = isecond_value;
                }
            }
            if (decimal_value >= 10){
                decimal_value = decimal_value * 10;
            }
        }
        equals_count = false;
    }


    private boolean checkInt (float value){
        if ((value%1)== 0){
            return true;
        }
        else return false;
    }


    private int convertToInt (float fnumber){
         return Math.round(fnumber);
    }

    private String deleteScreenUpdate (float fnumber, int inumber, String screen_text, String after_delete){

        if (checkInt(fnumber)){
            inumber = Integer.parseInt(new String(after_delete));
            screen_text = Integer.toString(inumber);
            first_value = inumber;
        }
        else {
            first_value = Float.parseFloat(new String(after_delete));
            screen_text = Float.toString(first_value);
        }
        return screen_text;

    }

    public void clearClick(View view) {
        first_value = 0;
        second_value = 0;
        ifirst_value = 0;
        isecond_value = 0;
        power_value = 10;
        decimal_value = 1;
        second_value_check = true;
        operation_type = 0; // used to tell what operation is being conducted, 0 is simply when a number is pressed
        check = true; // to make sure only numbered button presses enter the number else if and not operation symbols
        equals_count = false; //when the equals button is clicked more than once to repeat the operation
        TextView screen = (TextView)findViewById(R.id.screen);
        String setText= "0";
        screen.setText(setText);
    }
}
