package com.savtor.falconcalcultorFavoutive;

/**1
 * Created by GhostLeo_DT on 2/12/2017.
 */
public class Favourite_Data  {

    String id, create_date, name, loan_amount, loan_trems, loan_rate, apply_status, loan_type, installment, alert_date;

    public  Favourite_Data (String id, String create_date, String name, String loan_amount, String loan_trems, String loan_rate, String loan_type, String apply_status, String installment, String alert_date){

        this.id = id;
        this.create_date = create_date;
        this.name = name;
        this.loan_amount = loan_amount;
        this.loan_trems = loan_trems;
        this.loan_rate = loan_rate;
        this.apply_status = apply_status;
        this.loan_type = loan_type;
        this.installment = installment;
        this.alert_date = alert_date;

    }

    //=============================================================================================
    //
    public String getid(){
        return this.id;
    }

    public void setid(){
        this.id = id;
    }

    //=============================================================================================
    //
    public String getCreate_date(){
        return this.create_date;
    }

    public void setCreate_date(){
        this.create_date = create_date;
    }

    //=============================================================================================
    //
    public String getName(){
        return this.name;
    }

    public void setName(){
        this.name = name;
    }

    //=============================================================================================
    //
    public String getLoan_amount(){
        return this.loan_amount;
    }

    public void setLoan_amount(){
        this.loan_amount = loan_amount;
    }

    //=============================================================================================
    //
    public String getLoan_trems(){
        return this.loan_trems;
    }

    public void setLoan_trems(){
        this.loan_trems = loan_trems;
    }

    //=============================================================================================
    //
    public String getLoan_rate(){
        return this.loan_rate;
    }

    public void setLoan_rate(){
        this.loan_rate = loan_rate;
    }

}
