package com.savtor.falconcalcultorDebitInfo;
import android.support.v4.app.*;
import android.view.*;
import android.os.*;
import android.widget.TextView;

import com.savtor.falconcalcultor.*;
import com.savtor.falconcalaultorDatabase.*;

import java.util.List;


public class Fragment_DebitInfo extends Fragment
{

	private TextView T01;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		View v = inflater.inflate(R.layout.tesing_fragment_1, container, false);

		T01 = (TextView) v.findViewById(R.id.T01);

		Favourite_DataBasic favourite_dataBasic = new Favourite_DataBasic(getContext(), "Fragment_DebitInfo");

		T01.setText("");

		List<Favouite_Item> items = favourite_dataBasic.query_orderby_date();



		for(Favouite_Item i : items){

			T01.append("ID : " + i.getid() + "\n");
			T01.append("Create Date : " + i.getCreate_date() + "\n");
			T01.append("Name : " + i.getName() + "\n");
			T01.append("Loan Amount : " + i.getLoan_Amount() + "\n");
			T01.append("Loan Trems : " + i.getTrems() +"\n");
			T01.append("Loan Rate : " + i.getLoan_Rate() + "\n");
			T01.append("First due date : " + i.getFirst_dueddate() + "\n");
			T01.append("Final due date : " + i.getFinal_dueddate() + "\n");
			T01.append("Due date type : " + i.getDuedate_type() + "\n");
			T01.append("Apply Status : " + i.getApply_status() + "\n");
			T01.append("Loan Type :" + i.getLoan_Type() + "\n");
			T01.append("Alert Date : " + i.getAlert_date() + "\n");
			T01.append("LoanNum : " + i.getLoanNum() + "\n");
			T01.append("Address :" + i.getAddress() + "\n");
			T01.append("Phone :" + i.getPhone() + "\n");
			T01.append("Remarks : \n" + i.getRemarks() + "\n \n");

		}

		favourite_dataBasic.close();

		return v;
	}

}

