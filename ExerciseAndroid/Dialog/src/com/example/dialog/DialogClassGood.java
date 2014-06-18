package com.example.dialog;


import java.util.zip.Inflater;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

public class DialogClassGood extends DialogFragment {

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
		
		View view= getActivity().getLayoutInflater().inflate(R.layout.dialogfragmentgood, null);
		
		return new AlertDialog.Builder(getActivity()).setTitle("Dialog")
					.setView(view)
					.setPositiveButton(R.string.good_choice_positive, mKeyListener)
					.setNegativeButton(R.string.good_choice_negative, mKeyListener)
					.create();		
	}
	
	private void sendExtra(int result_code){
		
		
		getTargetFragment().onActivityResult(getTargetRequestCode(), result_code, new Intent());
		
		
	}
	
}
