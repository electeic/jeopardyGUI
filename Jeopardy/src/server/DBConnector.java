package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import game_logic.User;
import com.mysql.jdbc.Driver;

public class DBConnector {
//	private Connection conn;
//	private static final String dbName = "LoginCredentials";
//	private static final String userTable = "LoginCredentials.Login";
//	private static final String userIdCol = "username";
//	private static final String passwordCol = "encryptPass";
//	
//	public DBConnector() {
//		try {
//			new Driver();
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost/LoginCredentials?user=root&password=developer&useSSL=false");		
//		}
//		catch (SQLException sqle){
//			System.out.println("SQL exception in DBConnector: "+sqle.getMessage());
//		} 
//		catch (ClassNotFoundException e) {
//			System.out.println("class not found exception in DBConnector: "+e.getMessage());
//		}
//	}
//	
//	//returns a User object with these credentials
//	public User insertUser(String username, String password) throws SQLException{
//		PreparedStatement ps = conn.prepareStatement("INSERT INTO "+userTable+" ("+userIdCol+", "+passwordCol+") VALUES (?, ?)");
//		ps.setString(1, username);
//		ps.setString(2, password);
//		ps.executeUpdate();
//		return new User(username, password);
//	}
//	
//	//returns a Boolean with whether these credentials exist
//	public Boolean checkUser(String username, String password) throws SQLException{
//		PreparedStatement ps = conn.prepareStatement("SELECT * FROM "+userTable+" WHERE "+userIdCol+" = ? AND "+passwordCol+" = ?");
//		ps.setString(1, username);
//		ps.setString(2, password);
//		ResultSet rs = ps.executeQuery();
//		return rs.next();
//	}
//
//	public void close(){
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			System.out.println("exception in closing db connection "+e.getMessage());
//		}
//	}
}
