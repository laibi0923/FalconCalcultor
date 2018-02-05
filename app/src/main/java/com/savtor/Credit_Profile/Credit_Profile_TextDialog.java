package com.savtor.Credit_Profile;
import com.savtor.falconcalcultor.*;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.*;
import android.os.*;

/**
 * Created by GhostLeo_DT on 5/2/2018.
 */
public class Credit_Profile_TextDialog extends Dialog{

    private View setDialogView;

    private TextView Dialog_Title;

    private EditText Input_Editor;

    private RecyclerView mRecyclerView;
	
	private ImageView done_linear;

	private String Text_Dialog_Title;

	private String TAG;

    protected Credit_Profile_TextDialog(Context context, String Text_Dialog_Title, String TAG) {

		super(context, R.style.FullScreenDialogStyle);

		setDialogView = LayoutInflater.from(getContext()).inflate(R.layout.zz_credit_profile_text_dialog, null);

		this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

		setContentView(setDialogView);

        this.Text_Dialog_Title = Text_Dialog_Title;

		this.TAG = TAG;

		Find_View(setDialogView);
    }

/*================================================================================================
    *      Find Dialog View
 ================================================================================================ */
    private void Find_View(View dialog_view)
    {
        Dialog_Title = (TextView) dialog_view.findViewById(R.id.text_dialog_title);
        Dialog_Title.setText(Text_Dialog_Title);

        Input_Editor = (EditText) dialog_view.findViewById(R.id.input_editor);

		if (TAG == "Trems"){
			Input_Editor.setInputType(InputType.TYPE_CLASS_NUMBER);
		}

        mRecyclerView = (RecyclerView) dialog_view.findViewById(R.id.text_dialog_recycleview);
		
		done_linear = (ImageView) dialog_view.findViewById(R.id.done_linear);
		done_linear.setOnClickListener(View_OnclickListener);

    }
	
	private View.OnClickListener View_OnclickListener = new View.OnClickListener(){

		@Override
		public void onClick(View v)
		{
			// TODO: Implement this method
			switch(v.getId()){
				
				case R.id.done_linear:
					dismiss();
					break;
			}
		}
	};


/*================================================================================================
    *      Return Result to source
 ================================================================================================ */
	public String get_Result(){
		return Input_Editor.getText().toString();
	}

}
