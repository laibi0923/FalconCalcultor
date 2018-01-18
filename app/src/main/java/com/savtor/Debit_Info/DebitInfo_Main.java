package com.savtor.Debit_Info;
import android.support.v4.app.*;
import android.view.*;
import android.os.*;
import android.widget.TextView;

import com.savtor.falconcalcultor.*;
import com.savtor.Credit_Database.*;

import java.util.List;


public class DebitInfo_Main extends Fragment
{

	private TextView T01;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		View v = inflater.inflate(R.layout.tesing_fragment_1, container, false);

		T01 = (TextView) v.findViewById(R.id.T01);

		Favourite_DataBasic favourite_dataBasic = new Favourite_DataBasic(getContext(), "DebitInfo_Main");

		T01.setText("");

		List<Favouite_Item> items = favourite_dataBasic.query_all();



		for(Favouite_Item i : items){

			T01.append("ID : " + i.getID() + "\n");
			T01.append("Create Date : " + i.getCreate_Date() + "\n");
			T01.append("Name : " + i.getProduct_Name() + "\n");
			T01.append("LoanNum : " + i.getLoan_No() + "\n");
			T01.append("Product Type : " + i.getProduct_Type() + "\n");
			T01.append("Product Status :" + i.getProduct_Status() + "\n");
			T01.append("Loan Amount : " + i.getLoan_Amount() + "\n");
			T01.append("Loan Trems : " + i.getLoan_Trems() +"\n");
			T01.append("Loan Rate : " + i.getLoan_Rate() + "\n");
			T01.append("Loan Installment : " + i.getLoan_Installment() + "\n");
			T01.append("First due date : " + i.getFirst_Due() + "\n");
			T01.append("Final due date : " + i.getFinal_Due() + "\n");
			T01.append("EOM : " + i.getEOM_DueDate() + "\n");
			T01.append("Setup Alarm : " + i.getSetup_Alarm() + "\n");
			T01.append("Alert Time : " + i.getAlarm_Time() + "\n");
			T01.append("Address :" + i.getAddress() + "\n");
			T01.append("Phone :" + i.getPhone_No() + "\n");
			T01.append("Request Code :" + i.getRequestCode() + "\n");
			T01.append("Remarks : \n" + i.getRemarks() + "\n \n");

		}

		favourite_dataBasic.close();

		return v;
	}

}

