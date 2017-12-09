package com.savtor.falconcalcultorSetting;

import com.savtor.falconcalcultor.*;
import android.support.v4.app.*;
import android.view.*;
import android.os.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Fragment_Setting extends Fragment
{
	private View password_lock_subview, password_clear_subview, language_eng_subview, language_cht_subview, decimal_point_0_subview, decimal_point_2_subview;
	private View underline;
	private TextView password_title, password_lock_text, password_clear_text, language_title, language_eng_text, language_cht_text, decimal_point_title, decimal_point_0_text, decimal_point_2_text;
	private ImageView password_lock_icon, password_clear_icon, language_eng_icon, language_cht_icon, decimal_point_0_icon, decimal_point_2_icon;
	private LinearLayout password_lock_linear, password_clear_linear, language_eng_linear, language_cht_linear, decimal_point_0_linear, decimal_point_2_linear;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		View v = inflater.inflate(R.layout.setting_main, container, false);
		Find_View(v);

		return v;
	}



	public void Find_View(View v){

		password_title = (TextView) v.findViewById(R.id.password_title);
		password_title.setText("Password");

		password_lock_subview = v.findViewById(R.id.password_lock);
		password_lock_linear = (LinearLayout) password_lock_subview.findViewById(R.id.sub_linear);
		password_lock_linear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getContext(), "testing click", Toast.LENGTH_LONG).show();
			}
		});
		password_lock_text = (TextView) password_lock_subview.findViewById(R.id.sub_text);
		password_lock_text.setText("Setup Password");
		password_lock_icon = (ImageView) password_lock_subview.findViewById(R.id.sub_image);
		password_lock_icon.setImageResource(R.drawable.ic_lock_black_24dp);

		password_clear_subview = v.findViewById(R.id.password_clear);
		password_clear_linear = (LinearLayout) password_clear_subview.findViewById(R.id.sub_linear);
		password_clear_linear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getContext(), "testing click", Toast.LENGTH_LONG).show();
			}
		});
		password_clear_text = (TextView) password_clear_subview.findViewById(R.id.sub_text);
		password_clear_text.setText("Clear Password");
		password_clear_icon = (ImageView) password_clear_subview.findViewById(R.id.sub_image);
		password_clear_icon.setImageResource(R.drawable.ic_lock_open_black_24dp);

		underline = password_clear_subview.findViewById(R.id.sub_underline);
		underline.setVisibility(View.GONE);

//=====
		language_title = (TextView) v.findViewById(R.id.language_title);
		language_title.setText("Language");

		language_eng_subview = v.findViewById(R.id.language_eng);
		language_eng_linear = (LinearLayout) language_eng_subview.findViewById(R.id.sub_linear);
		language_eng_linear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getContext(), "testing click", Toast.LENGTH_LONG).show();
			}
		});
		language_eng_text = (TextView) language_eng_subview.findViewById(R.id.sub_text);
		language_eng_text.setText("ENG");
		language_eng_icon = (ImageView) language_eng_subview.findViewById(R.id.sub_image);
		language_eng_icon.setImageResource(R.drawable.ic_translate_black_24dp);

		language_cht_subview = v.findViewById(R.id.language_cht);
		language_cht_linear = (LinearLayout) language_cht_subview.findViewById(R.id.sub_linear);
		language_cht_linear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getContext(), "testing click", Toast.LENGTH_LONG).show();
			}
		});
		language_cht_text = (TextView) language_cht_subview.findViewById(R.id.sub_text);
		language_cht_text.setText("繁體");
		language_cht_icon = (ImageView) language_cht_subview.findViewById(R.id.sub_image);
		language_cht_icon.setImageResource(R.drawable.ic_translate_black_24dp);

		underline = language_cht_subview.findViewById(R.id.sub_underline);
		underline.setVisibility(View.GONE);


		decimal_point_title = (TextView) v.findViewById(R.id.decimal_point_title);
		decimal_point_title.setText("小數點");

		decimal_point_0_subview = v.findViewById(R.id.decimal_point_0);
		decimal_point_0_linear = (LinearLayout) decimal_point_0_subview.findViewById(R.id.sub_linear);
		decimal_point_0_linear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getContext(), "testing click", Toast.LENGTH_LONG).show();
			}
		});
		decimal_point_0_text = (TextView) decimal_point_0_subview.findViewById(R.id.sub_text);
		decimal_point_0_text.setText("不顯示小數點");
		decimal_point_0_icon = (ImageView) decimal_point_0_subview.findViewById(R.id.sub_image);
		decimal_point_0_icon.setImageResource(R.drawable.ic_increase_decimal);

		decimal_point_2_subview = v.findViewById(R.id.decimal_point_2);
		decimal_point_2_linear = (LinearLayout) decimal_point_2_subview.findViewById(R.id.sub_linear);
		decimal_point_2_linear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getContext(), "testing click", Toast.LENGTH_LONG).show();
			}
		});
		decimal_point_2_text = (TextView) decimal_point_2_subview.findViewById(R.id.sub_text);
		decimal_point_2_text.setText("顯示小數點後兩位");
		decimal_point_2_icon = (ImageView) decimal_point_2_subview.findViewById(R.id.sub_image);
		decimal_point_2_icon.setImageResource(R.drawable.ic_decrease_decimal);

		underline = decimal_point_2_subview.findViewById(R.id.sub_underline);
		underline.setVisibility(View.GONE);
	}


}

