package com.lemuel.exercice.eventlistenerexercise;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

	private Button mCancelButton;
	private Button mOkButton;
	private TextView mTextView;
	
	private View.OnClickListener mClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v.findViewById(R.id.ok_button)==mOkButton)
				mTextView.setText(R.string.ok_text);
			else {
				mTextView.setText(R.string.cancel_text);
			}
		}
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mCancelButton = (Button)findViewById(R.id.cancel_Button);
		mOkButton = (Button)findViewById(R.id.ok_button);
		
		mTextView = (TextView)findViewById(R.id.text_view);
		
		mCancelButton.setOnClickListener(mClickListener);
		mOkButton.setOnClickListener(mClickListener);
		
		
	}

	

}
