package com.example.andre.turingmachinesample;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Input extends AppCompatActivity {

    EditText text_string;
    ListView listView;
    static int index, count;
    String result = "", error="";
    static TuringMachine TM;
    ArrayAdapter<String> adapter;

    ArrayList<String> simulationTexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        text_string = (EditText) findViewById(R.id.text_string);
        //text_string.setText("010000110101#010000110101");
        index = 0;
        count = 1;
        simulationTexts = new ArrayList<>();
        listView = (ListView)findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, simulationTexts);




    }


    public void inputStringEq1(View view){


        //text_string.setText("Input string: " + text_string.getText().toString());
        TM = MachinesLibrary.Equation1();
        try {



            String string = text_string.getText().toString();
            if (string.matches("[01]+") == false) {
                Toast.makeText(this, "Input only binary digits", Toast.LENGTH_SHORT).show();
                return;
            }


            boolean done = TM.Run(string, false);
            text_string.setEnabled(false);
            if (done == true) {

                result = "String accepted!";

            } else {

                result = "String rejected!";
                error = "Error: " + TuringMachine.error + "\n\n";
            }


            simulationTexts.add("(" + count + ")\t  " + TM.getSimulationList().get(index));
            adapter.notifyDataSetChanged();
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String selected = (String)adapterView.getItemAtPosition(i);
                    itemDialog(selected.substring(4),i+1);
                }
            });
            listView.setSelection(adapter.getCount() - 1);
            //index++;
            //count++;


        }catch(IndexOutOfBoundsException ex){
            count--;
            //resultDialog();
            verdictDialog();

        }
        index++;
        count++;

    }


    public void resultDialog(){
        String string = text_string.getText().toString();
        String line = "\n----------------------------------------------";

        new AlertDialog.Builder(this)
                .setTitle(result + line)
                .setMessage("Summary: \n\nString entered: " + string + "\n" + error + "Total steps: " +  count)
                .setNeutralButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        text_string.setText("");
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).show();



    }

    public void verdictDialog(){
        String string = text_string.getText().toString();
        LayoutInflater factory = LayoutInflater.from(Input.this);
        final View view = factory.inflate(R.layout.sample, null);

        final TextView txtAbove  = (TextView)view.findViewById(R.id.txtAbove);
        final TextView txtStepNo  = (TextView)view.findViewById(R.id.txtStepNo);
        final TextView txtSimText  = (TextView)view.findViewById(R.id.txtSimText);
        final ImageView img1 = (ImageView)view.findViewById(R.id.img1);

        img1.setImageResource(R.drawable.carl);
        txtAbove.setText(result);
        txtStepNo.setText("Summary: \n"+"String entered: " + string + "\n" + error);
        txtSimText.setText("Total steps: " +  count);

        new AlertDialog.Builder(this)

                .setView(view)
                .setNeutralButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        text_string.setText("");
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }).show();
    }

    public void itemDialog(String item, int count){
        LayoutInflater factory = LayoutInflater.from(Input.this);
        final View view = factory.inflate(R.layout.sample, null);
        final TextView txtApproved  = (TextView)view.findViewById(R.id.txtApproved);
        final TextView txtStepNo  = (TextView)view.findViewById(R.id.txtStepNo);
        final TextView txtSimText  = (TextView)view.findViewById(R.id.txtSimText);
        final ImageView img1 = (ImageView)view.findViewById(R.id.img1);

        img1.setImageResource(R.drawable.carl);
        txtApproved.setText("");
        txtStepNo.setText("Step # " + count);
        txtSimText.setText("Simulation text: \n" + item);

        new AlertDialog.Builder(this)

                .setView(view)
                .setNeutralButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();



        /*

        new AlertDialog.Builder(this)
                .setTitle("Step details")
                .setMessage("Step # " + count + "\nSimulation text: " + item)
                .setNeutralButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();

                */

    }




}
