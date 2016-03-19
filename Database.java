package org.mastermind.pkg;

import java.sql.*;
import java.util.*;

public class Database {
	
	public boolean setData(Account account){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@apollo.ite.gmu.edu:1521:ite10g","mmehta1","zersoa");
			
			Statement statement = con.createStatement();
			ResultSet resultSet;
			DatabaseMetaData metadata = con.getMetaData();
			resultSet = metadata.getTables(null, null, "ACCOUNTDATA", null);
			
			if (!resultSet.next()){
				String createTable = 
						"create table ACCOUNTDATA "+
						"(NAME varchar(30) NOT NULL, "+
						"USERID varchar(15) NOT NULL, "+
						"PASSWORD varchar(30) NOT NULL, "+
						"EMAIL varchar(30), "+
						"PRIMARY KEY (USERID))";
				statement.executeUpdate(createTable);
			}

			String sql = "Insert into ACCOUNTDATA " +
						 "VALUES(?,?,?,?)";
			PreparedStatement pstmst;
			pstmst = con.prepareStatement(sql);
			pstmst.setString(1, account.getName());
			pstmst.setString(2, account.getUserId());
			pstmst.setString(3, account.getPassword());
			pstmst.setString(4, account.getEmail());
			pstmst.executeUpdate();
			statement.close();
			return true;
			
		}catch (ClassNotFoundException e){
			return false;
		}
		catch(SQLException s){
			return false;
		}
	}
	
	public ArrayList<Account> getData(){
		
		ArrayList<Account> a = new ArrayList<Account>();
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@apollo.ite.gmu.edu:1521:ite10g","mmehta1","zersoa");
			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM ACCOUNTDATA");

			while (rs.next()){
				Account account = this.makeFromResultSet(rs);
				a.add(account);
			}
			statement.close();
			
			return a;
		}
		catch (ClassNotFoundException e){
			return null;
		}
		catch(SQLException s){
			return null;
		}
		
	}
	
	public Account makeFromResultSet(ResultSet rs) throws SQLException{
		
		Account account = new Account();
		
		account.setEmail(rs.getString("EMAIL"));
		account.setName(rs.getString("NAME"));
		account.setPassword(rs.getString("PASSWORD"));
		account.setUserId(rs.getString("USERID"));
		
		return account;
	}

}
