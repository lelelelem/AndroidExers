package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
	
	public static final String EXTRA_ANSWER = "anything is fine";
	public static final String HAS_CHEATED = "fine is ok";
	private static boolean has_cheated; 
	
	private boolean mAnswer;
	
	private Button mCheatButton;
	
	private TextView mTextAnswer;
	private Intent data; 
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cheatlayout);
		
		data = new Intent();
		
		
		
		mAnswer = getIntent().getBooleanExtra(EXTRA_ANSWER, false);
		
		mTextAnswer = (TextView)findViewById(R.id.answer_text);

		mCheatButton = (Button)findViewById(R.id.show_answer);
		
		mCheatButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mAnswer)
					mTextAnswer.setText(R.string.true_button);
				else
					mTextAnswer.setText(R.string.false_button);
				data.putExtra(HAS_CHEATED, !has_cheated);
				setResult(RESULT_OK,data);
			}
		});
		
		
	}
}
