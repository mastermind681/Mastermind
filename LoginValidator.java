package org.mastermind.pkg;

import java.util.*;

public class LoginValidator {
	
	public Boolean validateLogin(String userid, String password){
		
		Database d = new Database();
		
		ArrayList<Account> a = d.getData();
		
		if (a != null){
			
			int i;
			
			for (i = 0; i < a.size(); i++){
				Account account = a.get(i);
				if (account.getUserId().equals(userid) &&
						account.getPassword().equals(password)){
					return true;
				}
			}
		}
		
		return false;
	}

}
