package com.savtor.Credit_Profile;
import com.savtor.falconcalcultor.*;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by GhostLeo_DT on 5/2/2018.
 */
public class Credit_Profile_TextDialog extends Dialog{

    private View setDialogView;

    private TextView Dialog_Title;

    private EditText Input_Editor;

    private RecyclerView mRecyclerView;

    public TextView Source_View;

    public String Text_Dialog_Title;


    protected Credit_Profile_TextDialog(Context context, String Text_Dialog_Title, TextView Source_View) {

        super(context, R.style.FullScreenDialogStyle);

        setDialogView = LayoutInflater.from(context).inflate(R.layout.zz_credit_profile_text_dialog, null);

        this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        setContentView(setDialogView);

        this.Source_View = Source_View;
        this.Text_Dialog_Title = Text_Dialog_Title;

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

        mRecyclerView = (RecyclerView) dialog_view.findViewById(R.id.text_dialog_recycleview);

    }

}
