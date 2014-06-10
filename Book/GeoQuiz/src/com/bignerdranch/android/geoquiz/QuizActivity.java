package com.bignerdranch.android.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends ActionBarActivity {

	private static final String KEY_INDEX = "index";
	private static final String IS_CHEATER = "cheater";
	private boolean mIsCheater;
	
	private Button mTrueButton;
	private Button mFalseButton;
	
	private Button mNextButton;
	private Button mPrevButton;
	private Button mCheatButton;
	
	private TextView mQuestionTextView;
	
	private TrueFalse[] mQuestionBank = new TrueFalse[]{
			new TrueFalse(R.string.question_oceans, true),
			new TrueFalse(R.string.question_mideast, false),
			new TrueFalse(R.string.question_africa, false),
			new TrueFalse(R.string.question_americas, true),
			new TrueFalse(R.string.question_asia, true),
	};
	
	private int mCurrentIndex = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getQuestion());
        
        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton = (Button)findViewById(R.id.false_button);
        mCheatButton = (Button)findViewById(R.id.cheat_button);
       
        mNextButton = (Button)findViewById(R.id.next_button);
        mPrevButton = (Button)findViewById(R.id.prev_button);
        
        mCheatButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
				intent.putExtra(CheatActivity.EXTRA_ANSWER, mQuestionBank[mCurrentIndex].isTrueQuestion());
				startActivityForResult(intent, 0);
			}
		});
        
        mTrueButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				if(mIsCheater){
					Toast.makeText(QuizActivity.this, R.string.cheater_toast, Toast.LENGTH_SHORT).show();	
					return;
				}
				mChecker(true);
			}
		});
        
        mFalseButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				if(mIsCheater){
					Toast.makeText(QuizActivity.this, R.string.cheater_toast, Toast.LENGTH_SHORT).show();	
					return;
				}
				mChecker(false);
			}
		});
        
        mNextButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				mNextQuestion();
			}
		});
        
        mPrevButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				mPrevQuestion();
			}
		});

        if (savedInstanceState != null) {
        	mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        	mIsCheater = savedInstanceState.getBoolean(IS_CHEATER, false);
       }

        mUpdateQuestion();
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (data == null) {
    	return;
    	}
    	mIsCheater = 
    			data.getBooleanExtra(CheatActivity.HAS_CHEATED, 
    	false);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
    	super.onSaveInstanceState(savedInstanceState);
    	savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    	savedInstanceState.putBoolean(IS_CHEATER, mIsCheater);
    }

    private void mUpdateQuestion(){    	
    	mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getQuestion());
    }
    
    private void mNextQuestion(){
    	mCurrentIndex = (mCurrentIndex+1)==mQuestionBank.length ? mQuestionBank.length-1:++mCurrentIndex%mQuestionBank.length;
    	
    	if (mQuestionBank.length==mCurrentIndex+1)
    			Toast.makeText(QuizActivity.this, R.string.end_of_questions, Toast.LENGTH_SHORT).show();
    	mUpdateQuestion();
    }
    
    private void mPrevQuestion(){
    	mCurrentIndex = (mCurrentIndex-1)<0 ? 0:--mCurrentIndex%mQuestionBank.length;
    	
     	if (mCurrentIndex==0)
			Toast.makeText(QuizActivity.this, R.string.start_of_questions, Toast.LENGTH_SHORT).show();
    	
    	mUpdateQuestion();
    }
    
    private void mChecker(boolean answer){
    	
    	if(mQuestionBank[mCurrentIndex].isTrueQuestion()==answer){
    		Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
    		mNextQuestion();
    	}
    	else{
    		Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
    	}
    	
    }
    

    
}
