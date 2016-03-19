package org.mastermind.pkg;

import javax.faces.application.*;
import javax.faces.bean.*;
import javax.faces.context.*;

import java.util.*;

@ManagedBean
public class Account {
	
	private String name;
	private String userId;
	private String password;
	private String email;
	
	public void setName(String n){
		this.name = n;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setUserId(String uId){
		this.userId = uId;
	}
	
	public String getUserId(){
		return this.userId;
	}
	
	public void setPassword(String p){
		this.password = p;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setEmail(String e){
		this.email = e;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public String createAccount(){
		
		Database d = new Database();
		
		if (d.setData(this)){
			return "AccountCreated";
		}
		else{
			return "AccountFailed";
		}
	}
}
