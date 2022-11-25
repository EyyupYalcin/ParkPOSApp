package com.eyyup1516.parkpos1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eyyüp Yalçın on 10.05.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "park.db";
    public static final String TABLE_NAME = "otopark_table";
    public static final String Col_1 = "ID";
    public static final String Col_2 = "PLAKA";
    public static final String Col_3 = "GIRIS";
    public static final String Col_4 = "CIKIS";


    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, PLAKA TEXT" +
                ", GIRIS TEXT, CIKIS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }


    public boolean insertData(String plaka, String giris, String cikis){

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2, plaka);
        contentValues.put(Col_3, giris);
        contentValues.put(Col_4, cikis);


        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery("select * from "+TABLE_NAME,null);

        return result;
    }

    public boolean updateData(String id, String plaka, String giris, String cikis){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_1, id);
        contentValues.put(Col_2, plaka);
        contentValues.put(Col_3, giris);
        contentValues.put(Col_4, cikis);

        db.update(TABLE_NAME, contentValues, "ID = ?",new String[]{id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?", new String[]{id});
    }



}
