package com.savtor.falconcalaultorDatabase;

/**
 * Created by GhostLeo_DT on 30/11/2017.
 */
public class Favouite_Item {

    int id;
    String create_date;
    String name;
    int apply_status;
    int loan_type;
    String loan_num;

    double loan_amount;
    int loan_trems;
    double loan_rate;

    String first_duedate;
    String final_duedate;
    int alert_date_type;
    String alert_time;

    String address;
    String phone;
    String remarks;


    public Favouite_Item(){
        String content = "";
    }

    public Favouite_Item(int id, String create_date, String name, int loan_type, int apply_status, String loan_num,
                         double loan_amount, int loan_trems, double loan_rate,
                         String first_duedate, String final_duedate, int alert_date_type, String alert_time,
                         String address, String phone,  String remarks ) {
        
		this.id = id;
		this.create_date = create_date;
        this.name = name;
        this.loan_type = loan_type;
        this.apply_status = apply_status;
        this.loan_num = loan_num;

        this.loan_amount = loan_amount;
        this.loan_trems = loan_trems;
        this.loan_rate = loan_rate;

        this.first_duedate = first_duedate;
        this.final_duedate = final_duedate;
        this.alert_date_type = alert_date_type;
        this.alert_time = alert_time;

		this.address = address;
        this.phone = phone;
        this.remarks = remarks;
    }

    //=============================================================================================
    // [1] item id
    public int getid(){
        return this.id;
    }

    public void setid(int id){
        this.id = id;
    }
    //=============================================================================================
    // [2] Create date
    public String getCreate_date() {
        return this.create_date;
    }

    public void setCreate_date(String create_date){
        this.create_date = create_date;
    }
    //=============================================================================================
    // [3] Name
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    //=============================================================================================
    // [4] Loan Type
    public int getLoan_Type(){
        return this.loan_type;
    }

    public void setLoan_Type(int loan_type){
        this.loan_type = loan_type;
    }

    //=============================================================================================
    // [5] Apply Status
    public int getApply_status(){
        return this.apply_status;
    }

    public void setApply_status(int apply_status){
        this.apply_status = apply_status;
    }

    //=============================================================================================
    // [6] Loan Number
    public String getLoanNum(){
        return this.loan_num;
    }

    public void setLoanNum(String loan_num) {
        this.loan_num = loan_num;
    }

    //=============================================================================================
    // [7] Loan Amount
    public double getLoan_Amount(){
        return this.loan_amount;
    }

    public void setLaon_Amount(double loan_amount){
        this.loan_amount = loan_amount;
    }

    //=============================================================================================
    // [8] Loan Trems
    public int getTrems(){
        return this.loan_trems;
    }

    public void setTrems(int loan_trems){
        this.loan_trems = loan_trems;
    }

    //=============================================================================================
    // [9] Loan Rate
    public double getLoan_Rate(){
        return this.loan_rate;
    }

    public void setLoan_Rate(double loan_rate){
        this.loan_rate = loan_rate;
    }

    //=============================================================================================
    // [10] First Due Date
    public String getFirst_dueddate(){
        return this.first_duedate;
    }

    public void setFirst_duedate(String first_duedate){
        this.first_duedate = first_duedate;
    }

    //=============================================================================================
    // [11] Final Due Date
    public String getFinal_dueddate(){
        return this.final_duedate;
    }

    public void setFinal_duedate(String final_duedate){
        this.final_duedate = final_duedate;
    }

    //=============================================================================================
    // [12] Alert date
    public int getAlert_date(){
        return  this.alert_date_type;
    }

    public void setAlert_date(int alert_date){
        this.alert_date_type = alert_date;
    }

    //=============================================================================================
    // [13] Alert time
    public String getAlert_time(){
        return this.alert_time;
    }

    public void setAlert_time(String duedate_type){
        this.alert_time = duedate_type;
    }

    //=============================================================================================
    // [15] Adress
    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //=============================================================================================
    // [14] Phone
    public String getPhone(){
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //=============================================================================================
    // [15] Remarks
    public String getRemarks(){
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


}
