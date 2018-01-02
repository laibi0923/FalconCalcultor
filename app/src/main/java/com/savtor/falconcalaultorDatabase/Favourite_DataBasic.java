package com.savtor.falconcalaultorDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by GhostLeo_DT on 30/11/2017.
 */
public class Favourite_DataBasic {

    // 表格名稱
    public static final String TABLE_NAME = "favourite";

    // 編號表格欄位名稱，固定不變, 共15項
    public static final String KEY_ID = "_id";

    public static final String CREATE_DATE_COULUMN = "CreateDate";
    public static final String NAME_COLUMN = "Name";
    public static final String LOAN_TYPE_COULUMN = "LoanType";
    public static final String APPLY_STATUS_COULUMN = "ApplyStatus";
    public static final String LOAN_NUM_COULUMN = "LoanNum";

    public static final String AMOUNT_COLUMN = "LoanAmount";
    public static final String TREMS_COULUMN = "LoanTrems";
    public static final String RATE_COULUMN = "LoanRate";

    public static final String FIRST_DUEDATE_COULUMN = "Firstduedate";
    public static final String FINAL_DUEDATE_COULUMN = "Finalduedate";
    public static final String ALERT_DATE_COULUMN = "AlertDate";
    public static final String ALERT_TIME_COULUMN = "AlertTime";


	public static final String ADDRESS_COULUMN = "Address";
    public static final String PHONE_COULUMN = "Phone";
    public static final String REMARKS_COULUMN = "Remarks";

    // 資料庫物件
    public SQLiteDatabase db;

    // 新建 Table
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CREATE_DATE_COULUMN + " DATETIME DEFAULT (datetime('now', 'localtime')), " +
            NAME_COLUMN + " TEXT," +
            LOAN_TYPE_COULUMN + " INTEGER," +
            APPLY_STATUS_COULUMN + " INTEGER," +
            LOAN_NUM_COULUMN + " TEXT," +

            AMOUNT_COLUMN + " DOUBLE," +
            TREMS_COULUMN + " INTEGER," +
            RATE_COULUMN + " DOUBLE," +

            FIRST_DUEDATE_COULUMN + " DATETIME," +
            FINAL_DUEDATE_COULUMN + " DATETIME," +
            ALERT_DATE_COULUMN + " INTEGER," +
            ALERT_TIME_COULUMN + " DATETIME, " +

            ADDRESS_COULUMN + " TEXT," +
			PHONE_COULUMN + " TEXT," +
            REMARKS_COULUMN + " TEXT)";




    //=============================================================================================
    // 建構子，一般的應用都不需要修改
    public Favourite_DataBasic(Context context, String open_from){

        db = myDBhelper.getDatabase(context);

        Log.e("DATA BASIC ACTION : ", "從" + open_from + "打開資料庫"); // [Log.e]
    }

    //=============================================================================================
    // [1] INSERT - 新增參數指定的物件
    public Favouite_Item inster(Favouite_Item item){

        ContentValues cv = new ContentValues();
//
        cv = get_ContenValue(item);

        db.insert(TABLE_NAME, null, cv);
        Log.e("DATA BASIC ACTION : ","數據庫新增1條紀錄");  // [Log.e]

        return item;
    }

    //=============================================================================================
    // [2] UPDATE - 修改
    public boolean update(Favouite_Item item){

        ContentValues cv = new ContentValues();

        String where =  KEY_ID + "=" + item.getid();

        cv = get_ContenValue(item);

        Log.e("DATA BASIC ACTION : ", "更新 " + where + "的紀錄"); // [Log.e]

        return db.update(TABLE_NAME, cv, where, null) > 0;
    }

    //=============================================================================================
    // [3] DELETE - 刪除
    public boolean delete(int id){

        String where = KEY_ID + "=" + id;

        Log.e("DATA BASIC ACTION : ","數據庫刪除1條紀錄, 位於" + where);  // [Log.e]

        return db.delete(TABLE_NAME, where, null) > 0;
    }

    //=============================================================================================
    // [4] QUERY - 查詢一條紀錄
    public Favouite_Item query(long id){

        Favouite_Item item = null;

        String where = KEY_ID + "=" + id;

        Cursor cursor =  db.query(TABLE_NAME, null, where, null, null, null, null, null);

        if (cursor.moveToFirst()){
            item = get_database_record(cursor);
        }

        cursor.close();

        Log.e("DATA BASIC ACTION : ","查詢一條紀錄, 位於" + where);  // [Log.e]

        return item;
    }

    //=============================================================================================
    // [5] QUERY ALL - 查詢所有紀錄
    public List<Favouite_Item> query_all(){

        List <Favouite_Item> result = new ArrayList<Favouite_Item>();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);

        while (cursor.moveToNext()){
            result.add(get_database_record(cursor));
        }

        cursor.close();

        Log.e("DATA BASIC ACTION : ","查詢所有紀錄, 一共" + getCount() + "條");  // [Log.e]

        return result;
    }

    //=============================================================================================
    // [5] QUERY ALL ORDER BY DATE - 查詢所有紀錄
    public List<Favouite_Item> query_orderby_date(){

        List <Favouite_Item> result = new ArrayList<Favouite_Item>();

        // ASC = 由小至大 ; DESC = 由大至小
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, CREATE_DATE_COULUMN + "  DESC");


        while (cursor.moveToNext()){
            result.add(get_database_record(cursor));
        }

        cursor.close();

        Log.e("DATA BASIC ACTION : ","查詢所有紀錄, 一共" + getCount() + "條");  // [Log.e]

        return result;
    }

	//=============================================================================================
    // [5] QUERY ALL ORDER BY DATE - 查詢所有紀錄
    public int query_max_id(){

        int max_id = 0;

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);

        if (cursor.moveToLast()){
            max_id = cursor.getInt(0);
        }

        cursor.close();

        Log.e("DATA BASIC ACTION : ","查詢最新ID" + max_id);  // [Log.e]

        return max_id;
    }
	
    //=============================================================================================
    // [6] 被[5]所用
    public Favouite_Item get_database_record(Cursor cusor){

        Favouite_Item item = new Favouite_Item();

        item.setid(cusor.getInt(0));
        item.setCreate_date(cusor.getString(1));

        item.setName(cusor.getString(2));
        item.setLoan_Type(cusor.getInt(3));
        item.setApply_status(cusor.getInt(4));
        item.setLoanNum(cusor.getString(5));

        item.setLaon_Amount(cusor.getDouble(6));
        item.setTrems(cusor.getInt(7));
        item.setLoan_Rate(cusor.getDouble(8));

        item.setFirst_duedate(cusor.getString(9));
        item.setFinal_duedate(cusor.getString(10));
        item.setAlert_date(cusor.getInt(11));
        item.setAlert_time(cusor.getString(12));

        item.setAddress(cusor.getString(13));
        item.setPhone(cusor.getString(14));
        item.setRemarks(cusor.getString(15));



        return item;
    }

    //=============================================================================================
    // [7] 被[1], [2]所用
    public ContentValues get_ContenValue(Favouite_Item item){

        ContentValues cv = new ContentValues();

        cv.put(CREATE_DATE_COULUMN, item.getCreate_date());

        cv.put(NAME_COLUMN, item.getName());
        cv.put(LOAN_TYPE_COULUMN, item.getLoan_Type());
        cv.put(APPLY_STATUS_COULUMN, item.getApply_status());
        cv.put(LOAN_NUM_COULUMN, item.getLoanNum());

        cv.put(AMOUNT_COLUMN, item.getLoan_Amount());
        cv.put(TREMS_COULUMN, item.getTrems());
        cv.put(RATE_COULUMN, item.getLoan_Rate());


        cv.put(FIRST_DUEDATE_COULUMN, item.getFirst_dueddate());
        cv.put(FINAL_DUEDATE_COULUMN, item.getFinal_dueddate());
        cv.put(ALERT_DATE_COULUMN, item.getAlert_date());
        cv.put(ALERT_TIME_COULUMN, item.getAlert_time());


        cv.put(ADDRESS_COULUMN, item.getAddress());
        cv.put(PHONE_COULUMN, item.getPhone());
        cv.put(REMARKS_COULUMN, item.getRemarks());



        return cv;
    }

    //=============================================================================================
    // [8] 取得資料大小
    public int getCount() {

        int result = 0;

        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }

    //=============================================================================================
    // [9] 取得資料大小
    public void close() {
        db.close();
    }


}
