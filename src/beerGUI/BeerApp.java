package beerGUI;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JPanel;


import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.*;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.Panel;
import java.awt.Button;
import java.awt.TextField;
import javax.swing.JDesktopPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;

public class BeerApp {

	private JFrame frame;
	private JFrame popup;
	private JTextField beerNameTF;
	private JTextField beerPerTF;
	private JTextField beerIbuTF;
	private JTextField customerPriceTF;
	private JTextField establishmentPriceTF;
	private JTextField firstYYYY;
	private JTextField secondYYYY;
	private JTextField firstMM;
	private JTextField firstDD;
	private JTextField secondMM;
	private JTextField secondDD;
	private JTextField quantity_b2c;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws SQLException{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BeerApp window = new BeerApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		// Register our postrgreSQL driver
		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
		} catch (Exception cnfe) {
			System.out.println("Class Not Found Exception");
		}	
		System.out.println("Here");
		
	
	}

	/**
	 * Create the application.
	 */
	public BeerApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 51, 102));
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		popup = new JFrame();
		popup.getContentPane().setBackground(new Color(0, 50, 100));
		popup.setBounds(200, 200, 400, 300);
		popup.getContentPane().setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("BEER DATABASE ACCESS");
		lblNewLabel.setBackground(new Color(173, 216, 230));
		lblNewLabel.setBounds(6, 24, 888, 91);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Kohinoor Bangla", Font.BOLD, 60));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		
		
		JLabel lblPossibleQuerries = new JLabel("Possible Queries:");
		lblPossibleQuerries.setForeground(Color.WHITE);
		lblPossibleQuerries.setFont(new Font("Kohinoor Bangla", Font.BOLD, 25));
		lblPossibleQuerries.setBounds(35, 132, 300, 55);
		frame.getContentPane().add(lblPossibleQuerries);
		
		ImageIcon beerIcon = new ImageIcon("images/header3.jpg");
		Image beer = beerIcon.getImage();
		Image scaledBeer = beer.getScaledInstance(815, 351, Image.SCALE_SMOOTH);
		beerIcon = new ImageIcon(scaledBeer);
		
		ImageIcon beerBody = new ImageIcon("images/taps.jpg");
		Image beer2 = beerBody.getImage();
		Image scaledBeer2 = beer2.getScaledInstance(513, 322, Image.SCALE_SMOOTH);
		beerBody = new ImageIcon(scaledBeer2);
		
		JLabel beerImage = new JLabel("");
		beerImage.setHorizontalAlignment(SwingConstants.CENTER);
		beerImage.setBounds(35, 6, 824, 123);
		beerImage.setIcon(beerIcon);
		frame.getContentPane().add(beerImage);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBackground(new Color(70, 130, 180));
		btnExit.setFont(new Font("Kohinoor Bangla", Font.BOLD, 20));
		btnExit.setBounds(35, 523, 85, 38);
		frame.getContentPane().add(btnExit);
		
		JDesktopPane q4Pane2 = new JDesktopPane();
		q4Pane2.setBackground(new Color(51, 102, 153));
		q4Pane2.setBounds(374, 154, 473, 390);
		frame.getContentPane().add(q4Pane2);
		q4Pane2.setVisible(false);
		
		JLabel lblEmployee = new JLabel("Employee");
		lblEmployee.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmployee.setForeground(Color.WHITE);
		lblEmployee.setBounds(128, 36, 61, 23);
		q4Pane2.add(lblEmployee);
		
		JComboBox employeeList_b2c = new JComboBox();
		employeeList_b2c.setBounds(216, 35, 231, 27);
		q4Pane2.add(employeeList_b2c);
		
		JLabel customer = new JLabel("Customer");
		customer.setHorizontalAlignment(SwingConstants.RIGHT);
		customer.setForeground(Color.WHITE);
		customer.setBounds(128, 80, 61, 23);
		q4Pane2.add(customer);
		
		JComboBox customerList_b2c = new JComboBox();
		customerList_b2c.setBounds(216, 74, 231, 36);
		q4Pane2.add(customerList_b2c);
		
		JLabel beerb2c = new JLabel("Beer");
		beerb2c.setHorizontalAlignment(SwingConstants.RIGHT);
		beerb2c.setForeground(Color.WHITE);
		beerb2c.setBounds(128, 122, 61, 23);
		q4Pane2.add(beerb2c);
		
		JComboBox beerList_b2c = new JComboBox();
		beerList_b2c.setBounds(216, 122, 231, 24);
		q4Pane2.add(beerList_b2c);
		
		JLabel quantity = new JLabel("Quantity");
		quantity.setHorizontalAlignment(SwingConstants.RIGHT);
		quantity.setForeground(Color.WHITE);
		quantity.setBounds(128, 157, 61, 23);
		q4Pane2.add(quantity);
		
		quantity_b2c = new JTextField();
		quantity_b2c.setBounds(216, 158, 231, 22);
		q4Pane2.add(quantity_b2c);
		quantity_b2c.setColumns(10);
		
		JButton btnSubmit_1 = new JButton("SUBMIT");
		btnSubmit_1.setBounds(170, 320, 117, 29);
		q4Pane2.add(btnSubmit_1);
		
		JDesktopPane q4Pane1 = new JDesktopPane();
		q4Pane1.setBackground(new Color(51, 102, 153));
		q4Pane1.setBounds(374, 154, 463, 390);
		frame.getContentPane().add(q4Pane1);
		q4Pane1.setVisible(false);
		
		JLabel lblCustomer = new JLabel("Which establishment did this occur at?");
		lblCustomer.setHorizontalAlignment(SwingConstants.LEFT);
		lblCustomer.setForeground(Color.WHITE);
		lblCustomer.setBounds(32, 23, 316, 16);
		q4Pane1.add(lblCustomer);
		
		JComboBox b2cEstablishment = new JComboBox();
		b2cEstablishment.setBounds(42, 53, 235, 27);
		q4Pane1.add(b2cEstablishment);
		
		JButton b2cEstablishmentNext = new JButton("NEXT");
		b2cEstablishmentNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//enter info for b2c transaction at certain 
				
				q4Pane2.setVisible(true);
				
				employeeList_b2c.removeAllItems();
				customerList_b2c.removeAllItems();
				beerList_b2c.removeAllItems();
				
				String establishment = (String) b2cEstablishment.getSelectedItem();
				System.out.println(establishment);
				
				
				try{
					String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
					Connection con = DriverManager.getConnection(url, "cs421g23", "beerDB2016");
					Statement statement = con.createStatement();
					
					if(con != null){
						System.out.println("Connected to Database");
					}
					
					String insert_sql = "SELECT name FROM employee INNER JOIN establishmentemployee ON employee.employeeid = establishmentemployee.employeeid;";
					System.out.println(insert_sql);
					java.sql.ResultSet rs = statement.executeQuery(insert_sql);
					//java.sql.ResultSet rs2 = statement.executeQuery(insert_sql2);
					
					
					while (rs.next()) {
						String name = rs.getString("name");
						employeeList_b2c.addItem(name);
						System.out.println("Employee: "+name);
					}
					
					String insert_sql2 = "SELECT name FROM buyer;";
					System.out.println(insert_sql2);
					java.sql.ResultSet rs2 = statement.executeQuery(insert_sql2);


					while (rs2.next()) {
						String name = rs2.getString("name");
						customerList_b2c.addItem(name);
						System.out.println("Customer: " +name);
					}
					
					String insert_sql3 = "SELECT name FROM beer;";
					System.out.println(insert_sql3);
					java.sql.ResultSet rs3 = statement.executeQuery(insert_sql3);
					
					while (rs3.next()) {
						String name = rs3.getString("name");
						beerList_b2c.addItem(name);
						System.out.println("Beer: " +name);
					}
					
								
				} catch (SQLException error) {
					int sqlCode = error.getErrorCode();
					String sqlState = error.getSQLState();
					System.err.println("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
					
				}
				
				
				
				
			}
		});
		b2cEstablishmentNext.setBounds(176, 336, 117, 29);
		q4Pane1.add(b2cEstablishmentNext);
		
		JLabel beerBodyImage = new JLabel("");
		beerBodyImage.setBounds(367, 183, 480, 320);
		frame.getContentPane().add(beerBodyImage);
		beerBodyImage.setHorizontalAlignment(SwingConstants.CENTER);
		beerBodyImage.setIcon(beerBody);
		beerBodyImage.setVisible(true);
		
		//setup1 for query 1
		JTextPane textPane = new JTextPane();
		
		//new setup for query 1 - textframe that is scrollable
		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setBounds(367, 183, 463, 330);
		frame.getContentPane().add(scrollPane);
		scrollPane.setVisible(false);
		
		JDesktopPane q4Pane = new JDesktopPane();
		q4Pane.setBackground(new Color(51, 102, 153));
		q4Pane.setBounds(374, 154, 463, 390);
		frame.getContentPane().add(q4Pane);
		q4Pane.setVisible(false);
		
		JButton btnBusinessToCustomer = new JButton("Business to Customer Transaction");
		btnBusinessToCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				q4Pane.setVisible(false);
				q4Pane1.setVisible(true);

				
			}
		});
		btnBusinessToCustomer.setBounds(76, 95, 293, 46);
		q4Pane.add(btnBusinessToCustomer);
		
		JButton btnBusinessToBusiness = new JButton("Business to Business Transaction");
		btnBusinessToBusiness.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				q4Pane.setVisible(false);
			}
		});
		btnBusinessToBusiness.setBounds(76, 153, 293, 46);
		q4Pane.add(btnBusinessToBusiness);
		
		JLabel lblChooseATransaction = new JLabel("Choose a transaction type:");
		lblChooseATransaction.setForeground(Color.WHITE);
		lblChooseATransaction.setBounds(5, 0, 293, 26);
		q4Pane.add(lblChooseATransaction);
		

		JDesktopPane q3Panel = new JDesktopPane();
		q3Panel.setBackground(new Color(51, 102, 153));
		q3Panel.setBounds(367, 183, 463, 140);
		frame.getContentPane().add(q3Panel);
		q3Panel.setVisible(false);
		
		JLabel firstDate = new JLabel("First Date (YYYY/MM/DD) :");
		firstDate.setLocation(52, 5);
		firstDate.setSize(200, 20);
		firstDate.setForeground(Color.WHITE);
		q3Panel.add(firstDate);
		
		firstYYYY = new JTextField();
		firstYYYY.setSize(58, 26);
		firstYYYY.setLocation(234, 2);
		q3Panel.add(firstYYYY);
		firstYYYY.setColumns(4);
		
		firstMM = new JTextField();
		firstMM.setLocation(289, 2);
		firstMM.setSize(34, 26);
		firstMM.setColumns(2);
		q3Panel.add(firstMM);
		
		firstDD = new JTextField();
		firstDD.setLocation(321, 2);
		firstDD.setSize(34, 26);
		firstDD.setColumns(2);
		q3Panel.add(firstDD);
		
		JLabel secondDate = new JLabel("Second Date: (YYYY/MM/DD) :");
		secondDate.setLocation(52, 37);
		secondDate.setSize(190, 16);
		secondDate.setForeground(Color.WHITE);
		q3Panel.add(secondDate);
		
		secondYYYY = new JTextField();
		secondYYYY.setLocation(254, 31);
		secondYYYY.setSize(58, 26);
		q3Panel.add(secondYYYY);
		secondYYYY.setColumns(4);
		
		JButton q3Submit = new JButton("SUBMIT");
		q3Submit.setLocation(184, 64);
		q3Submit.setSize(90, 29);
		q3Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String firstDate = "'"+firstYYYY.getText()+"-"+firstMM.getText()+"-"+firstDD.getText()+"'";
				System.out.println(firstDate);
				String secondDate = "'"+secondYYYY.getText()+"-"+secondMM.getText()+"-"+secondDD.getText()+"'";
				System.out.println(secondDate);
				
				try{
					String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
					Connection con = DriverManager.getConnection(url, "cs421g23", "beerDB2016");
					Statement statement = con.createStatement();
					
					if(con != null){
						System.out.println("Connected to Database");
					}
					
					String insert_sql = "SELECT name, SUM((b2c_price - b2b_price)*quantity) AS profit FROM (SELECT transaction.tid, transaction.quantity, transaction.date, establishment.name, b2c.beerid, beer.b2c_price, beer.b2b_price FROM transaction INNER JOIN b2c ON transaction.tid = b2c.tid INNER JOIN establishmentemployee ON b2c.employeeid = establishmentemployee.employeeid INNER JOIN establishment ON establishmentemployee.eid = establishment.eid INNER JOIN beer ON beer.beerid = b2c.beerid) AS result WHERE ("+secondDate + " > result.date AND result.date > "+firstDate+") GROUP BY name;";
					System.out.println(insert_sql);
					java.sql.ResultSet rs = statement.executeQuery(insert_sql);
					
					
					while (rs.next()) {
						String name = rs.getString("name");
						String profit = rs.getString("profit");
						String currentText = textPane.getText();
						if (currentText.equals("")){
							textPane.setText(name+": "+profit);
						} else {
							String newText = currentText +"\n"+ name+": "+profit;
							textPane.setText(newText);
						}
						System.out.println("Name: "+name);
					}
				
					
				} catch (SQLException error) {
					int sqlCode = error.getErrorCode();
					String sqlState = error.getSQLState();
					System.err.println("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
					
				}
				
				
				
				
				
				
			}
		});
		
		secondMM = new JTextField();
		secondMM.setLocation(310, 31);
		secondMM.setSize(34, 26);
		secondMM.setColumns(2);
		q3Panel.add(secondMM);
		
		secondDD = new JTextField();
		secondDD.setLocation(344, 31);
		secondDD.setSize(34, 26);
		secondDD.setColumns(2);
		q3Panel.add(secondDD);
		q3Panel.add(q3Submit);
		
		
	
		JDesktopPane query2Pane = new JDesktopPane();
		query2Pane.setBackground(new Color(51, 102, 153));
		query2Pane.setBounds(374, 154, 463, 390);
		frame.getContentPane().add(query2Pane);
		query2Pane.setVisible(false);
		
		JLabel beerPer = new JLabel("Beer Percentage:");
		beerPer.setForeground(Color.WHITE);
		beerPer.setHorizontalAlignment(SwingConstants.RIGHT);
		beerPer.setBounds(6, 42, 139, 24);
		query2Pane.add(beerPer);
		
		beerNameTF = new JTextField();
		beerNameTF.setBounds(157, 5, 288, 26);
		query2Pane.add(beerNameTF);
		beerNameTF.setColumns(10);
		
		JLabel beerName = new JLabel("Beer Name:");
		beerName.setForeground(Color.WHITE);
		beerName.setHorizontalAlignment(SwingConstants.RIGHT);
		beerName.setBounds(6, 6, 139, 24);
		query2Pane.add(beerName);
		
		beerPerTF = new JTextField();
		beerPerTF.setColumns(10);
		beerPerTF.setBounds(157, 41, 288, 26);
		query2Pane.add(beerPerTF);
		
		JLabel beerIbu = new JLabel("Beer IBU");
		beerIbu.setForeground(Color.WHITE);
		beerIbu.setHorizontalAlignment(SwingConstants.RIGHT);
		beerIbu.setBounds(6, 84, 139, 24);
		query2Pane.add(beerIbu);
		
		JLabel beerType = new JLabel("Beer Type");
		beerType.setForeground(Color.WHITE);
		beerType.setHorizontalAlignment(SwingConstants.RIGHT);
		beerType.setBounds(6, 124, 139, 24);
		query2Pane.add(beerType);
		
		//JList list = new JList();
		//list.setBounds(137, 120, 202, 28);
		//desktopPane.add(list);
		String[] beerTypes = {"Ale","Pale Ale", "Lager","Stout", "Porter"};
		//list.setLayoutOrientation(JList.VERTICAL_WRAP);
		
		JComboBox beerTypeList = new JComboBox(beerTypes);
		beerTypeList.setBounds(160, 123, 285, 28);
		query2Pane.add(beerTypeList);
		
		JLabel beerStyle = new JLabel("Beer Style");
		beerStyle.setForeground(Color.WHITE);
		beerStyle.setHorizontalAlignment(SwingConstants.RIGHT);
		beerStyle.setBounds(6, 168, 139, 24);
		query2Pane.add(beerStyle);
		
		JLabel company = new JLabel("Company");
		company.setForeground(Color.WHITE);
		company.setHorizontalAlignment(SwingConstants.RIGHT);
		company.setBounds(6, 210, 139, 24);
		query2Pane.add(company);
		
		JLabel customerPrice = new JLabel("Customer Price");
		customerPrice.setForeground(Color.WHITE);
		customerPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		customerPrice.setBounds(6, 259, 139, 24);
		query2Pane.add(customerPrice);
		
		String[] beerStyles = {"Amber", "Blond", "Brown", "Honey", "IPA"};
		
		JComboBox beerStyleList = new JComboBox(beerStyles);
		beerStyleList.setBounds(160, 167, 285, 28);
		query2Pane.add(beerStyleList);
		
		//Query to Database to find correctlist of companies
		
		
		ArrayList<String> companyNames = new ArrayList<String>();
		
		JComboBox companyList = new JComboBox();

		
		try{
			String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
			Connection con = DriverManager.getConnection(url, "cs421g23", "beerDB2016");
			Statement statement = con.createStatement();
			
			if(con != null){
				System.out.println("Connected to Database");
			}
			
			String insert_sql = "SELECT name FROM company";
			System.out.println(insert_sql);
			java.sql.ResultSet rs = statement.executeQuery(insert_sql);
			
			
			while (rs.next()) {
				String name = rs.getString("name");
				companyNames.add(name);
				companyList.addItem(name);
				System.out.println("Name: "+name);
			}
		
			
		} catch (SQLException error) {
			int sqlCode = error.getErrorCode();
			String sqlState = error.getSQLState();
			System.err.println("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
			
		}
		
		System.out.println("Here4");
		companyList.setBounds(160, 209, 285, 28);
		query2Pane.add(companyList);
		
		JLabel establishmentPrice = new JLabel("Establishment Price");
		establishmentPrice.setForeground(Color.WHITE);
		establishmentPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		establishmentPrice.setBounds(6, 295, 139, 24);
		query2Pane.add(establishmentPrice);
		
		beerIbuTF = new JTextField();
		beerIbuTF.setColumns(10);
		beerIbuTF.setBounds(157, 83, 288, 26);
		query2Pane.add(beerIbuTF);
		
		customerPriceTF = new JTextField();
		customerPriceTF.setColumns(10);
		customerPriceTF.setBounds(157, 256, 288, 26);
		query2Pane.add(customerPriceTF);
		
		establishmentPriceTF = new JTextField();
		establishmentPriceTF.setColumns(10);
		establishmentPriceTF.setBounds(157, 294, 288, 26);
		query2Pane.add(establishmentPriceTF);
		
		
		JLabel popUpMessageSuccess = new JLabel("Beer was Submitted to the Database!");
		popUpMessageSuccess.setForeground(Color.WHITE);
		popUpMessageSuccess.setFont(new Font("Kohinoor Bangla", Font.BOLD, 25));
		popUpMessageSuccess.setBounds(5, 5, 800, 50);
		popup.getContentPane().add(popUpMessageSuccess);
		
		
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//METHOD TO actually add the beer to the database. 
				//Need to get all the values entered into the GUI Fields and create PostGreSQL INSERT statement
				
				
				String beerNameSQL = beerNameTF.getText();
				Integer beerPerSQL = Integer.parseInt(beerPerTF.getText());
				Integer beerIBUSQL = Integer.parseInt(beerIbuTF.getText());
				String beerTypeSQL = (String) beerTypeList.getSelectedItem();
				String beerStyleSQL = (String) beerStyleList.getSelectedItem();
				String companySQL = (String) companyList.getSelectedItem();
				Double customerPriceSQL = Double.parseDouble(customerPriceTF.getText());
				Double establishmentPriceSQL = Double.parseDouble(establishmentPriceTF.getText());
				
				
				//find beerid
				//int length = (int)(Math.log10(n)+1);
				
				// get beerid, find largest beerid then add 1
				int maxBeerId = 0;

				try{
					String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
					Connection con = DriverManager.getConnection(url, "cs421g23", "beerDB2016");
					Statement statement = con.createStatement();
					
					if(con != null){
						System.out.println("Connected to Database");
					}
					
					String insert_sql = "SELECT beerid FROM beer";
					System.out.println(insert_sql);
					java.sql.ResultSet rs = statement.executeQuery(insert_sql);
					
					//maxCid store the maximum value of cid in the database. We will use this value and 
					//add 1 to it for entering a new beer. 
					
					while (rs.next()) {
						String name = rs.getString("beerid");
						//String cidTrim = name.Trim("0");
						name.replaceFirst("^0", "");
						System.out.println(name);
						
						int cid = Integer.parseInt(name);
						
						if (maxBeerId < cid){
							maxBeerId = cid;
							System.out.println("Max CID = "+maxBeerId);
						}

					}
				
				} catch (SQLException error) {
					int sqlCode = error.getErrorCode();
					String sqlState = error.getSQLState();
					System.err.println("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
					
				}
				
				
				// find corresponding cid for company name inputted. 
				
				String cidFromCompany = "";
				
				try{
					String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
					Connection con = DriverManager.getConnection(url, "cs421g23", "beerDB2016");
					Statement statement = con.createStatement();
					
					if(con != null){
						System.out.println("Connected to Database");
					}
					
					String insert_sql = "SELECT cid FROM company WHERE name = '"+companySQL+"'";
					
					System.out.println(insert_sql);
					java.sql.ResultSet rs = statement.executeQuery(insert_sql);
					
					
					while (rs.next()) {
						cidFromCompany = rs.getString("cid");
						System.out.println("Cid: "+cidFromCompany);
												
					}
									
					
				} catch (SQLException error) {
					int sqlCode = error.getErrorCode();
					String sqlState = error.getSQLState();
					System.err.println("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
					
				}
				
				
				
				//


				int lengthOfCurrentMaxBeerId = (int)(Math.log10(maxBeerId)+1);
				System.out.println("Lenght of Max Cid = "+ lengthOfCurrentMaxBeerId);
				
				int numberOfZeroes = 6 - lengthOfCurrentMaxBeerId;
				
				String newBeerId = "";
				for(int i = 0; i < numberOfZeroes; i++){
					newBeerId = newBeerId + "0";
				}
				newBeerId = newBeerId + (maxBeerId+1);
				System.out.println("New Beer Id = "+ newBeerId);
				
				
				//need to find corresponding cid for company
				
				
				System.out.println(beerNameSQL +", " + beerPerSQL+"%, " + beerIBUSQL+" IBU's, "+ beerTypeSQL+ ", "+beerStyleSQL+ ", " + companySQL+ ", "+customerPriceSQL+", "+establishmentPriceSQL);
				
				//check if constraints are met
				
				if( (beerNameSQL != null) && (beerPerSQL ==(int)beerPerSQL) && (beerPerSQL != null) && (beerIBUSQL ==(int)beerIBUSQL) && (beerIBUSQL != null) && (customerPriceSQL != null) && (establishmentPrice != null)) {
					//INSERT STATEMENT
					
					try{
						String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
						Connection con = DriverManager.getConnection(url, "cs421g23", "beerDB2016");
						Statement statement = con.createStatement();
						
						if(con != null){
							System.out.println("Connected to Database");
						}
						
						String insert_sql = "INSERT INTO beer (beerid, name, alcoholper, ibu, type, style, cid, b2c_price, b2b_price) VALUES (\'" 
								+ newBeerId + "\', '" + beerNameSQL + "\', " + beerPerSQL + ", " + beerIBUSQL +
								", \'" + beerTypeSQL + "\', \'" + beerStyleSQL + "\', \'" + cidFromCompany + "\', " + customerPriceSQL + 
								", " + establishmentPriceSQL + ");";
						System.out.println(insert_sql);
						
						int x = frame.getX();
						int y = frame.getY();
						int width = frame.getWidth();
						int height = frame.getHeight();
						
						popup.setBounds((width/2), (y+height/2), 500, 100);
						System.out.println("Here");
						popup.setVisible(true);
						popup.getContentPane().add(popUpMessageSuccess);

	
						java.sql.ResultSet rs = statement.executeQuery(insert_sql);
						
					} catch (SQLException error) {
						int sqlCode = error.getErrorCode();
						String sqlState = error.getSQLState();
						System.err.println("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
						
					}
					
					
					
					
				} else {
								
					
				}
					
				
			}
		});
		btnSubmit.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnSubmit.setBounds(157, 332, 139, 40);
		query2Pane.add(btnSubmit);
		
		JButton btnHome = new JButton("HOME");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textPane.setText("");
				scrollPane.setVisible(false);
				beerBodyImage.setVisible(true);
				btnHome.setVisible(false);
				query2Pane.setVisible(false);
				q3Panel.setVisible(false);
				q4Pane.setVisible(false);
				q4Pane1.setVisible(false);
				q4Pane2.setVisible(false);
				
				
			}
		});
		btnHome.setFont(new Font("Kohinoor Bangla", Font.BOLD, 20));
		btnHome.setBackground(new Color(70, 130, 180));
		btnHome.setBounds(132, 523, 85, 38);
		frame.getContentPane().add(btnHome);
		btnHome.setVisible(false);

		
		//First Query: SELECT name FROM beer;
		
		JButton btnQuery = new JButton("1. Show all beers in database.");
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				 
				textPane.setText("");
				beerBodyImage.setVisible(false);
				scrollPane.setBounds(367, 183, 400, 330);
				scrollPane.setVisible(true);
				btnHome.setVisible(true);
				query2Pane.setVisible(false);	
				q3Panel.setVisible(false);
				q4Pane.setVisible(false);
				q4Pane1.setVisible(false);
				q4Pane2.setVisible(false);
				
				//case 1 query
				try{
					String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
					Connection con = DriverManager.getConnection(url, "cs421g23", "beerDB2016");
					Statement statement = con.createStatement();
					
					if(con != null){
						System.out.println("Connected to Database");
					}
					
					String insert_sql = "SELECT name FROM beer";
					System.out.println(insert_sql);
					java.sql.ResultSet rs = statement.executeQuery(insert_sql);
					
					
					while (rs.next()) {
						String name = rs.getString("name");
						String currentText = textPane.getText();
						if (currentText.equals("")){
							textPane.setText(name);
						} else {
							String newText = currentText +"\n"+ name;
							textPane.setText(newText);
						}
						System.out.println("Name: "+name);
					}
				
					
				} catch (SQLException error) {
					int sqlCode = error.getErrorCode();
					String sqlState = error.getSQLState();
					System.err.println("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
					
				}
				
				
				
			}
		});
		btnQuery.setBounds(35, 183, 300, 55);
		frame.getContentPane().add(btnQuery);
		
		//Query 2
		
		JButton btnQuery_1 = new JButton("2. Enter a Beer Into the Database");
		btnQuery_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textPane.setText("");
				beerBodyImage.setVisible(false);
				scrollPane.setVisible(false);
				btnHome.setVisible(true);
				query2Pane.setVisible(true);
				q3Panel.setVisible(false);
				q4Pane.setVisible(false);
				q4Pane.setVisible(false);
				q4Pane.setVisible(false);
				
				beerNameTF.setText("");
				beerPerTF.setText("");
				beerIbuTF.setText("");
				customerPriceTF.setText("");
				establishmentPriceTF.setText("");
				
			
			}
		});
		
		btnQuery_1.setBounds(35, 250, 300, 55);
		frame.getContentPane().add(btnQuery_1);
		
		
		//Query 3:
		
		JButton btnQuery_2 = new JButton("3. Total Profit Between Two Dates.");
		btnQuery_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				textPane.setText("");
				beerBodyImage.setVisible(false);
				scrollPane.setBounds(367, 313, 463, 200);
				scrollPane.setVisible(true);
				btnHome.setVisible(true);
				query2Pane.setVisible(false);
				q3Panel.setVisible(true);
				q4Pane.setVisible(false);
				q4Pane.setVisible(false);
				q4Pane1.setVisible(false);
				q4Pane2.setVisible(false);
				
				
	
				
			}
		});
		btnQuery_2.setBounds(35, 317, 300, 55);
		frame.getContentPane().add(btnQuery_2);
		
		JButton btnQuery_3 = new JButton("4. Enter a Transaction");
		btnQuery_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				beerBodyImage.setVisible(false);
				btnHome.setVisible(true);
				query2Pane.setVisible(false);
				q3Panel.setVisible(false);
				scrollPane.setVisible(false);
				q4Pane.setVisible(true);
				q4Pane1.setVisible(false);
				q4Pane2.setVisible(false);
				
				
				//update new values into b2cEstablishment combobox list
				
				try{
					String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
					Connection con = DriverManager.getConnection(url, "cs421g23", "beerDB2016");
					Statement statement = con.createStatement();
					
					if(con != null){
						System.out.println("Connected to Database");
					}
					
					String insert_sql = "SELECT name FROM establishment";
					System.out.println(insert_sql);
					java.sql.ResultSet rs = statement.executeQuery(insert_sql);
					
					
					while (rs.next()) {
						String name = rs.getString("name");
						companyNames.add(name);
						b2cEstablishment.addItem(name);
						System.out.println("Name: "+name);
					}
								
				} catch (SQLException error) {
					int sqlCode = error.getErrorCode();
					String sqlState = error.getSQLState();
					System.err.println("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
					
				}
			}
				
			
		});
		btnQuery_3.setBounds(35, 384, 300, 55);
		frame.getContentPane().add(btnQuery_3);
		
		JButton btnQuery_4 = new JButton("Query 5");
		btnQuery_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnQuery_4.setBounds(35, 451, 300, 55);
		frame.getContentPane().add(btnQuery_4);
		

		
	}
}
