package com.example.toledano.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by toledano on 10/10/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL( "create table names " + "(fname text, lname text)" );

    }

    public boolean insertItem (String fname, String lname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fname", fname);
        contentValues.put("lname", lname);
        db.insert("names", null, contentValues);
        return true;
    }

    public ArrayList<ArrayList<String>> getAllNames() {

        ArrayList<ArrayList<String>> out = new ArrayList<ArrayList<String>>();
        ArrayList<String> in = new ArrayList<String>();
        String[] columns = {"fname","lname"};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.query("names",columns,null,null,null,null,null);


        if (res.moveToFirst())
        {
            do
            {
                in = new ArrayList<String>();
                for(int i=0; i<res.getColumnCount(); i++)
                {
                    in.add( res.getString(i) );
                }
                out.add(in);
            } while (res.moveToNext());
        }
        if (res != null && !res.isClosed()) {
            res.close();
        }
        return out;
    }


//    public ArrayList<String> getAllNames() {
//
//        ArrayList<String> array_list = new ArrayList<String>();
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from names", null );
//        res.moveToFirst();
//
//        while(!res.isAfterLast()){
//            array_list.add(res.getString(res.getColumnIndex("fname")));
//            res.moveToNext();
//
//        }
//        return array_list;
//    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
