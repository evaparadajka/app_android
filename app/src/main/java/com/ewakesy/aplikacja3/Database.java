package com.ewakesy.aplikacja3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;


public class Database extends SQLiteOpenHelper{

    public Database(Context context){
        super(context, "control.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table state(" + "id integer primary key autoincrement," + "name text," + "value text);" + "");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int OldVersion, int newVersion){
    }

    public void addVariable(State state){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", state.getName());
        values.put("value", state.getValue());
        db.insertOrThrow("state", null, values);
    }

    public void deleteVariable(int id){
        SQLiteDatabase db = getWritableDatabase();
        String[] args={""+id};
        db.delete("state", "id=?", args);
    }

    public void modifyVariable(State state){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", state.getName());
        values.put("value", state.getValue());
        String args[]={state.getId()+""};
        db.update("state", values, "id=?", args);
    }

    public State getVariable(int id){
        State state = new State();
        SQLiteDatabase db = getReadableDatabase();
        String[] columns={"id", "name", "value"};
        String args[]={id+""};
        Cursor cursor = db.query("state", columns, "id=?", args, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
            state.setId(cursor.getLong(0));
            state.setName(cursor.getString(1));
            state.setValue(cursor.getString(2));
        }
        return state;
    }

    public List<State> getAllVariables(){
        List<State> states = new LinkedList<State>();
        String[] columns={"id", "name", "value"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("state", columns, null, null, null, null, null);
        while(cursor.moveToNext()){
            State state = new State();
            state.setId(cursor.getLong(0));
            state.setName(cursor.getString(1));
            state.setValue(cursor.getString(2));
            states.add(state);
        }
        return states;
    }

    public List<State> getByName(String name){
        List<State> states = new LinkedList<State>();
        String[] columns={"id", "name", "value"};
        String args[]={name+""};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("state", columns, "name=?", args, null, null, null);
        while(cursor.moveToNext()){
            State state = new State();
            state.setId(cursor.getLong(0));
            state.setName(cursor.getString(1));
            state.setValue(cursor.getString(2));
            states.add(state);
        }
        return states;
    }
}
