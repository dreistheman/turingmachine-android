package com.example.andre.turingmachinesample;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    Button btnEq1, btnEq2, btnEq3;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //First button:
        btnEq1 = (Button)findViewById(R.id.btnEq1);

        //Second button:
        btnEq2 = (Button)findViewById(R.id.btnEq2);


        //Third button
        btnEq3 = (Button)findViewById(R.id.btnEq3);



    }

    public void showInputActivity1(View v){
        intent = new Intent(getBaseContext(),Input.class);
        startActivity(intent);

    }
    public void showInputActivity2(View v){
        intent = new Intent(getBaseContext(),Input2.class);
        startActivity(intent);

    }
    public void showInputActivity3(View v){
        intent = new Intent(getBaseContext(),Input3.class);
        startActivity(intent);

    }

}
