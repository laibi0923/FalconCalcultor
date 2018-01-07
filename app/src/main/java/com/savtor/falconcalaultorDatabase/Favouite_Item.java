package com.savtor.falconcalaultorDatabase;

import java.util.Set;

/**
 * Created by GhostLeo_DT on 30/11/2017.
 */
public class Favouite_Item {

    int ID;
    String Create_Date;
    String Product_Name;
    String Product_Status;
    String Product_Type;
    String Loan_No;

    double Loan_Amount;
    int Loan_Trems;
    double Loan_Rate;
    double Loan_Installment;

    String First_Due;
    String EOM_DueDate;
    int Setup_Alarm;
    String Alarm_Time;

    String Address;
    String Phone_No;
    String Remarks;


    public Favouite_Item(){
        String content = "";
    }

    public Favouite_Item(int ID, String Create_Date, String Product_Name, String Product_Status, String Product_Type, String Loan_No,
                         double Loan_Amount, int Loan_Trems, double Loan_Rate, double Loan_Installment,
                         String First_Due, String EOM_DueDate, int Setup_Alarm, String Alarm_Time,
                         String Addess, String Phone_No, String Remarks){

        this.ID = ID;
        this.Create_Date = Create_Date;
        this.Product_Name = Product_Name;
        this.Product_Status = Product_Status;
        this.Product_Type = Product_Type;
        this.Loan_No = Loan_No;
        this.Loan_Amount = Loan_Amount;
        this.Loan_Trems = Loan_Trems;
        this.Loan_Rate = Loan_Rate;
        this.Loan_Installment = Loan_Installment;
        this.First_Due = First_Due;
        this.EOM_DueDate = EOM_DueDate;
        this.Setup_Alarm = Setup_Alarm;
        this.Alarm_Time = Alarm_Time;
        this.Address = Addess;
        this.Phone_No = Phone_No;
        this.Remarks = Remarks;

    }

    //=============================================================================================
    // [1] ID

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    //=============================================================================================
    // [2] Create Date

    public String getCreate_Date() {
        return Create_Date;
    }

    public void setCreate_Date(String create_Date) {
        Create_Date = create_Date;
    }

    //=============================================================================================
    // [3] Product Name

    public String getProduct_Name() {
        return Product_Name;
    }

    public void setProduct_Name(String product_Name) {
        Product_Name = product_Name;
    }

    //=============================================================================================
    // [4] Product Status

    public String getProduct_Status() {
        return Product_Status;
    }

    public void setProduct_Status(String product_Status) {
        Product_Status = product_Status;
    }

    //=============================================================================================
    // [5] Product Type

    public String getProduct_Type() {
        return Product_Type;
    }

    public void setProduct_Type(String product_Type) {
        Product_Type = product_Type;
    }
    //=============================================================================================
    // [6] Loan_No

    public String getLoan_No() {
        return Loan_No;
    }

    public void setLoan_No(String loan_No) {
        Loan_No = loan_No;
    }
    //=============================================================================================
    // [7] Loan Amount

    public double getLoan_Amount() {
        return Loan_Amount;
    }

    public void setLoan_Amount(double loan_Amount) {
        Loan_Amount = loan_Amount;
    }
    //=============================================================================================
    // [8] Loan Trems

    public int getLoan_Trems() {
        return Loan_Trems;
    }

    public void setLoan_Trems(int loan_Trems) {
        Loan_Trems = loan_Trems;
    }
    //=============================================================================================
    // [9] Loan Rate

    public double getLoan_Rate() {
        return Loan_Rate;
    }

    public void setLoan_Rate(double loan_Rate) {
        Loan_Rate = loan_Rate;
    }
    //=============================================================================================
    // [10] Loan Installment

    public double getLoan_Installment() {
        return Loan_Installment;
    }

    public void setLoan_Installment(double loan_Installment) {
        Loan_Installment = loan_Installment;
    }

    //=============================================================================================
    // [11] First Due

    public String getFirst_Due() {
        return First_Due;
    }

    public void setFirst_Due(String first_Due) {
        First_Due = first_Due;
    }
    //=============================================================================================
    // [12] EOM DueDate

    public String getEOM_DueDate() {
        return EOM_DueDate;
    }

    public void setEOM_DueDate(String EOM_DueDate) {
        this.EOM_DueDate = EOM_DueDate;
    }

    //=============================================================================================
    // [13] Setup Alarm

    public int getSetup_Alarm() {
        return Setup_Alarm;
    }

    public void setSetup_Alarm(int setup_Alarm) {
        Setup_Alarm = setup_Alarm;
    }

    //=============================================================================================
    // [14] Alarm Time

    public String getAlarm_Time() {
        return Alarm_Time;
    }

    public void setAlarm_Time(String alarm_Time) {
        Alarm_Time = alarm_Time;
    }

    //=============================================================================================
    // [15] Address

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    //=============================================================================================
    // [16] Phone

    public String getPhone_No() {
        return Phone_No;
    }

    public void setPhone_No(String phone_No) {
        Phone_No = phone_No;
    }

    //=============================================================================================
    // [17] Remarks

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }
}
