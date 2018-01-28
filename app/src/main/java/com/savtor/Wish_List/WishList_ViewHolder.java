package com.savtor.Wish_List;

import android.opengl.EGLDisplay;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.daimajia.swipe.SwipeLayout;
import com.savtor.falconcalcultor.*;

/**
 * Created by GhostLeo_DT on 2/12/2017.
 */
public class WishList_ViewHolder extends RecyclerView.ViewHolder {

    TextView Last_Modify, Product_Name, Approval_Status, Loan_Number, Loan_Amount, Loan_Trems, Loan_Rate, Loan_installment, Edit_Text, Delete_Text, Remark;

    ImageView Alarm_Icon, Product_Icon;

    LinearLayout Edit_Btn, Share_Btn, Delete_Btn;

    public WishList_ViewHolder(View itemView) {

        super(itemView);
		
		Remark = (TextView) itemView.findViewById(R.id.wishlist_remark);

        Last_Modify = (TextView) itemView.findViewById(R.id.last_modfiy);

        Alarm_Icon = (ImageView) itemView.findViewById(R.id.alarm_icon);

        Product_Icon = (ImageView) itemView.findViewById(R.id.product_type_icon);

        Product_Name = (TextView) itemView.findViewById(R.id.product_name);

        Approval_Status = (TextView) itemView.findViewById(R.id.approval_status);

        Loan_Number = (TextView) itemView.findViewById(R.id.loan_number);

        Loan_Amount = (TextView) itemView.findViewById(R.id.loan_amount);

        Loan_Rate = (TextView) itemView.findViewById(R.id.loan_rate);

        Loan_Trems = (TextView) itemView.findViewById(R.id.loan_trems);

        Loan_installment = (TextView) itemView.findViewById(R.id.loan_installment);

        Edit_Btn = (LinearLayout) itemView.findViewById(R.id.edit_btn);

        Share_Btn = (LinearLayout) itemView.findViewById(R.id.share_btn);

        Delete_Btn = (LinearLayout) itemView.findViewById(R.id.del_btnn);

    }
}
