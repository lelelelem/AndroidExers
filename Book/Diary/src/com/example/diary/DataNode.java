package com.example.diary;

import java.io.Serializable;

import android.R.integer;
import android.util.Log;

@SuppressWarnings("serial")
public class DataNode implements Serializable{

	DataNode next;
	String detail;
	int type;

	public DataNode() {
		next = null;
		
	}

}

@SuppressWarnings("serial")
class LinkedList implements Serializable{
	String Tag = "t";
	DataNode head, tail,holdInfo;
	int size = 0;

	LinkedList() {
		head = null;
		tail = null;
	}

	public void add(String detail, int type) {
		DataNode temp = new DataNode();
		Log.i(Tag, "Went IN");
		if (size == 0) {
			head = temp;
			holdInfo=head;
			tail = temp;
			head.detail = detail;
			head.type = type;
			tail.next = null;
		} else {
			tail.next = temp;
			tail = temp;
			tail.next = null;
			tail.detail = detail;
			tail.type = type;

		}
		size++;
	}

	public DataNode getInfo() {
		
		
		if(holdInfo==null){
			holdInfo=head;
			return null;
		}
		
		
		holdInfo=holdInfo.next;
		
		return holdInfo;
	}
	
	public String[][] getAllInfo(){
		
		String infos[][]=new String[size][2];
		int detail=0, type=detail;
		
		for (holdInfo=head; holdInfo!=null; holdInfo=holdInfo.next) {
			infos[detail][type++]=holdInfo.detail;
			infos[detail][type]=Integer.toString(holdInfo.type);
			detail++;
			type=0;
		}
		
		return infos;
	}

}