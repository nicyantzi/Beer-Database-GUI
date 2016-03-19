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

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
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
	private JTextField quantity_b2b;
	private JTextField salaryIncreaseAmount;
	private String employeeSalaryName;
	private JTextField salaryDecreaseAmount;
	
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
	
//	private static ImageIcon createImageIcon(String path, String description){
//		java.net.URL imgURL = BeerApp.class.getResource(path);
//		if (imgURL != null) {
//			return new ImageIcon(imgURL, description);
//		}else {
//			System.err.println("Couldn't find file: " + path);
//			return null;
//		}
//	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 51, 102));
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//popup window
		popup = new JFrame();
		popup.setBounds(300, 300, 450, 300);
		popup.getContentPane().setLayout(null);
		popup.setVisible(false);
		
		
		JButton btnNewButton = new JButton("CLOSE WINDOW");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popup.setVisible(false);
				
			}
		});
		btnNewButton.setBounds(148, 243, 153, 29);
		popup.getContentPane().add(btnNewButton);
		
		JLabel popupTitle = new JLabel("Beer Database Access Confirmation");
		popupTitle.setBounds(0, 0, 450, 30);
		popupTitle.setForeground(new Color(51, 102, 153));
		popupTitle.setFont(new Font("Kohinoor Bangla", Font.BOLD, 20));
		popupTitle.setHorizontalAlignment(SwingConstants.CENTER);
		popup.getContentPane().add(popupTitle);
		
		JDesktopPane messagePane = new JDesktopPane();
		messagePane.setBackground(new Color(51, 102, 153));
		messagePane.setBounds(18, 31, 413, 200);
		popup.getContentPane().add(messagePane);
		
		JTextPane textPanePopup = new JTextPane();
		textPanePopup.setForeground(Color.WHITE);
		textPanePopup.setFont(new Font("Kohinoor Bangla", Font.PLAIN, 16));

		textPanePopup.setBackground(new Color(0, 153, 204));
		textPanePopup.setBounds(6, 6, 401, 188);
		messagePane.add(textPanePopup);
		
		//popup
		
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
		
		ImageIcon beerIcon = new ImageIcon(BeerApp.class.getResource("/images/header3.jpg"));
		Image beer = beerIcon.getImage();
		Image scaledBeer = beer.getScaledInstance(815, 351, Image.SCALE_SMOOTH);
		beerIcon = new ImageIcon(scaledBeer);
		
		ImageIcon beerBody = new ImageIcon(BeerApp.class.getResource("/images/taps.jpg"));
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
		
		JDesktopPane q5Pane = new JDesktopPane();
		q5Pane.setBackground(new Color(51, 102, 153));
		q5Pane.setBounds(374, 154, 473, 390);
		frame.getContentPane().add(q5Pane);
		q5Pane.setVisible(false);
		
		JLabel lblSelectEmployee = new JLabel("Employee:");
		lblSelectEmployee.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSelectEmployee.setForeground(Color.WHITE);
		lblSelectEmployee.setBounds(6, 36, 123, 20);
		q5Pane.add(lblSelectEmployee);
		
		JComboBox<String> salaryEmployeeList = new JComboBox<String>();
		salaryEmployeeList.setBounds(141, 34, 217, 27);
		q5Pane.add(salaryEmployeeList);
		
		JLabel lblIncreaseSalaryBy = new JLabel("Increase Salary By:");
		lblIncreaseSalaryBy.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIncreaseSalaryBy.setForeground(Color.WHITE);
		lblIncreaseSalaryBy.setBounds(6, 175, 123, 20);
		q5Pane.add(lblIncreaseSalaryBy);
		
		salaryIncreaseAmount = new JTextField();
		salaryIncreaseAmount.setBounds(141, 172, 294, 29);
		q5Pane.add(salaryIncreaseAmount);
		salaryIncreaseAmount.setColumns(10);
		
		JTextPane currentSalaryField = new JTextPane();
		currentSalaryField.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		currentSalaryField.setForeground(Color.WHITE);
		currentSalaryField.setBackground(new Color(0, 153, 204));
		currentSalaryField.setBounds(141, 89, 294, 55);
		q5Pane.add(currentSalaryField);
		
		
		
		
		
		
		
		JLabel lblCurrentSalary = new JLabel("Current Salary:");
		lblCurrentSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCurrentSalary.setForeground(Color.WHITE);
		lblCurrentSalary.setBounds(6, 101, 123, 20);
		q5Pane.add(lblCurrentSalary);
		
		
		JButton salaryEmployeeSelect = new JButton("SELECT");
		salaryEmployeeSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String employee = (String) salaryEmployeeList.getSelectedItem();
				
				try{
					String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
					Connection con = DriverManager.getConnection(url, "cs421g23", "beerDB2016");
					Statement statement = con.createStatement();
					
					if(con != null){
						System.out.println("Connected to Database");
					}
					
					String insert_sql = "SELECT salary FROM employee WHERE name = '"+employee+"';";
					System.out.println(insert_sql);
					java.sql.ResultSet rs = statement.executeQuery(insert_sql);
					
					
					while (rs.next()) {
						String salary = rs.getString("salary");
						currentSalaryField.setText(salary);
					}
								
				} catch (SQLException error) {
					int sqlCode = error.getErrorCode();
					String sqlState = error.getSQLState();
					System.err.println("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
					String errorPrint = ("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
					
					popup.setVisible(true);
					textPanePopup.setText(errorPrint);
				}
				
				
			}
		});
		salaryEmployeeSelect.setBounds(370, 33, 97, 29);
		q5Pane.add(salaryEmployeeSelect);
		
		JLabel lblDecreaseSalaryBy = new JLabel("Decrease Salary By:");
		lblDecreaseSalaryBy.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDecreaseSalaryBy.setForeground(Color.WHITE);
		lblDecreaseSalaryBy.setBounds(6, 222, 123, 20);
		q5Pane.add(lblDecreaseSalaryBy);
		
		salaryDecreaseAmount = new JTextField();
		salaryDecreaseAmount.setColumns(10);
		salaryDecreaseAmount.setBounds(141, 213, 294, 29);
		q5Pane.add(salaryDecreaseAmount);
		
		
		
		JDesktopPane q4Pane4 = new JDesktopPane();
		q4Pane4.setBackground(new Color(51, 102, 153));
		q4Pane4.setBounds(374, 154, 473, 390);
		frame.getContentPane().add(q4Pane4);
		q4Pane4.setVisible(false);
		
		JLabel lblCompanyEmployee = new JLabel("Company Employee (Seller)");
		lblCompanyEmployee.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCompanyEmployee.setForeground(Color.WHITE);
		lblCompanyEmployee.setBounds(21, 50, 191, 27);
		q4Pane4.add(lblCompanyEmployee);
		
		JLabel lblEstablishmentEmployee = new JLabel("Establishment Employee (Buyer)");
		lblEstablishmentEmployee.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstablishmentEmployee.setForeground(Color.WHITE);
		lblEstablishmentEmployee.setBounds(6, 100, 206, 28);
		q4Pane4.add(lblEstablishmentEmployee);
		
		JLabel lblBeer = new JLabel("Beer");
		lblBeer.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBeer.setForeground(Color.WHITE);
		lblBeer.setBounds(69, 155, 143, 27);
		q4Pane4.add(lblBeer);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuantity.setForeground(Color.WHITE);
		lblQuantity.setBounds(69, 210, 143, 27);
		q4Pane4.add(lblQuantity);
		
		JComboBox<String> companyEmployee_b2b = new JComboBox<String>();
		companyEmployee_b2b.setBounds(224, 51, 231, 27);
		q4Pane4.add(companyEmployee_b2b);
		
		JComboBox<String> establishmentEmployee_b2b = new JComboBox<String>();
		establishmentEmployee_b2b.setBounds(224, 101, 231, 27);
		q4Pane4.add(establishmentEmployee_b2b);
		
		JComboBox<String> beer_b2b = new JComboBox<String>();
		beer_b2b.setBounds(224, 156, 231, 27);
		q4Pane4.add(beer_b2b);
		
		quantity_b2b = new JTextField();
		quantity_b2b.setColumns(10);
		quantity_b2b.setBounds(224, 210, 223, 27);
		q4Pane4.add(quantity_b2b);
		
		JButton button = new JButton("SUBMIT");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//take values from b2b transaction and insert the appropriate data into database. 
				
				//1. b2b 
				//2. transaction
				//3. sellstype
				
				Integer b2bQuantity = 0;
				
				try {
					if(quantity_b2b.getText() != null){
						b2bQuantity = Integer.parseInt(quantity_b2b.getText());
					}
				}
				catch (NumberFormatException error){
					b2bQuantity = 0;
				}
				String companyEmployee = (String) companyEmployee_b2b.getSelectedItem();
				String establishmentEmployee = (String) establishmentEmployee_b2b.getSelectedItem();
				String beer = (String) beer_b2b.getSelectedItem();
				
				
				
				if(b2bQuantity != 0 && companyEmployee != null && establishmentEmployee != null && beer != null){
					LocalDate date = LocalDate.now();
					
					SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
					Date now = new Date();
					System.out.println(formatter.format(now));
					
					
					int maxTid = 0;
					String newTid = "";
					
					try{
						String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
						Connection con = DriverManager.getConnection(url, "cs421g23", "beerDB2016");
						Statement statement = con.createStatement();
						
						if(con != null){
							System.out.println("Connected to Database");
						}
						
						
						//insert into transaction
						
						String insert_sql = "SELECT tid FROM transaction;";
						System.out.println(insert_sql);
						java.sql.ResultSet rs = statement.executeQuery(insert_sql);
						
						
						while (rs.next()) {
							String tid = rs.getString("tid");
							tid.replace("^0", "");

							
							int tidInt = Integer.parseInt(tid);
							
							if (maxTid < tidInt){
								maxTid = tidInt;
							}
							int lengthCurrentMaxTid = (int)(Math.log10(maxTid+1)+1);
							System.out.println("Lenght of Max Cid = "+ lengthCurrentMaxTid);
							
							int numberOfZeroes = 6 - lengthCurrentMaxTid;
							
							newTid = "";
							for(int i = 0; i < numberOfZeroes; i++){
								newTid = newTid + "0";
							}
							newTid = newTid + (maxTid+1);
						}
						
						
						String insert_sql2 = "INSERT INTO transaction (tid, date, time, quantity) VALUES ('"+newTid+"', '"+date+"', '"+(formatter.format(now))+"', "+b2bQuantity+");";
						System.out.println(insert_sql2);
						statement.executeUpdate(insert_sql2);
						
					
						//insert into b2b
						
						String companyEmployeeId = "";
						String establishmentEmployeeId = "";
						String beerId = "";
						
						String insert_sql3 = "SELECT employeeid FROM employee WHERE name = '"+companyEmployee+"';";
						System.out.println(insert_sql3);
						java.sql.ResultSet rs3 = statement.executeQuery(insert_sql3);
						
						while (rs3.next()) {
							companyEmployeeId = rs3.getString("employeeid");
							
						}
						String insert_sql4 = "SELECT employeeid FROM employee WHERE name = '"+establishmentEmployee+"';";
						System.out.println(insert_sql4);
						java.sql.ResultSet rs4 = statement.executeQuery(insert_sql4);
						
						while (rs4.next()) {
							establishmentEmployeeId = rs4.getString("employeeid");
							
						}
						String insert_sql5 = "SELECT beerid FROM beer WHERE name = '"+beer+"';";
						System.out.println(insert_sql5);
						java.sql.ResultSet rs5 = statement.executeQuery(insert_sql5);
						
						while (rs5.next()) {
							beerId = rs5.getString("beerid");
							
						}
						
						String insert_sql6 = "INSERT INTO b2b (tid, establishmentbuyerid, companysellerid, beerid) VALUES ('"+newTid+"', '"+establishmentEmployeeId+"', '"+companyEmployeeId+"', '"+beerId+"');";
						System.out.println(insert_sql6);
						statement.executeUpdate(insert_sql6);
						
						
						//insert into sellstype
						
						//need eid and beerid
						
						String eid ="";
						
						String insert_sql7 = "SELECT eid FROM establishmentemployee WHERE employeeid = '"+establishmentEmployeeId+"';";
						System.out.println(insert_sql7);
						java.sql.ResultSet rs7 = statement.executeQuery(insert_sql7);
						
						while (rs7.next()) {
							eid = rs7.getString("eid");
							
						}
						String insert_sql8 = "INSERT INTO sellstype (beerid, eid) SELECT '"+beerId+"', '"+eid+"' WHERE NOT EXISTS ( SELECT eid FROM sellstype WHERE eid = '"+eid+"');";
						System.out.println(insert_sql8);
						statement.executeUpdate(insert_sql8);
						
						
						//popup sucess
						
						String message = "Success. All appropriate data for a B2B transaction has been inserted into the database.";
						
						popup.setVisible(true);
						textPanePopup.setText(message);
						
						
									
					} catch (SQLException error) {
						int sqlCode = error.getErrorCode();
						String sqlState = error.getSQLState();
						System.err.println("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
						String errorPrint = ("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
						
						popup.setVisible(true);
						textPanePopup.setText(errorPrint);

					}
					
					
				} else {
					
					String inputError = "Incorrect input, please try again.";
					popup.setVisible(true);
					textPanePopup.setText(inputError);
					
				}
			
				
				
			}
		});
		button.setBounds(171, 315, 117, 29);
		q4Pane4.add(button);
		
		JDesktopPane q4Pane3 = new JDesktopPane();
		q4Pane3.setBackground(new Color(51, 102, 153));
		q4Pane3.setBounds(374, 154, 473, 390);
		frame.getContentPane().add(q4Pane3);
		q4Pane3.setVisible(false);
		
		JLabel lblWhichCompanyIs = new JLabel("Which Company is Selling Beer?");
		lblWhichCompanyIs.setHorizontalAlignment(SwingConstants.LEFT);
		lblWhichCompanyIs.setForeground(Color.WHITE);
		lblWhichCompanyIs.setBounds(6, 17, 316, 16);
		q4Pane3.add(lblWhichCompanyIs);
		
		JLabel lblWhichEstablishmentIs = new JLabel("Which Establishment is Buying Beer?");
		lblWhichEstablishmentIs.setHorizontalAlignment(SwingConstants.LEFT);
		lblWhichEstablishmentIs.setForeground(Color.WHITE);
		lblWhichEstablishmentIs.setBounds(6, 95, 316, 16);
		q4Pane3.add(lblWhichEstablishmentIs);
		
		JComboBox<String> companyList_b2b = new JComboBox<String>();
		companyList_b2b.setBounds(34, 45, 250, 27);
		q4Pane3.add(companyList_b2b);
		
		JComboBox<String> establishmentList_b2b = new JComboBox<String>();
		establishmentList_b2b.setBounds(34, 130, 250, 27);
		q4Pane3.add(establishmentList_b2b);
		
		JButton b2bNext = new JButton("NEXT");
		b2bNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				q4Pane3.setVisible(false);
				q4Pane4.setVisible(true);
				
				//populate the company, establishment employee lists as well as the beer list <- reflects the beer that the company sells.
				
				String companyB2B = (String) companyList_b2b.getSelectedItem();
				String establishmentB2B = (String) establishmentList_b2b.getSelectedItem();
				
				try{
					String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
					Connection con = DriverManager.getConnection(url, "cs421g23", "beerDB2016");
					Statement statement = con.createStatement();
					
					if(con != null){
						System.out.println("Connected to Database");
					}
					
					String insert_sql = "SELECT employee.name from companyemployee INNER JOIN company ON companyemployee.cid = company.cid INNER JOIN employee ON companyemployee.employeeid = employee.employeeid WHERE company.name = '"+companyB2B+"';";
					System.out.println(insert_sql);
					java.sql.ResultSet rs = statement.executeQuery(insert_sql);
					
					
					while (rs.next()) {
						String name = rs.getString("name");
						companyEmployee_b2b.addItem(name);
					}
					
					String insert_sql2 = "SELECT employee.name from establishmentemployee INNER JOIN establishment ON establishmentemployee.eid = establishment.eid INNER JOIN employee ON establishmentemployee.employeeid = employee.employeeid WHERE establishment.name = '"+establishmentB2B+"';";
					System.out.println(insert_sql2);
					java.sql.ResultSet rs2 = statement.executeQuery(insert_sql2);
					
					
					while (rs2.next()) {
						String name = rs2.getString("name");
						establishmentEmployee_b2b.addItem(name);
					}
					
					String insert_sql3 = "select beer.name FROM beer INNER JOIN company ON beer.cid = company.cid WHERE company.name = '"+companyB2B+"';";

					System.out.println(insert_sql3);
					java.sql.ResultSet rs3 = statement.executeQuery(insert_sql3);
					
					
					while (rs3.next()) {
						String name = rs3.getString("name");
						beer_b2b.addItem(name);
					}
					
				} catch (SQLException error) {
					int sqlCode = error.getErrorCode();
					String sqlState = error.getSQLState();
					System.err.println("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
					String errorPrint = ("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
					
					popup.setVisible(true);
					textPanePopup.setText(errorPrint);
				}
				
			}
		});
		b2bNext.setBounds(186, 335, 117, 29);
		q4Pane3.add(b2bNext);
		
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
		
		JComboBox<String> employeeList_b2c = new JComboBox<String>();
		employeeList_b2c.setBounds(216, 35, 231, 27);
		q4Pane2.add(employeeList_b2c);
		
		JLabel customer = new JLabel("Customer");
		customer.setHorizontalAlignment(SwingConstants.RIGHT);
		customer.setForeground(Color.WHITE);
		customer.setBounds(128, 80, 61, 23);
		q4Pane2.add(customer);
		
		JComboBox<String> customerList_b2c = new JComboBox<String>();
		customerList_b2c.setBounds(216, 74, 231, 36);
		q4Pane2.add(customerList_b2c);
		
		JLabel beerb2c = new JLabel("Beer");
		beerb2c.setHorizontalAlignment(SwingConstants.RIGHT);
		beerb2c.setForeground(Color.WHITE);
		beerb2c.setBounds(128, 122, 61, 23);
		q4Pane2.add(beerb2c);
		
		JComboBox<String> beerList_b2c = new JComboBox<String>();
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
		btnSubmit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String quantity = quantity_b2c.getText();
				Integer quantity_b2c = Integer.parseInt(quantity);
				
				String employee = (String) employeeList_b2c.getSelectedItem();
				String customer = (String) customerList_b2c.getSelectedItem();
				String beer = (String) beerList_b2c.getSelectedItem();
				
				
				
				if (quantity != null && quantity_b2c != null && employee != null && customer != null && beer != null ) {
					LocalDate date = LocalDate.now();
					
					SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
					Date now = new Date();
					System.out.println(formatter.format(now));
					
					
					int maxTid = 0;
					String newTid = "";
					
					try{
						String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
						Connection con = DriverManager.getConnection(url, "cs421g23", "beerDB2016");
						Statement statement = con.createStatement();
						
						if(con != null){
							System.out.println("Connected to Database");
						}
						
						String insert_sql = "SELECT tid FROM transaction;";
						System.out.println(insert_sql);
						java.sql.ResultSet rs = statement.executeQuery(insert_sql);
						
						
						while (rs.next()) {
							String tid = rs.getString("tid");
							tid.replace("^0", "");

							
							int tidInt = Integer.parseInt(tid);
							
							if (maxTid < tidInt){
								maxTid = tidInt;
							}
							int lengthCurrentMaxTid = (int)(Math.log10(maxTid+1)+1);
							System.out.println("Lenght of Max Cid = "+ lengthCurrentMaxTid);
							
							int numberOfZeroes = 6 - lengthCurrentMaxTid;
							
							newTid = "";
							for(int i = 0; i < numberOfZeroes; i++){
								newTid = newTid + "0";
							}
							newTid = newTid + (maxTid+1);
							System.out.println("New Beer Id = "+ newTid);
						}
						
						String insert_sql2 = "INSERT INTO transaction (tid, date, time, quantity) VALUES ('"+newTid+"', '"+date+"', '"+(formatter.format(now))+"', "+quantity_b2c+");";
						System.out.println(insert_sql2);
						statement.executeUpdate(insert_sql2);
						
					
						//need memberId, employeeId, beerId
						
						String memberId = "";
						String employeeId = "";
						String beerId = "";
						
						String insert_sql3 = "SELECT memberid FROM buyer WHERE name = '"+customer+"';";
						System.out.println(insert_sql3);
						java.sql.ResultSet rs3 = statement.executeQuery(insert_sql3);
						
						while (rs3.next()) {
							memberId = rs3.getString("memberid");
							
						}
						String insert_sql4 = "SELECT employeeid FROM employee WHERE name = '"+employee+"';";
						System.out.println(insert_sql4);
						java.sql.ResultSet rs4 = statement.executeQuery(insert_sql4);
						
						while (rs4.next()) {
							employeeId = rs4.getString("employeeid");
							
						}
						String insert_sql5 = "SELECT beerid FROM beer WHERE name = '"+beer+"';";
						System.out.println(insert_sql5);
						java.sql.ResultSet rs5 = statement.executeQuery(insert_sql5);
						
						while (rs5.next()) {
							beerId = rs5.getString("beerid");
							
						}
						
						String insert_sql6 = "INSERT INTO b2c (tid, memberid, employeeid, beerid) VALUES ('"+newTid+"', '"+memberId+"', '"+employeeId+"', '"+beerId+"');";
						System.out.println(insert_sql6);
						statement.executeUpdate(insert_sql6);
						
						
						//popup success
						
						String message = "Success. All appropriate data for a B2C transaction has been inserted into the database.";
						
						popup.setVisible(true);
						textPanePopup.setText(message);
						
									
					} catch (SQLException error) {
						int sqlCode = error.getErrorCode();
						String sqlState = error.getSQLState();
						System.err.println("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
						String errorPrint = ("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
						
						popup.setVisible(true);
						textPanePopup.setText(errorPrint);
						
					}
					
				}else{
					//popup failure
					String inputError = "Incorrect input, please try again.";
					popup.setVisible(true);
					textPanePopup.setText(inputError);
				}
				
					
				
			}
		});
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
		
		JComboBox<String> b2cEstablishment = new JComboBox<String>();
		b2cEstablishment.setBounds(42, 53, 235, 27);
		q4Pane1.add(b2cEstablishment);
		
		JButton b2cEstablishmentNext = new JButton("NEXT");
		b2cEstablishmentNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//enter info for b2c transaction at certain 
				
				q4Pane1.setVisible(false);
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
					
					//populate the employe list
					String insert_sql = "SELECT employee.name FROM employee INNER JOIN establishmentemployee ON employee.employeeid = establishmentemployee.employeeid INNER JOIN establishment ON establishment.eid = establishmentemployee.eid WHERE establishment.name = '"+ establishment +"';";
					System.out.println(insert_sql);
					java.sql.ResultSet rs = statement.executeQuery(insert_sql);					
					
					while (rs.next()) {
						String name = rs.getString("name");
						employeeList_b2c.addItem(name);
						System.out.println("Employee: "+name);
					}
							
					//populate the buyer list
					String insert_sql2 = "SELECT name FROM buyer;";
					System.out.println(insert_sql2);
					java.sql.ResultSet rs2 = statement.executeQuery(insert_sql2);


					while (rs2.next()) {
						String name = rs2.getString("name");
						customerList_b2c.addItem(name);
						System.out.println("Customer: " +name);
					}
					
					String insert_sql3 = "select beer.name from sellstype INNER JOIN establishment ON sellstype.eid = establishment.eid  INNER JOIN beer ON sellstype.beerid = beer.beerid WHERE establishment.name = '"+ establishment +"';";
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
					String errorPrint = ("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
					
					popup.setVisible(true);
					textPanePopup.setText(errorPrint);
					
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
				q4Pane2.setVisible(false);
				
			}
		});
		btnBusinessToCustomer.setBounds(76, 95, 293, 46);
		q4Pane.add(btnBusinessToCustomer);
		
		JButton btnBusinessToBusiness = new JButton("Business to Business Transaction");
		btnBusinessToBusiness.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				q4Pane.setVisible(false);
				q4Pane3.setVisible(true);
				companyList_b2b.removeAllItems();
				establishmentList_b2b.removeAllItems();
				
				
				//populate companyList_b2b & establishmentList_b2b
				
				try{
					String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
					Connection con = DriverManager.getConnection(url, "cs421g23", "beerDB2016");
					Statement statement = con.createStatement();
					
					if(con != null){
						System.out.println("Connected to Database");
					}
					
					String insert_sql = "SELECT name FROM company;";
					System.out.println(insert_sql);
					java.sql.ResultSet rs = statement.executeQuery(insert_sql);
					
					
					while (rs.next()) {
						String name = rs.getString("name");
						companyList_b2b.addItem(name);
					}
					
					String insert_sql2 = "SELECT name FROM establishment;";
					System.out.println(insert_sql2);
					java.sql.ResultSet rs2 = statement.executeQuery(insert_sql2);
					
					
					while (rs2.next()) {
						String name = rs2.getString("name");
						establishmentList_b2b.addItem(name);
					}
					
				} catch (SQLException error) {
					int sqlCode = error.getErrorCode();
					String sqlState = error.getSQLState();
					System.err.println("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
					String errorPrint = ("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
					
					popup.setVisible(true);
					textPanePopup.setText(errorPrint);
					
				}
				
				
				
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
					String errorPrint = ("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
					
					popup.setVisible(true);
					textPanePopup.setText(errorPrint);
					
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
		
		JComboBox<?> beerTypeList = new JComboBox<Object>(beerTypes);
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
		
		JComboBox<?> beerStyleList = new JComboBox<Object>(beerStyles);
		beerStyleList.setBounds(160, 167, 285, 28);
		query2Pane.add(beerStyleList);
		
		//Query to Database to find correctlist of companies
		
		
		ArrayList<String> companyNames = new ArrayList<String>();
		
		JComboBox<String> companyList = new JComboBox<String>();

		
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
			String errorPrint = ("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
			
			popup.setVisible(true);
			textPanePopup.setText(errorPrint);
			
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
					String errorPrint = ("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
					
					popup.setVisible(true);
					textPanePopup.setText(errorPrint);
					
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
					String errorPrint = ("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
					
					popup.setVisible(true);
					textPanePopup.setText(errorPrint);
					
				}
				
				

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
						statement.executeUpdate(insert_sql);

						
						//popup success
						
						String message = "Success. Your new beer has been entered into the Beer Database.";
						
						popup.setVisible(true);
						textPanePopup.setText(message);
						
					
						
					} catch (SQLException error) {
						int sqlCode = error.getErrorCode();
						String sqlState = error.getSQLState();
						System.err.println("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
						String errorPrint = ("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
						
						popup.setVisible(true);
						textPanePopup.setText(errorPrint);
						
					}

					
				} else {
					
					String inputError = "Incorrect input, please try again.";
					popup.setVisible(true);
					textPanePopup.setText(inputError);			
					
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
				q4Pane3.setVisible(false);
				q4Pane4.setVisible(false);
				q5Pane.setVisible(false);
				
				
			}
		});
		btnHome.setFont(new Font("Kohinoor Bangla", Font.BOLD, 20));
		btnHome.setBackground(new Color(70, 130, 180));
		btnHome.setBounds(132, 523, 85, 38);
		frame.getContentPane().add(btnHome);
		btnHome.setVisible(false);
		
		
		JButton salaryApply = new JButton("APPLY");
		salaryApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String currentSalary = currentSalaryField.getText();
				double currentSalaryReal = Double.parseDouble(currentSalary);
				String updateSalary = salaryIncreaseAmount.getText();
				double updateSalaryReal = Double.parseDouble(updateSalary);
				String decreaseSalary = salaryDecreaseAmount.getText();
				double decreaseSalaryReal = Double.parseDouble(decreaseSalary);
				double newSalary = currentSalaryReal + updateSalaryReal - decreaseSalaryReal;
				String employee = (String) salaryEmployeeList.getSelectedItem();
				
				System.out.println("New Salary = "+newSalary);
				
				
 
				
				try{
					String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
					Connection con = DriverManager.getConnection(url, "cs421g23", "beerDB2016");
					Statement statement = con.createStatement();
					
					if(con != null){
						System.out.println("Connected to Database");
					}
					

					
					String insert_sql = "UPDATE employee SET SALARY = "+newSalary+" WHERE employee.name = '"+employee+"' ;";
					System.out.println(insert_sql);
					statement.executeUpdate(insert_sql);
					
					//popup sucess
					
					String message = "Success. Salary updated for " + employee+ ".";
					
					popup.setVisible(true);
					textPanePopup.setText(message);
					
					textPane.setText("");
					scrollPane.setVisible(false);
					beerBodyImage.setVisible(true);
					btnHome.setVisible(false);
					query2Pane.setVisible(false);
					q3Panel.setVisible(false);
					q4Pane.setVisible(false);
					q4Pane1.setVisible(false);
					q4Pane2.setVisible(false);
					q4Pane3.setVisible(false);
					q4Pane4.setVisible(false);
					q5Pane.setVisible(false);
		
					
					
					
				} catch (SQLException error) {
					int sqlCode = error.getErrorCode();
					String sqlState = error.getSQLState();
					System.err.println("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
					String errorPrint = ("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
					
					popup.setVisible(true);
					textPanePopup.setText(errorPrint);
				}
				
				
				
				
			}
		});
		salaryApply.setBounds(185, 325, 97, 29);
		q5Pane.add(salaryApply);

		
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
				q4Pane3.setVisible(false);
				q4Pane4.setVisible(false);
				q5Pane.setVisible(false);
				
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
					String errorPrint = ("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
					
					popup.setVisible(true);
					textPanePopup.setText(errorPrint);
					
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
				q4Pane1.setVisible(false);
				q4Pane2.setVisible(false);
				q4Pane3.setVisible(false);
				q4Pane4.setVisible(false);
				q5Pane.setVisible(false);
				
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
				q4Pane3.setVisible(false);
				q4Pane4.setVisible(false);
				q5Pane.setVisible(false);
				
				
	
				
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
				q4Pane3.setVisible(false);
				q4Pane4.setVisible(false);
				q5Pane.setVisible(false);
				
				employeeList_b2c.removeAllItems();
				customerList_b2c.removeAllItems();
				beerList_b2c.removeAllItems();
				b2cEstablishment.removeAllItems();
				companyList_b2b.removeAllItems();
				establishmentList_b2b.removeAllItems();
				beer_b2b.removeAllItems();
				quantity.setText("");
				companyEmployee_b2b.removeAllItems();
				establishmentEmployee_b2b.removeAllItems();
				quantity_b2b.setText("");
								
				
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
					String errorPrint = ("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
					
					popup.setVisible(true);
					textPanePopup.setText(errorPrint);
					
				}
			}
				
			
		});
		btnQuery_3.setBounds(35, 384, 300, 55);
		frame.getContentPane().add(btnQuery_3);
		
		JButton btnQuery_4 = new JButton("5. Update Salary for Employee");
		btnQuery_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				beerBodyImage.setVisible(false);
				btnHome.setVisible(true);
				query2Pane.setVisible(false);
				q3Panel.setVisible(false);
				scrollPane.setVisible(false);
				q4Pane.setVisible(true);
				q4Pane1.setVisible(false);
				q4Pane2.setVisible(false);
				q4Pane3.setVisible(false);
				q4Pane4.setVisible(false);
				q5Pane.setVisible(true);
				
				salaryEmployeeList.removeAllItems();
				currentSalaryField.setText("");
				salaryIncreaseAmount.setText("0");
				salaryDecreaseAmount.setText("0");
				
				
				
				try{
					String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
					Connection con = DriverManager.getConnection(url, "cs421g23", "beerDB2016");
					Statement statement = con.createStatement();
					
					if(con != null){
						System.out.println("Connected to Database");
					}
					
					String insert_sql = "SELECT name FROM employee";
					System.out.println(insert_sql);
					java.sql.ResultSet rs = statement.executeQuery(insert_sql);
					
					
					while (rs.next()) {
						String name = rs.getString("name");
						salaryEmployeeList.addItem(name);
					}
								
				} catch (SQLException error) {
					int sqlCode = error.getErrorCode();
					String sqlState = error.getSQLState();
					System.err.println("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
					String errorPrint = ("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
					
					popup.setVisible(true);
					textPanePopup.setText(errorPrint);
				}
	
				
			}
		});
		btnQuery_4.setBounds(35, 451, 300, 55);
		frame.getContentPane().add(btnQuery_4);
		
	}
}
