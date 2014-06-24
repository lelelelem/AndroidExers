package com.bignerdranch.android.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class QuizActivity extends ActionBarActivity{

	private static final String KEY_INDEX = "index";
	private static final String IS_CHEATER = "cheater";
	private boolean mIsCheater;
	
	private Button mTrueButton;
	private Button mFalseButton;
	
	private Button mNextButton;
	private Button mPrevButton;
	
	private Button mCheatButton;
	
	private TextView mStatusTextView;
	private TextView mQuestionTextView;
	
	private TrueFalse[] mQuestionBank = new TrueFalse[]{
			new TrueFalse(R.string.question_oceans, true),
			new TrueFalse(R.string.question_mideast, false),
			new TrueFalse(R.string.question_africa, false),
			new TrueFalse(R.string.question_americas, true),
			new TrueFalse(R.string.question_asia, true),
	};
	
	private View.OnClickListener mCheatListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
			intent.putExtra(CheatActivity.EXTRA_ANSWER, mQuestionBank[mCurrentIndex].isTrueQuestion());
			startActivityForResult(intent, 0);
		}
	};
	private View.OnClickListener mTrueButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(mIsCheater){
				mStatusTextView = (TextView)findViewById(R.id.status_text);
				mStatusTextView.setText(R.string.cheater_toast);					
				return;
			}
			mChecker(true);
		}
	};
	private View.OnClickListener mFalseButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(mIsCheater){
				mStatusTextView = (TextView)findViewById(R.id.status_text);
				mStatusTextView.setText(R.string.cheater_toast);					
				return;
			}
			mChecker(false);
			
		}
	};
	private View.OnClickListener mNextButtonListener = new OnClickListener() {		
		@Override
		public void onClick(View v) {
			mNextQuestion();
		}
	};
	
	private View.OnClickListener mPrevButtonListener = new OnClickListener() {		
		@Override
		public void onClick(View v) {
					mPrevQuestion();
		}
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
        
        mCheatButton.setOnClickListener(mCheatListener);
        
        mTrueButton.setOnClickListener(mTrueButtonListener);
        
        mFalseButton.setOnClickListener(mFalseButtonListener);
        
        mNextButton.setOnClickListener(mNextButtonListener);
        
        mPrevButton.setOnClickListener(mPrevButtonListener);

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
    	mIsCheater = data.getBooleanExtra(CheatActivity.HAS_CHEATED, false);
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
    	if (mQuestionBank.length==mCurrentIndex+1){
    		mStatusTextView = (TextView)findViewById(R.id.status_text);
			mStatusTextView.setText(R.string.end_of_questions);
    	}
    	mUpdateQuestion();
    }
    
    private void mPrevQuestion(){
    	mCurrentIndex = (mCurrentIndex-1)<0 ? 0:--mCurrentIndex%mQuestionBank.length;
    	
     	if (mCurrentIndex==0){
    		mStatusTextView = (TextView)findViewById(R.id.status_text);
			mStatusTextView.setText(R.string.start_of_questions);
    	}
    	
    	mUpdateQuestion();
    }
    
    private void mChecker(boolean answer){
    	
    	if(mQuestionBank[mCurrentIndex].isTrueQuestion()==answer){    	
        		mStatusTextView = (TextView)findViewById(R.id.status_text);
    			mStatusTextView.setText(R.string.correct_toast);       	
    		    mNextQuestion();
    	}
    	else{
    		mStatusTextView = (TextView)findViewById(R.id.status_text);
			mStatusTextView.setText(R.string.incorrect_toast);  
    	}
    	
    }
    

    
}
