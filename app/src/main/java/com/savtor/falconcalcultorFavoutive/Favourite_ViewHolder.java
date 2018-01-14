package com.savtor.falconcalcultorFavoutive;

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
public class Favourite_ViewHolder extends RecyclerView.ViewHolder {

    TextView  create_date_tv, name_tv, loan_amount_tv, loan_trems_tv, loan_rate_tv, installment_tv, apply_status_tv, remarks_tv, loan_number_tv, last_modify;

    ImageView alert_icon_im, loan_type_icon;

    public SwipeLayout swipeLayout;

    RelativeLayout Del_btn, Edit_btn;
    LinearLayout top_warp;


    public Favourite_ViewHolder(View itemView) {
        super(itemView);

        top_warp = (LinearLayout) itemView.findViewById(R.id.top_warp);
        create_date_tv = (TextView) itemView.findViewById(R.id.create_date_tv);
        name_tv = (TextView) itemView.findViewById(R.id.name_tv);
        loan_amount_tv = (TextView) itemView.findViewById(R.id.loan_amount_tv);
        loan_trems_tv = (TextView) itemView.findViewById(R.id.loan_trems_tv);
        loan_rate_tv = (TextView) itemView.findViewById(R.id.loan_rate_tv);
        installment_tv = (TextView) itemView.findViewById(R.id.installment_tv);
        apply_status_tv = (TextView) itemView.findViewById(R.id.apply_status_tv);
        loan_type_icon = (ImageView) itemView.findViewById(R.id.loan_type_icon);
        alert_icon_im = (ImageView) itemView.findViewById(R.id.alert_icon_im);
        swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe_layout);
        Edit_btn = (RelativeLayout) itemView.findViewById(R.id.edit_btn);
        Del_btn = (RelativeLayout) itemView.findViewById(R.id.del_btn);
        remarks_tv = (TextView) itemView.findViewById(R.id.remarks_tv);
        loan_number_tv = (TextView) itemView.findViewById(R.id.loan_number_tv);
		last_modify = (TextView) itemView.findViewById(R.id.last_modify);
    }
}
