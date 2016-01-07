package com.rusrom.patientinformation;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ViewData extends AppCompatActivity {
    DatabaseHelper myDb;
    private static Button btnViewAll;
    private static Button btnViewById;
    private static Button btnViewByName;
    private static Button btnViewByFile;
    private static EditText etPatientId;
    private static EditText etPatientName;
    private static EditText etFileNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDb = new DatabaseHelper(this);
        btnViewAll = (Button) findViewById(R.id.buttonViewAll);
        btnViewById = (Button) findViewById(R.id.buttonFindByPId);
        btnViewByName = (Button) findViewById(R.id.buttonFindByName);
        btnViewByFile = (Button) findViewById(R.id.buttonFindByFile);

        etPatientId = (EditText) findViewById(R.id.editTextFPatientId);
        etPatientName = (EditText) findViewById(R.id.editTextFPatientName);
        etFileNo  = (EditText) findViewById(R.id.editTextFFileNo);
        view();

    }
    public void view(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            //show message
                            showMessage("Error","No Data found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("SN:" + res.getString(0) + "\n");
                            buffer.append("Patient Id :" + res.getString(1) + "\n");
                            String cleanName =  stringOperation(res.getString(2));
                            buffer.append("Name :" + cleanName + "\n");
                            buffer.append("File No. :" + res.getString(3) + "\n");
                            buffer.append("------------------------- \n");
                        }
                        //show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
        btnViewById.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = etPatientId.getText().toString();

                        Cursor res = myDb.getById(id);
                        if (res.getCount() == 0) {
                            //show message
                            showMessage("Information","No Data found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("SN:" + res.getString(0) + "\n");
                            buffer.append("Patient Id :" + res.getString(1) + "\n");
                            String cleanName =  stringOperation(res.getString(2));
                            buffer.append("Name :" + cleanName + "\n");
                            buffer.append("File No. :" + res.getString(3) + "\n");
                        }
                        //show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
        btnViewByName.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = etPatientName.getText().toString();
                        name = name.replaceAll(" ","_").toUpperCase();
                        Cursor res=null;
                        try{res = myDb.getByName(name);}catch(Exception e) {
                            Log.d("error2", "error ayo");
                            }
                        if (res.getCount() == 0) {
                            //show message
                            showMessage("Information","No Data found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("SN:" + res.getString(0) + "\n");
                            buffer.append("Patient Id :" + res.getString(1) + "\n");
                           String cleanName =  stringOperation(res.getString(2));
                            buffer.append("Name :" + cleanName + "\n");
                            buffer.append("File No. :" + res.getString(3) + "\n");

                        }
                        //show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
        btnViewByFile.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String fileno = etFileNo.getText().toString();
                        Cursor res = myDb.getByFileNo(fileno);
                        if (res.getCount() == 0) {
                            //show message
                            showMessage("Information", "No Data found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("SN:" + res.getString(0) + "\n");
                            buffer.append("Patient Id :" + res.getString(1) + "\n");
                            String cleanName =  stringOperation(res.getString(2));
                            buffer.append("Name :" + cleanName + "\n");
                            buffer.append("File No. :" + res.getString(3) + "\n");
                        }
                        //show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }
    public void showMessage (String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public String stringOperation(String dirt){
        String clean = dirt.replaceAll("_"," ");
        return clean;
    }
}
