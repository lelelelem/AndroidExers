package com.example.dialog;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;

public class DialogClassBad extends DialogFragment {

	
	private DialogInterface.OnClickListener mKeyListener = new OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			if (which==DialogInterface.BUTTON_POSITIVE)
				sendExtra(Activity.RESULT_OK);
			else
				sendExtra(Activity.RESULT_CANCELED);
		}
	}; 
	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		View view= getActivity().getLayoutInflater().inflate(R.layout.dialogfragmentbad, null);
		
		return new AlertDialog.Builder(getActivity()).setTitle("Dialog")
					.setView(view)
					.setPositiveButton(R.string.bad_choice_positive, mKeyListener)
					.setNegativeButton(R.string.bad_choice_negative, mKeyListener)
					.create();		
	}
	
	private void sendExtra(int result_code){
		
		Intent intent=new Intent();
		getTargetFragment().onActivityResult(getTargetRequestCode(), result_code, intent);
		
		
	}
	
	
}
