package com.savtor.falconcalcultorCalcultor;

import java.util.ArrayList;
import java.util.List;
import com.savtor.falconcalcultor.*;
/**
 * Created by GhostLeo_DT on 8/12/2017.
 */
public class Calcultor {

    //=============================================================================================
    // [12] 計算月平息
    public double getMonthlyRate(double loanRate){
        return loanRate / 12 / 100;
    }

    //=============================================================================================
    // [8] 計算每月還款額
    public double getMonthlyInstallment(double amount, int trems, double rate){
       double monthlyRate = getMonthlyRate(rate);
       return (amount * monthlyRate * Math.pow(1 + monthlyRate, trems)) / (Math.pow(1 + monthlyRate, trems) - 1);
    }

    //=============================================================================================
    // [10] 計算全期利
    public double getTotalInsterest(double amount, int trems, double installment){
        return  (installment * trems) - amount;
    }

    //=============================================================================================
    // [11] 計算本利和
    public double getTotalPayment(double installment, double trems){
        return installment * trems;
    }

    //=============================================================================================
    // [14] 每月利息
    public double cal_even_interest(double rate, double even_balance){
        double monthlyRate = getMonthlyRate(rate);
        return even_balance * monthlyRate;
    }

    //=============================================================================================
    // [15] 每期本金
    public double cal_even_principle(double installment, double even_interest){
        return installment - even_interest;
    }

    //=============================================================================================
    // [16] 每期結餘
    public double cal_even_balance(double balance, double installment, double interest) {

        double balance_result;
        double x = 0;
        x =  balance - (installment - interest);

        if (x < 0){
            balance_result = 0;
        }else {
            balance_result = x;
        }


        return balance_result;
    }

}
