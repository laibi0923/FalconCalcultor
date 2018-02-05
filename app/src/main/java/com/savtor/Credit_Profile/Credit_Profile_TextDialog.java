package com.savtor.Credit_Profile;
import com.savtor.falconcalcultor.*;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

    public TextView Source_View;

    public String Text_Dialog_Title;
	
	private String Scoure_Value;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		
		setDialogView = LayoutInflater.from(getContext()).inflate(R.layout.zz_credit_profile_text_dialog, null);
		
		setContentView(setDialogView);
		
		Find_View(setDialogView);
		
		this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		
	}

    protected Credit_Profile_TextDialog(Context context, String Text_Dialog_Title, TextView Source_View, String Scoure_Value) {

        super(context, R.style.FullScreenDialogStyle);

        this.Source_View = Source_View;
		
        this.Text_Dialog_Title = Text_Dialog_Title;
		
		this.Scoure_Value = Scoure_Value;
        
    }

	
/*================================================================================================
    *      Find Dialog View
 ================================================================================================ */
    private void Find_View(View dialog_view)
    {
        Dialog_Title = (TextView) dialog_view.findViewById(R.id.text_dialog_title);
        Dialog_Title.setText(Text_Dialog_Title);

        Input_Editor = (EditText) dialog_view.findViewById(R.id.input_editor);

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
					
					if(Input_Editor.getText().toString().isEmpty() || Input_Editor.getText().toString() == ""){
						dismiss();
					}else{
						String get_value = Input_Editor.getText().toString();
						Source_View.setText(get_value);
						Scoure_Value = Input_Editor.getText().toString();
						dismiss();
					}
					break;
			}
		}
	};

}
