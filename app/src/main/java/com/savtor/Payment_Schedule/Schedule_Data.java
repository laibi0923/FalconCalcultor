package com.savtor.Payment_Schedule;

/**
 * Created by GhostLeo_DT on 26/11/2017.
 */
public class Schedule_Data {

    public String sc_data_trems, sc_data_insterest, sc_data_principle, sc_data_balance, cumulative_Interest;

    public Schedule_Data(String scv_data_trems, String scv_data_insterest, String scv_data_principle, String scv_data_balance, String cumulative_Interest){

        this.sc_data_trems = scv_data_trems;
        this.sc_data_insterest = scv_data_insterest;
        this.sc_data_principle = scv_data_principle;
        this.sc_data_balance = scv_data_balance;
        this.cumulative_Interest = cumulative_Interest;
    }

}
