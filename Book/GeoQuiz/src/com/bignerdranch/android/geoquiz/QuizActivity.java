package com.bignerdranch.android.geoquiz;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.R.integer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class QuizActivity extends ActionBarActivity {

	private Button mTrueButton;
	private Button mFalseButton;
	
	private Button mNextButton;
	private Button mPrevButton;
	
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
       
        mNextButton = (Button)findViewById(R.id.next_button);
        mPrevButton = (Button)findViewById(R.id.prev_button);
        
        mTrueButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				mChecker(true);
			}
		});
        
        mFalseButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
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

    }
    
    private void mNextQuestion(){
    	mCurrentIndex = (mCurrentIndex+1)==mQuestionBank.length ? mQuestionBank.length-1:++mCurrentIndex%mQuestionBank.length;
    	
    	if (mQuestionBank.length==mCurrentIndex+1)
    			Toast.makeText(QuizActivity.this, R.string.end_of_questions, Toast.LENGTH_SHORT).show();
    	
		mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getQuestion());
    }
    
    private void mPrevQuestion(){
    	mCurrentIndex = (mCurrentIndex-1)<0 ? 0:--mCurrentIndex%mQuestionBank.length;
    	
     	if (mCurrentIndex==0)
			Toast.makeText(QuizActivity.this, R.string.start_of_questions, Toast.LENGTH_SHORT).show();
    	
    	mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getQuestion());
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

 
     public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
