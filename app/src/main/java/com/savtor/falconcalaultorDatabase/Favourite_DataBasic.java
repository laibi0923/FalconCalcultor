package com.savtor.falconcalaultorDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import com.savtor.falconcalcultorFavoutive.*;

/**
 * Created by GhostLeo_DT on 30/11/2017.
 */
public class Favourite_DataBasic {

    // 表格名稱
    public static final String TABLE_NAME = "favourite";

    // 編號表格欄位名稱，固定不變
    public static final String KEY_ID = "_id";

    public static final String CREATE_DATE_COULUMN = "CreateDate";
    public static final String NAME_COLUMN = "Name";
    public static final String AMOUNT_COLUMN = "LoanAmount";
    public static final String TREMS_COULUMN = "LoanTrems";
    public static final String RATE_COULUMN = "LoanRate";
    public static final String APPLY_STATUS_COULUMN = "ApplyStatus";
    public static final String LOAN_TYPE_COULUMN = "LoanType";
    public static final String FIRST_DUEDATE_COULUMN = "Firstduedate";
    public static final String FINAL_DUEDATE_COULUMN = "Finalduedate";
    public static final String DUEDATE_TYPE_COULUMN = "Duedatetype";
    public static final String ALERT_DATE = "Alertdate";
    public static final String REMARKS_COULUMN = "Remarks";
	public static final String LOAN_NUM_COULUMN = "LoanNum";
	public static final String ADDRESS_COULUMN = "Address";

    // 新建 Table
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CREATE_DATE_COULUMN + " TEXT, " +
            NAME_COLUMN + " TEXT," +
            AMOUNT_COLUMN + " DOUBLE," +
            TREMS_COULUMN + " INTEGER," +
            RATE_COULUMN + " DOUBLE," +
            APPLY_STATUS_COULUMN + " INTEGER," +
            LOAN_TYPE_COULUMN + " INTEGER," +
            FIRST_DUEDATE_COULUMN + " TEXT," +
            FINAL_DUEDATE_COULUMN + " TEXT," +
            DUEDATE_TYPE_COULUMN + " TEXT," +
            ALERT_DATE + " INTEGER, " +
            REMARKS_COULUMN + " TEXT," + 
			LOAN_NUM_COULUMN + " TEXT," +
			ADDRESS_COULUMN + " TEXT)";

    // 資料庫物件
    private SQLiteDatabase db;

    // 建構子，一般的應用都不需要修改
    public Favourite_DataBasic(Context context){
        db = myDBhelper.getDatabase(context);
    }


    // 關閉資料庫
    public void close() {
        db.close();
    }

    // 新增參數指定的物件
    public Favouite_Item inster(Favouite_Item values){

        ContentValues cv = new ContentValues();

        // KEY_ID 為 Autoincrement, 不用新增
        cv.put(CREATE_DATE_COULUMN, values.getCreate_date());
        cv.put(NAME_COLUMN, values.getName());
        cv.put(AMOUNT_COLUMN, values.getLoan_Amount());
        cv.put(TREMS_COULUMN, values.getTrems());
        cv.put(RATE_COULUMN, values.getLoan_Rate());
        cv.put(APPLY_STATUS_COULUMN, values.getApply_status());
        cv.put(LOAN_TYPE_COULUMN, values.getLoan_Type());
        cv.put(FIRST_DUEDATE_COULUMN, values.getFirst_dueddate());
        cv.put(FINAL_DUEDATE_COULUMN, values.getFinal_dueddate());
        cv.put(DUEDATE_TYPE_COULUMN, values.getDuedate_type());
        cv.put(ALERT_DATE, values.getAlert_date());
        cv.put(REMARKS_COULUMN, values.getRemarks());
		cv.put(LOAN_NUM_COULUMN, values.getLoanNum());
		cv.put(ADDRESS_COULUMN, values.getAddress());

        db.insert(TABLE_NAME, null, cv);
        Log.e("DATA BASIC ACTION : ","數據庫新增1條紀錄");  // [Log.e]

        return values;
    }

    //=============================================================================================
    // 刪除
    public boolean delete(int id){

        String where = KEY_ID + "=" + id;

        Log.e("DATA BASIC ACTION : ","數據庫刪除1條紀錄, 位於" + where);  // [Log.e]

        return db.delete(TABLE_NAME, where, null) > 0;
    }

    //=============================================================================================
    // 修改
    public boolean update(Favouite_Item item){

        ContentValues cv = new ContentValues();

        String where =  KEY_ID + "=" + item.getid();

        cv.put(CREATE_DATE_COULUMN, item.getCreate_date());
        cv.put(NAME_COLUMN, item.getName());
        cv.put(NAME_COLUMN, item.getName());
        cv.put(AMOUNT_COLUMN, item.getLoan_Amount());
        cv.put(TREMS_COULUMN, item.getTrems());
        cv.put(RATE_COULUMN, item.getLoan_Rate());
        cv.put(RATE_COULUMN, item.getLoan_Rate());
        cv.put(APPLY_STATUS_COULUMN, item.getApply_status());
        cv.put(FIRST_DUEDATE_COULUMN, item.getFirst_dueddate());
        cv.put(FINAL_DUEDATE_COULUMN, item.getFinal_dueddate());
        cv.put(DUEDATE_TYPE_COULUMN, item.getDuedate_type());
        cv.put(ALERT_DATE, item.getAlert_date());
        cv.put(REMARKS_COULUMN, item.getRemarks());
		cv.put(LOAN_NUM_COULUMN, item.getLoanNum());
		cv.put(ADDRESS_COULUMN, item.getAddress());


        return db.update(TABLE_NAME, cv, where, null) > 0;
		
    }
	

    //=============================================================================================
    // 查詢
    public Favouite_Item query(long id){

        Favouite_Item item = null;

        String where = KEY_ID + "=" + id;

        Cursor cursor =  db.query(TABLE_NAME, null, where, null, null, null, null, null);

        if (cursor.moveToFirst()){
            item = get_database_record(cursor);
        }

        cursor.close();
        return item;
    }

    public List<Favouite_Item> query_all(){

        List <Favouite_Item> result = new ArrayList<Favouite_Item>();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);

        while (cursor.moveToNext()){
            result.add(get_database_record(cursor));
        }

        cursor.close();
        return result;
    }

    public Favouite_Item get_database_record(Cursor cusor){

        Favouite_Item item = new Favouite_Item();

        item.setid(cusor.getInt(0));
        item.setCreate_date(cusor.getString(1));
        item.setName(cusor.getString(2));
        item.setLaon_Amount(cusor.getDouble(3));
        item.setTrems(cusor.getInt(4));
        item.setLoan_Rate(cusor.getDouble(5));
        item.setApply_status(cusor.getInt(6));
        item.setLoan_Type(cusor.getInt(7));
        item.setFirst_duedate(cusor.getString(8));
        item.setFinal_duedate(cusor.getString(9));
        item.setDuedate_type(cusor.getString(10));
        item.setAlert_date(cusor.getInt(11));
        item.setRemarks(cusor.getString(12));


        return item;
    }


    //=============================================================================================
    // 取得資料數量
    public int getCount() {

        int result = 0;

        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }

	public ContentValues get_ContenValue(Favouite_Item item){
		
		ContentValues cv = new ContentValues();
		
		cv.put(CREATE_DATE_COULUMN, item.getCreate_date());
        cv.put(NAME_COLUMN, item.getName());
        cv.put(AMOUNT_COLUMN, item.getLoan_Amount());
        cv.put(TREMS_COULUMN, item.getTrems());
        cv.put(RATE_COULUMN, item.getLoan_Rate());
        cv.put(APPLY_STATUS_COULUMN, item.getApply_status());
        cv.put(FIRST_DUEDATE_COULUMN, item.getFirst_dueddate());
        cv.put(FINAL_DUEDATE_COULUMN, item.getFinal_dueddate());
        cv.put(DUEDATE_TYPE_COULUMN, item.getDuedate_type());
        cv.put(ALERT_DATE, item.getAlert_date());
        cv.put(REMARKS_COULUMN, item.getRemarks());
		cv.put(LOAN_NUM_COULUMN, item.getLoanNum());
		cv.put(ADDRESS_COULUMN, item.getAddress());
		
		return cv;
	}

}
