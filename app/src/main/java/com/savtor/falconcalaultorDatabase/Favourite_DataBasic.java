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

    // 編號表格欄位名稱，固定不變, 共18項
    public static final String KEY_ID = "_id";

    public static final String CREATE_DATE_COULUMN = "CreateDate";
    public static final String PRODUCT_NAME_COLUMN = "ProductName";
    public static final String PRODUCT_TYPE_COULUMN = "ProductType";
    public static final String PRODUCT_STATUS_COULUMN = "ProductStatus";
    public static final String LOAN_NUM_COULUMN = "LoanNum";

    public static final String AMOUNT_COLUMN = "LoanAmount";
    public static final String TREMS_COULUMN = "LoanTrems";
    public static final String RATE_COULUMN = "LoanRate";
    public static final String INSTALLMENT_COULUMN = "LoanInstallment";

    public static final String FIRST_DUEDATE_COULUMN = "Firstduedate";
	public static final String FINAL_DUEDATE_COULUMN = "Finalduedate";
	
    public static final String EOM_DUEDATE_COULUMN = "EOMduedate";
    public static final String ALERT_DATE_COULUMN = "AlertDate";
    public static final String ALERT_TIME_COULUMN = "AlertTime";

	public static final String ADDRESS_COULUMN = "Address";
    public static final String PHONE_COULUMN = "Phone";
    public static final String REMARKS_COULUMN = "Remarks";

	public static final String REQUEST_CODE_COULUMN = "RequestCode";
    // 資料庫物件
    public SQLiteDatabase db;

    // 新建 Table
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CREATE_DATE_COULUMN + " DATETIME DEFAULT (datetime('now', 'localtime')), " +
            PRODUCT_NAME_COLUMN + " TEXT," +
            PRODUCT_TYPE_COULUMN + " TEXT," +
            PRODUCT_STATUS_COULUMN + " TEXT," +
            LOAN_NUM_COULUMN + " TEXT," +

            AMOUNT_COLUMN + " DOUBLE," +
            TREMS_COULUMN + " INTEGER," +
            RATE_COULUMN + " DOUBLE," +
            INSTALLMENT_COULUMN + " DOUBLE," +

            FIRST_DUEDATE_COULUMN + " DATETIME," +
			FINAL_DUEDATE_COULUMN + " DATETIME," +
			
            EOM_DUEDATE_COULUMN + " TEXT," +
            ALERT_DATE_COULUMN + " INTEGER," +
            ALERT_TIME_COULUMN + " DATETIME, " +

            ADDRESS_COULUMN + " TEXT," +
			PHONE_COULUMN + " TEXT," +
            REMARKS_COULUMN + " TEXT," +
			REQUEST_CODE_COULUMN + " INTEGER)";




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

        String where =  KEY_ID + "=" + item.getID();

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
    //
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

        item.setID(cusor.getInt(0));
        item.setCreate_Date(cusor.getString(1));

        item.setProduct_Name(cusor.getString(2));
        item.setProduct_Type(cusor.getString(3));
        item.setProduct_Status(cusor.getString(4));
        item.setLoan_No(cusor.getString(5));

        item.setLoan_Amount(cusor.getDouble(6));
        item.setLoan_Trems(cusor.getInt(7));
        item.setLoan_Rate(cusor.getDouble(8));
        item.setLoan_Installment(cusor.getDouble(9));

        item.setFirst_Due(cusor.getString(10));
		item.setFinal_Due(cusor.getString(11));
        item.setEOM_DueDate(cusor.getString(12));
        item.setSetup_Alarm(cusor.getInt(13));
        item.setAlarm_Time(cusor.getString(14));

        item.setAddress(cusor.getString(15));
        item.setPhone_No(cusor.getString(16));
        item.setRemarks(cusor.getString(17));
		item.setRequestCode(cusor.getInt(18));
		
        return item;
    }

    //=============================================================================================
    // [7] 被[1], [2]所用
    public ContentValues get_ContenValue(Favouite_Item item){

        ContentValues cv = new ContentValues();

        cv.put(CREATE_DATE_COULUMN, item.getCreate_Date());

        cv.put(PRODUCT_NAME_COLUMN, item.getProduct_Name());
        cv.put(PRODUCT_TYPE_COULUMN, item.getProduct_Type());
        cv.put(PRODUCT_STATUS_COULUMN, item.getProduct_Status());
        cv.put(LOAN_NUM_COULUMN, item.getLoan_No());

        cv.put(AMOUNT_COLUMN, item.getLoan_Amount());
        cv.put(TREMS_COULUMN, item.getLoan_Trems());
        cv.put(RATE_COULUMN, item.getLoan_Rate());
        cv.put(INSTALLMENT_COULUMN, item.getLoan_Installment());


        cv.put(FIRST_DUEDATE_COULUMN, item.getFirst_Due());
		cv.put(FINAL_DUEDATE_COULUMN, item.getFinal_Due());
        cv.put(EOM_DUEDATE_COULUMN, item.getEOM_DueDate());
        cv.put(ALERT_DATE_COULUMN, item.getSetup_Alarm());
        cv.put(ALERT_TIME_COULUMN, item.getAlarm_Time());


        cv.put(ADDRESS_COULUMN, item.getAddress());
        cv.put(PHONE_COULUMN, item.getPhone_No());
        cv.put(REMARKS_COULUMN, item.getRemarks());
		cv.put(REQUEST_CODE_COULUMN, item.getRequestCode());


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
