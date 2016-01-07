package com.rusrom.patientinformation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DataEntry extends AppCompatActivity {
    DatabaseHelper myDb;
    private static Button submitData;
    private static EditText patientId;
    private static EditText patientName;
    private static EditText fileNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDb = new DatabaseHelper(this);
        submitData = (Button) findViewById(R.id.buttonSubmitData);
        patientId = (EditText) findViewById(R.id.editTextPId);
        patientName = (EditText) findViewById(R.id.editTextPName);
        fileNo = (EditText) findViewById(R.id.editTextPFile);
        addData();
    }

    private void addData() {
        submitData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = patientName.getText().toString();
                        String newName = name.replaceAll(" ","_").toUpperCase();
                       boolean isInserted = myDb.insertData(patientId.getText().toString(),newName,fileNo.getText().toString());
                        if(isInserted== true){
                            Toast.makeText(DataEntry.this,"Data Added",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(DataEntry.this,"Data Not Added",Toast.LENGTH_LONG).show();
                        }
                        // Intent intent = new Intent("com.rusrom.patientinformation.DataEntry");
                        //startActivity(intent);
                    }
                }
        );
    }

}
