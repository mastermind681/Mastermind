package org.mastermind.pkg;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.*;

import java.util.*;

@ManagedBean
public class UserInfoBean {
	
	private String userid;
	private String password;
	
	public void setUserId(String uId){
		this.userid = uId;
	}
	
	public String getUserId(){
		return this.userid;
	}
	
	public void setPassword(String p){
		this.password = p;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public String submit(){
		LoginValidator loginValidator = new LoginValidator();
		
		if (loginValidator.validateLogin(this.userid, this.password)){
			return "LoginSuccess";
		}
		
		return "LoginFailed";
	}

}
