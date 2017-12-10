package com.savtor.falconcalcultorSetting;

import com.savtor.falconcalcultor.*;
import com.savtor.falconcalcultorPasswordgate.Fragment_Passwordgate;
import com.savtor.falconcalcultorPasswordgate.*;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.*;
import android.util.Log;
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

	private SharedPreferences mSharedPreferences;
	private SharedPreferences.Editor mEditor;
	private int get_setting_password, get_setting_language, get_setting_decimal;


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mSharedPreferences = this.getActivity().getSharedPreferences("Setting", Context.MODE_PRIVATE);
		get_sharedpreferences();
		mEditor = mSharedPreferences.edit();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		View v = inflater.inflate(R.layout.setting_main, container, false);

		Find_View(v);

		return v;
	}




	//=============================================================================================
	// []
	public void get_sharedpreferences(){
		get_setting_password = mSharedPreferences.getInt("Setting_password", 1);
		get_setting_language = mSharedPreferences.getInt("Setting_language", 1);
		get_setting_decimal = mSharedPreferences.getInt("Setting_decimal" , 1);
	}

	//=============================================================================================
	// [1] 加入畫面內容
	public void Find_View(View v){

		password_title = (TextView) v.findViewById(R.id.password_title);
		password_title.setText("Password");

		password_lock_subview = v.findViewById(R.id.password_lock);
		password_lock_linear = (LinearLayout) password_lock_subview.findViewById(R.id.sub_linear);
		password_lock_linear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if(get_setting_password == 1){

					Fragment mFragment = new Fragment_Passwordgate();
					Bundle mBundle = new Bundle();
					mBundle.putInt("setup_password_mode", 1);
					mFragment.setArguments(mBundle);

					getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.mFrameLayout, mFragment).commit();

				}else if(get_setting_password == 2){
					Toast.makeText(getContext(), "Yoy have setup password yet", Toast.LENGTH_LONG).show();
				}

			}
		});
		password_lock_text = (TextView) password_lock_subview.findViewById(R.id.sub_text);
		password_lock_text.setText("Setup Password");
		password_lock_icon = (ImageView) password_lock_subview.findViewById(R.id.sub_image);
		password_lock_icon.setImageResource(R.drawable.ic_lock_black_24dp);

		if(get_setting_password == 1){
			password_lock_text.setTextColor(getResources().getColor(R.color.normal_textcolor));
		}else if(get_setting_password == 2){
			password_lock_text.setTextColor(getResources().getColor(R.color.accent));
		}


		password_clear_subview = v.findViewById(R.id.password_clear);
		password_clear_linear = (LinearLayout) password_clear_subview.findViewById(R.id.sub_linear);
		password_clear_linear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (get_setting_password == 1){
					Toast.makeText(getContext(), "Please setup your password first", Toast.LENGTH_LONG).show();
				}else if (get_setting_password == 2){

					Fragment mFragment = new Fragment_Passwordgate();
					Bundle mBundle = new Bundle();
					mBundle.putInt("setup_password_mode", 2);
					mFragment.setArguments(mBundle);

					getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.mFrameLayout, mFragment).commit();

				}

			}
		});
		password_clear_text = (TextView) password_clear_subview.findViewById(R.id.sub_text);
		password_clear_text.setText("Clear Password");
		password_clear_icon = (ImageView) password_clear_subview.findViewById(R.id.sub_image);
		password_clear_icon.setImageResource(R.drawable.ic_lock_open_black_24dp);

		underline = password_clear_subview.findViewById(R.id.sub_underline);
		underline.setVisibility(View.GONE);




		language_title = (TextView) v.findViewById(R.id.language_title);
		language_title.setText("Language");

		language_eng_subview = v.findViewById(R.id.language_eng);
		language_eng_linear = (LinearLayout) language_eng_subview.findViewById(R.id.sub_linear);
		language_eng_linear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				language_cht_text.setTextColor(getResources().getColor(R.color.normal_textcolor));
				language_eng_text.setTextColor(getResources().getColor(R.color.accent));
				mEditor.putInt("Setting_language", 1).commit();
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
				language_cht_text.setTextColor(getResources().getColor(R.color.accent));
				language_eng_text.setTextColor(getResources().getColor(R.color.normal_textcolor));
				mEditor.putInt("Setting_language", 2).commit();
			}
		});
		language_cht_text = (TextView) language_cht_subview.findViewById(R.id.sub_text);
		language_cht_text.setText("繁體");
		language_cht_icon = (ImageView) language_cht_subview.findViewById(R.id.sub_image);
		language_cht_icon.setImageResource(R.drawable.ic_translate_black_24dp);

		underline = language_cht_subview.findViewById(R.id.sub_underline);
		underline.setVisibility(View.GONE);

		if (get_setting_language == 1){
			language_eng_text.setTextColor(getResources().getColor(R.color.accent));
		}else if (get_setting_language == 2){
			language_cht_text.setTextColor(getResources().getColor(R.color.accent));
		}




		decimal_point_title = (TextView) v.findViewById(R.id.decimal_point_title);
		decimal_point_title.setText("小數點");

		decimal_point_0_subview = v.findViewById(R.id.decimal_point_0);
		decimal_point_0_linear = (LinearLayout) decimal_point_0_subview.findViewById(R.id.sub_linear);
		decimal_point_0_linear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				decimal_point_0_text.setTextColor(getResources().getColor(R.color.accent));
				decimal_point_2_text.setTextColor(getResources().getColor(R.color.normal_textcolor));
				mEditor.putInt("Setting_decimal", 1).commit();
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
				decimal_point_0_text.setTextColor(getResources().getColor(R.color.normal_textcolor));
				decimal_point_2_text.setTextColor(getResources().getColor(R.color.accent));
				mEditor.putInt("Setting_decimal", 2).commit();
			}
		});
		decimal_point_2_text = (TextView) decimal_point_2_subview.findViewById(R.id.sub_text);
		decimal_point_2_text.setText("顯示小數點後兩位");
		decimal_point_2_icon = (ImageView) decimal_point_2_subview.findViewById(R.id.sub_image);
		decimal_point_2_icon.setImageResource(R.drawable.ic_decrease_decimal);

		underline = decimal_point_2_subview.findViewById(R.id.sub_underline);
		underline.setVisibility(View.GONE);


		if (get_setting_decimal == 1){
			decimal_point_0_text.setTextColor(getResources().getColor(R.color.accent));
		}else if (get_setting_decimal == 2){
			decimal_point_2_text.setTextColor(getResources().getColor(R.color.accent));
		}

	}


}

