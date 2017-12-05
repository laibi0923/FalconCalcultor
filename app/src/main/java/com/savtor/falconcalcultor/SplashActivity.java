package com.savtor.falconcalcultor;
import android.support.v7.app.*;
import android.os.*;
import android.content.*;


public class SplashActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_activity);

		new Handler().postDelayed(new Runnable(){

				@Override
				public void run()
				{
					// TODO: Implement this method
					startActivity(new Intent(SplashActivity.this, MainActivity.class)); 
					finish();
				}
			},1500);
	}

}

