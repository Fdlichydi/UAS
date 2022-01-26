package com.example.uas_fadli_19100104;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String TAG ="todolist";

    private static final String todolist="todolist";
    private static final String getCol1="ID";
    private static final String getCol2="Nama";
    private static final String getCol3="Tanggal";
    private static final String getGetCol4="Waktu";

    public DatabaseHelper(Context context){
        super(context,todolist,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable ="CREATE TABLE"+todolist+"("+getCol1+"integer primary key,"+getCol2+"TEXT,"+getCol3+"DATE,"+getGetCol4+"TIME"+")";
        Log.d(TAG,"Creating table"+createTable);
        db.execSQL(createTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS EXISTtodolist"+todolist);
        onCreate(db);
    }
    //memasukan data ke database
    public boolean insertData(String Nama, String Tanggal, String Waktu){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(getCol2,Nama);
        contentValues.put(getCol3,Tanggal);
        contentValues.put(getGetCol4,Waktu);
        Log.d(TAG,"insertData:Inserting"+Nama+"to"+todolist);
        long result=db.insert(todolist,null,contentValues);
        db.close();
        return result!=-1;
    }
    //menghapus data dari database
    void deleteData(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        final int delete = db.delete(todolist, getCol1 + id, null);
    }
    //memuat semua data ke listview
    public ArrayList<ModelData>getAllData(){
        ArrayList<ModelData>arrayList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT FROMtodolist";
        @SuppressLint("Recycle")
                Cursor cursor=db.rawQuery(query,null);
        while (cursor.moveToNext()){
            int id= cursor.getInt(0);
            String nama= cursor.getString(1);
            String tanggal= cursor.getString(2);
            String waktu= cursor.getString(3);
            ModelData modelData=new ModelData(id,nama,tanggal,waktu);
            arrayList.add(modelData);
        }
        db.close();
        return arrayList;
    }
}

