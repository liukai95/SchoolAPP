package model.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ResultBean {
	
	public final static int ISREGISTED=3;
	public final static int PARAMETERERROR=2;
	public final static int SUCCESS=1;
	public final static int FAILED=0;
	
	private int Code;
	private int id;
	private int MessageNum;
	private List Message=new ArrayList();
	
	public int getCode(){
		return this.Code;
	}
	public void setCode(int code){
		Code=code;
	}
	public int getMessageNum(){
		return this.MessageNum;
	}
	public void setMessageNum(int num){
		MessageNum=num;
	}
	public List getMessage(){
		return this.Message;
	}
	public void addResult(Object o){
		Message.add(o);
	}
	public void addResult(Collection c){
		Message.add(c);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
