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
	

	public DataNode() {
		next = null;
		
	}

}

@SuppressWarnings("serial")
class LinkedList implements Serializable{
	String Tag = "DATANODE";
	DataNode head, tail,holdInfo;
	int size = 0;
	boolean first=true;
	
	public static final String TAG="Datanode";

	LinkedList() {
		head = null;
		tail = null;
	}

	public void add(String detail) {
		DataNode temp = new DataNode();
		
		Log.i(Tag, "Adding Details on DataNode");
		if (detail==(null))
			return;
		
		if (size == 0) {
			head = temp;
			tail = temp;
			head.detail = detail;
			holdInfo=head;
			tail.next = null;
		} else {
			tail.next = temp;
			tail = temp;
			tail.next = null;
			tail.detail = detail;
		}
		size++;
	}

	public DataNode getInfo() {
		DataNode temp;
		
		
		if(holdInfo==null&&!first){
			Log.i(TAG, "NULL ALREADY");
			holdInfo=head;
			return null;
		}
		
		if (first){
			Log.i(TAG, "FIRST TIME");
			holdInfo=head;
			first=false;
		}
		
		temp=holdInfo;
		
		holdInfo=holdInfo.next;
		
		return temp;
	}
	
	public String[] getAllInfo(){
		
		String infos[]=new String[size];
		int detail=0;
		
		for (holdInfo=head; holdInfo!=null; holdInfo=holdInfo.next) {
			infos[detail]=holdInfo.detail;
			Log.i(TAG, "["+holdInfo.detail+"] is added");
			detail++;
		}
		
		return infos;
	}
	
public void printAllInfo(){
		
	
		
		for (holdInfo=head; holdInfo!=null; holdInfo=holdInfo.next) {
			Log.i(TAG, "["+holdInfo.detail+"]");
		}
		
	}

}