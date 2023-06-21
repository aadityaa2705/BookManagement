package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Service {
	static Connection con = null;
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gitHub?user=root&password=sql123");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean login(String user,String pass){
		String query = "select username,password,name from user_data where username=? and password=?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1,user);
			pstmt.setString(2, pass);
			ResultSet rs = pstmt.executeQuery();
			if(!rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
