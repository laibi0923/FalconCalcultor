package com.savtor.AlarmNotification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.savtor.falconcalcultor.*;

/**
 * Created by GhostLeo_DT on 21/12/2017.
 */
public class tseting_Notification extends Fragment{

    private Button show_notification_btn, show_custom_notification_btn;
    private EditText notification_title, notification_content;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.testing_notification_fragment, container, false);

        notification_title = (EditText) v.findViewById(R.id.notification_title);

        notification_content = (EditText) v.findViewById(R.id.notification_content);

        show_notification_btn = (Button) v.findViewById(R.id.show_notification_btn);
        show_notification_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myNotification m1 = new myNotification();
                m1.myNotification(getActivity(), notification_title.getText().toString(), notification_content.getText().toString());
            }
        });


        show_custom_notification_btn = (Button) v.findViewById(R.id.show_custom_notification_btn);
        show_custom_notification_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Custom_Notification mN = new Custom_Notification();
                mN.Custom_Notification(getActivity(), notification_title.getText().toString(), notification_content.getText().toString());

            }
        });

        return v;
    }


}
