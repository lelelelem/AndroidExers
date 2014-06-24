/*Created own LinkedList
 * 
 * used in order to add more than one details
 * 
 * */


package com.example.diary;

import java.io.Serializable;

import android.util.Log;

@SuppressWarnings("serial")
public class DataNode implements Serializable{

	DataNode next;
	String detail;
	//used for checking if ID, or plain detail only
	int type;

	public DataNode() {
		next = null;
		
	}

}

@SuppressWarnings("serial")
class LinkedList implements Serializable{
	String Tag = "DATANODE";
	DataNode head, tail,holdInfo;
	int size = 0;
	
	public static final String TAG="Datanode";

	LinkedList() {
		head = null;
		tail = null;
	}

	public void add(String detail, int type) {
		DataNode temp = new DataNode();
		
		Log.i(Tag, "Adding Details on DataNode");
		if (detail==(null))
			return;
		
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
		DataNode temp;
		
		if(holdInfo==null){
			Log.i(Tag, "NULL");
			holdInfo=head;
			return null;
		}
		
		temp=holdInfo;
		
		holdInfo=holdInfo.next;
		
		return temp;
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
	
public void printAllInfo(){
		
	
		
		for (holdInfo=head; holdInfo!=null; holdInfo=holdInfo.next) {
			Log.i(TAG, "["+holdInfo.detail+"]"+"["+holdInfo.type+"]");
		}
		
	}

}