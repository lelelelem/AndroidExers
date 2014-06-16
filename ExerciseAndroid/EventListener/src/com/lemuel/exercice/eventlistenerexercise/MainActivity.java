package com.lemuel.exercice.eventlistenerexercise;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	private Button mCancelButton;
	private Button mOkButton;
	private TextView mTextView;
	
	private final String TAG = "logs";
	
	private View.OnClickListener mClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Log.d(TAG, "Something was Clicked");
			//used to reuse clickListner, switch can be used but
			//it will be an overkill but it will be more flexible
			if (v.findViewById(R.id.ok_button)==mOkButton){
				//tempted to use a local variable to use for storage of R.string
				//not sure if its a better approach?
				Toast.makeText(getApplication(), R.string.ok_text, Toast.LENGTH_SHORT).show();
				mTextView.setText(R.string.ok_text);
			}
			else {
				Toast.makeText(getApplication(), R.string.cancel_text, Toast.LENGTH_SHORT).show();
				mTextView.setText(R.string.cancel_text);
			}
		}
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.d(TAG, "wiring up buttons");
		
		mCancelButton = (Button)findViewById(R.id.cancel_Button);
		mOkButton = (Button)findViewById(R.id.ok_button);
		
		Log.d(TAG, "wiring up TextField");
		mTextView = (TextView)findViewById(R.id.text_view);
		
		Log.d(TAG, "wiring up button listeners");
		mCancelButton.setOnClickListener(mClickListener);
		mOkButton.setOnClickListener(mClickListener);
		
		
	}

	

}
