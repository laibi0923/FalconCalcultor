package com.savtor.Credit_Profile;
import com.savtor.falconcalcultor.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *	 Created by GhostLeo_DT on 5/2/2018.
 *	 Fragment Dialog 向 Fragment 傳值 : http://blog.csdn.net/a361200614/article/details/71107120
 */
public class Credit_Profile_TextDialog extends DialogFragment {

	private TextView Dialog_Title;
	private EditText Text_Editor;
	private Button Post_Btn;
	private RecyclerView mRecyclerView;
	public static final String RESPONSE = "response";

	private String Dialog_Title_Text, Tag, Content;

	public Credit_Profile_TextDialog(String Dialog_Title_Text, String Tag, String Content){
		this.Dialog_Title_Text = Dialog_Title_Text;
		this.Tag = Tag;
		this.Content = Content;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//	設定 Dialog Style
		setStyle(DialogFragment.STYLE_NO_TITLE, R.style.FullScreenDialogStyle);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.zz_credit_profile_text_dialog, container, false);

		Find_Dialog_View(view);

		return view;
	}

/*================================================================================================
 *
================================================================================================ */
	private void Find_Dialog_View(View v){
		Text_Editor = (EditText) v.findViewById(R.id.input_editor);
		Text_Editor.setHint(Dialog_Title_Text);
		if(Content != null && !Content.trim().isEmpty()){
			Text_Editor.setText(Content);
			Text_Editor.setSelection(Content.length());
		}
        if (Tag == "PRODUCT_REMARK"){
            Text_Editor.setSingleLine(false);
        }
		Post_Btn = (Button) v.findViewById(R.id.output_value_btn);
		Post_Btn.setOnClickListener(Item_OnclickListener);
		mRecyclerView = (RecyclerView) v.findViewById(R.id.text_dialog_recycleview);
	}

/*================================================================================================
 *
================================================================================================ */
	private Button.OnClickListener Item_OnclickListener = new Button.OnClickListener(){
		@Override
		public void onClick(View v) {
			// do something when click done btn
			if (getTargetFragment() == null){
				return;
			}else{
				Intent mIntent = new Intent();
				mIntent.putExtra(RESPONSE, Text_Editor.getText().toString().trim());
				getTargetFragment().onActivityResult(Credit_Profile_Main.DIALOG_REQUEST_CODE, Activity.RESULT_OK, mIntent);
				dismiss();
			}
		}
	};


}
