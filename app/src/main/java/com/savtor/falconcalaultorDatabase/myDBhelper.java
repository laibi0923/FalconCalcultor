package com.savtor.falconcalaultorDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by GhostLeo_DT on 30/11/2017.
 */
public class myDBhelper extends SQLiteOpenHelper {

    // Data Base 名稱
    public static final String DATABASE_NAME = "falcon_db_dummyy.db";

    // Data base 版本
    public static final int VERSION = 3;

    private static SQLiteDatabase database;

    public myDBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // 需要資料庫的元件呼叫這個方法，這個方法在一般的應用都不需要修改
    public static SQLiteDatabase getDatabase(Context context){

        if(database == null || !database.isOpen()){
            database = new myDBhelper(context, DATABASE_NAME, null, VERSION).getWritableDatabase();
        }

        return database;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 建立要存放資料的資料表格
        // 1. SQL語法不分大小寫
        // 2. 這裡大寫代表的是SQL標準語法, 小寫字是資料表/欄位的命名
        // 建立應用程式需要的表格
        db.execSQL(Favourite_DataBasic.CREATE_TABLE);
        Log.d("123", Favourite_DataBasic.CREATE_TABLE + "");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 刪除原有的表格
        db.execSQL("DROP TABLE IF EXISTS " + Favourite_DataBasic.TABLE_NAME);
        // 呼叫onCreate建立新版的表格
        onCreate(db);

    }

}
