package com.example.veraj.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "com.example.veraj.calculator";

    Button[][] buttons = new Button [5][4];
    int first_value = 0;
    int second_value = 0;
    int power_value = 10;
    boolean next_value = true;
    int operation_type = 0; // used to tell what operation is being conducted, 0 is simply when a number is pressed
    boolean check = true; // to make sure only numbered button presses enter the number else if


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

        TextView screen = (TextView)findViewById(R.id.screen);
        Button button = (Button)view;
        String screen_text = "";

        String button_text = button.getText().toString();
        Log.i(TAG, "Button pressed:" + button_text);


        if (Objects.equals(button_text, "√")){
            check = false;

        }
        else if (Objects.equals(button_text, "%")){
            check = false;

        }
        else if (Objects.equals(button_text, "del")){
            check = false;

        }
        else if (Objects.equals(button_text, "+/-")){
            check = false;

        }
        else if (Objects.equals(button_text, "*")){ //operation type: 1
            screen.setText(screen_text);
            next_value = !next_value;
            operation_type = 1;
            check = false;

        }
        else if (Objects.equals(button_text, "/")){//operation type: 2
            check = false;

        }
        else if (Objects.equals(button_text, "-")){//operation type: 3
            check = false;

        }
        else if (Objects.equals(button_text, "+")){//operation type: 4
            check = false;

        }
        else if (Objects.equals(button_text, "=")){
            screen.setText(screen_text);
            if (operation_type == 1){
                screen_text = Integer.toString(first_value*second_value);
                screen.setText(screen_text);
            }
            else if (operation_type == 2){

            }
            else if (operation_type == 3){

            }
            else if (operation_type == 4){

            }
            else if (operation_type == 5){

            }
        }
        else if (check){
            if (next_value) {
                first_value = first_value * power_value + Integer.parseInt(button_text);
                Log.i(TAG, "First value: " + first_value);
                screen_text = Integer.toString(first_value);
                screen.setText(screen_text);
            }
            else{
                second_value = second_value*power_value + Integer.parseInt(button_text);
                Log.i(TAG, "Second value: " + second_value);
                screen_text = Integer.toString(second_value);
                screen.setText(screen_text);
            }
        }



    }
}
