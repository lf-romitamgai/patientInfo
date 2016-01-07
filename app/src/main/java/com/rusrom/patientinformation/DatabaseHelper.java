package com.rusrom.patientinformation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by romit on 11/15/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    Context con;
    public static final String DATABASE_NAME = "patient1.db";
    public static final String TABLE_NAME = "patient_table";
    public static final String colID = "ID";
    public static final String colPatientId = "PATIENTID";
    public static final String colPatientName = "NAME";
    public static final String colFileNo = "FILENO";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        con = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, PATIENTID INTEGER, NAME TEXT, FILENO INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TABLE_NAME");
        onCreate(db);
    }

    public boolean insertData(String pId,String name, String PfileNo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(colPatientId,pId);
        contentValues.put(colPatientName,name);
        contentValues.put(colFileNo, PfileNo);
        Long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select *  from "+ TABLE_NAME,null);
        return res;
    }
    public Cursor getById(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME+" where PATIENTID="+id,null);
        return res;
    }
    public Cursor getByName(String nam){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = null;
        try {
            res = db.rawQuery("select * from " + TABLE_NAME+ " where NAME=\""+nam+"\"",null);
        }catch (Exception e){
            Log.d("error1",nam);
        }
        return res;
    }
    public Cursor getByFileNo(String fileno){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME+" where FILENO="+fileno,null);
        return res;
    }
}
