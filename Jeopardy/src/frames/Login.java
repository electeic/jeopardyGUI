package frames;
import java.util.Arrays;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

import game_logic.User;

import java.sql.Statement;

public class Login extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField accountUser;
	//private JTextField passwords;
	private JPasswordField passwords;
	private JButton loginB;
	private JButton createAB;
	private JLabel invalidLabel;
	private Connection con;
	private Statement st = null;
	private ResultSet rs = null;
	
	public Login(){
		super("Login");
		try {
			new Driver();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout( new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		this.setSize(600, 600);
		this.setBackground(Color.cyan);
		setupWelcomeMsg();
		setupUserPass();
		setupButton();
	}
	
	void setupWelcomeMsg(){
		JPanel containsLogin = new JPanel();
		JLabel login = new JLabel("login or create an account to play");
		containsLogin.add(login);
		JPanel containsJep = new JPanel();
		JLabel jepLabel = new JLabel("Jeopardy");
		jepLabel.setFont(jepLabel.getFont().deriveFont(18.0f));
		containsJep.add(jepLabel);
		add(containsLogin);
		add(containsJep);
		
		JPanel invalid = new JPanel();
		invalidLabel = new JLabel();
		invalid.add(invalidLabel);
		add(invalid);
		
	}

	void setupUserPass(){
		JPanel containsUserP = new JPanel();
		containsUserP.setLayout(new BoxLayout(containsUserP, BoxLayout.Y_AXIS));
		JPanel userPanel = new JPanel();
		accountUser = new JTextField("username", 20);
		userPanel.add(accountUser);
		containsUserP.add(userPanel);
		JPanel pwPanel = new JPanel();
		//passwords = new JTextField("password", 20);
		passwords = new JPasswordField("password", 20);
		pwPanel.add(passwords);
		containsUserP.add(pwPanel);
		add(containsUserP);	
		
		//TODO this could be done so much nicer
		
		accountUser.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent keyEvent) {
		        	loginB.setEnabled(!accountUser.getText().isEmpty() && !passwords.getText().isEmpty());
		        	createAB.setEnabled(!accountUser.getText().isEmpty() && !passwords.getText().isEmpty());
		    }

			@Override
			public void keyTyped(KeyEvent e) {
				loginB.setEnabled(!accountUser.getText().isEmpty() && !passwords.getText().isEmpty());
	        	createAB.setEnabled(!accountUser.getText().isEmpty() && !passwords.getText().isEmpty());
			}

			@Override
			public void keyReleased(KeyEvent e) {
				loginB.setEnabled(!accountUser.getText().isEmpty() && !passwords.getText().isEmpty());
	        	createAB.setEnabled(!accountUser.getText().isEmpty() && !passwords.getText().isEmpty());
			}
		});
		
		passwords.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent keyEvent) {
		        	loginB.setEnabled(!accountUser.getText().isEmpty() && !passwords.getText().isEmpty());
		        	createAB.setEnabled(!accountUser.getText().isEmpty() && !passwords.getText().isEmpty());
		    }

			@Override
			public void keyTyped(KeyEvent e) {
				loginB.setEnabled(!accountUser.getText().isEmpty() && !passwords.getText().isEmpty());
	        	createAB.setEnabled(!accountUser.getText().isEmpty() && !passwords.getText().isEmpty());
			}

			@Override
			public void keyReleased(KeyEvent e) {
				loginB.setEnabled(!accountUser.getText().isEmpty() && !passwords.getText().isEmpty());
	        	createAB.setEnabled(!accountUser.getText().isEmpty() && !passwords.getText().isEmpty());
			}
		});
	}
	
	void setupButton(){
		JPanel buttonPanel = new JPanel();
		loginB = new JButton("Login");
		createAB = new JButton("Create Account");
		buttonPanel.add(loginB);
		buttonPanel.add(createAB);
		loginB.setEnabled(false);
		createAB.setEnabled(false);
		add(buttonPanel);
		
		loginB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					st = con.createStatement();
					String query = "SELECT * FROM LoginCredentials.Login WHERE username=" + "'"+accountUser.getText()+"'"+";";
					ResultSet rs1 = st.executeQuery(query);
					if(rs1.next()){
							String user = rs1.getString(1);
							String pw = rs1.getString(2);
							
							if(user.equals(accountUser.getText()) && pw.equals(new String(passwords.getPassword()))){
								dispose();	
								User user2 = new User(accountUser.getText(), passwords.getPassword().toString());
				 				new StartWindowGUI(user2).setVisible(true);
				 				try {
				 					if (rs != null) {
				 						rs.close();
				 					}
				 					if (st != null) {
				 						st.close();
				 					}
				 					if (con != null) {
				 						con.close();
				 					}
				 				} catch (SQLException sqle) {
				 					System.out.println(sqle.getMessage());
				 				}
							}
				 			else{
				 				invalidLabel.setText("Invalid Username/Password");
				 			}
					}else{
						invalidLabel.setText("Invalid Username/Password");
					}
//					String user = rs1.getString(1);
					//System.out.println(user);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//rs.setString(1, accountUser.getText()); // set first variable in prepared statement
				//rs = ps.executeQuery();
			}
			
		});
		
		createAB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					st = con.createStatement();
					String query = "SELECT * FROM LoginCredentials.Login WHERE username=" + "'"+accountUser.getText()+"'"+";";
					ResultSet rs = st.executeQuery(query);
					if(rs.next()){
						invalidLabel.setText("Username already taken");
					}
					else{
						invalidLabel.setText("Created Account!");
						String query1 = "INSERT INTO Login (username, encryptPass) VALUES (?,?)";
						PreparedStatement ps = con.prepareStatement(query1);
						ps.setString(1, accountUser.getText());
						ps.setString(2, new String(passwords.getPassword()));
						ps.executeUpdate();
					}
				} catch (SQLException sqle) {
					// TODO Auto-generated catch block
					sqle.printStackTrace();
				}
			}
		});
	}
	

	
	public void connect(){
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost/LoginCredentials?user=root&password=developer&useSSL=false");
			//jdbc:mysql://localhost/StudentGrades?user=root&password=developer&useSSL=false"
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	public void stop(){
		try{
			con.close();
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	

}
	
//	public class Pair<T1, T2> implements Serializable{
//		private T1 first;
//		private T2 second;
//		
//		Pair(T1 one, T2 two){
//			first = one;
//			second = two;
//		}
//		T1 getT1(){
//			return first;
//		}
//		T2 getT2(){
//			return second;
//		}
//	}
