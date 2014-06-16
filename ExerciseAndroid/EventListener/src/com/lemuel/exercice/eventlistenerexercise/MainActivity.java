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
	private Button mHelloButton;
	
	private TextView mTextView;
	
	
	private final String TAG = "logs";
	
	private View.OnClickListener mClickListener = new OnClickListener() {
		//local variable to be used for storage of string
		//not sure if correct or if i should have just called
		//toast and maketext multiple times instead
		int text_to_be_seen;
		@Override
		public void onClick(View v) {
			Log.d(TAG, "Something was Clicked");
			//used switch since its already three
			switch(v.getId()){
				case R.id.ok_button:
					text_to_be_seen=R.string.ok_text;
					break;
				case R.id.cancel_Button:
					text_to_be_seen=R.string.cancel_text;
					break;
				case R.id.hello_world_Button:
					text_to_be_seen=R.string.hello_text;
					break;
			}
				Toast.makeText(getApplication(), text_to_be_seen, Toast.LENGTH_SHORT).show();
				mTextView.setText(text_to_be_seen);
		}
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.d(TAG, "wiring up buttons");
		mCancelButton = (Button)findViewById(R.id.cancel_Button);
		mOkButton = (Button)findViewById(R.id.ok_button);
		mHelloButton = (Button)findViewById(R.id.hello_world_Button);
		
		Log.d(TAG, "wiring up TextField");
		mTextView = (TextView)findViewById(R.id.text_view);
		
		Log.d(TAG, "wiring up button listeners");
		mCancelButton.setOnClickListener(mClickListener);
		mOkButton.setOnClickListener(mClickListener);
		mHelloButton.setOnClickListener(mClickListener);
		
		
	}

	

}
