package com.example.ljh.liangminghui.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ljh.liangminghui.application.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库管理类
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String SQLITE_NAME = "ljh";
    private static final int VERSION = 1;
    private SQLiteDatabase mSqliteDatabase;
    private static SQLiteHelper mSQLiteHelper;

    public static SQLiteHelper getInstance(){
        if(mSQLiteHelper == null){
            synchronized(SQLiteHelper.class){
                if(mSQLiteHelper == null){
                    mSQLiteHelper = new SQLiteHelper(MyApplication.getInstance(),null);
                }
            }
        }
        return mSQLiteHelper;
    }

    public SQLiteHelper(Context context, SQLiteDatabase.CursorFactory factory){
        this(context,SQLITE_NAME,factory,VERSION);
    }

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE history(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "content VARCHAR)");
        SharedPreferences sp = MyApplication.getInstance().getSharedPreferences("lmh",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username","梁铭辉");
        editor.commit();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<String> getSearchHistory(){
        mSqliteDatabase = getReadableDatabase();
        Cursor cursor = mSqliteDatabase.rawQuery("SELECT * FROM history",null);
        List<String> list = new ArrayList<>();
        while (cursor.moveToNext()){
            list.add(cursor.getString(cursor.getColumnIndex("content")));
        }
        mSqliteDatabase.close();
        return list;
    }

    public void insertSearchHistory(String s){
        mSqliteDatabase = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("content",s);
        mSqliteDatabase.insert("history",null,contentValues);
        mSqliteDatabase.close();
    }
}
