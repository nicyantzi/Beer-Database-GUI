package beerGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JDesktopPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JTextPane;

public class PopupSucessFailure {

	private JFrame frame;
	 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopupSucessFailure window = new PopupSucessFailure(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PopupSucessFailure(String message) {
		initialize(message);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String message) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		JButton btnNewButton = new JButton("CLOSE WINDOW");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(148, 243, 153, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Beer Database Access Confirmation");
		lblNewLabel.setBounds(0, 0, 450, 30);
		lblNewLabel.setForeground(new Color(51, 102, 153));
		lblNewLabel.setFont(new Font("Kohinoor Bangla", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		
		JDesktopPane messagePane = new JDesktopPane();
		messagePane.setBackground(new Color(51, 102, 153));
		messagePane.setBounds(18, 31, 413, 200);
		frame.getContentPane().add(messagePane);
		
		JTextPane textPane = new JTextPane();
		textPane.setForeground(Color.WHITE);
		textPane.setBackground(new Color(0, 153, 204));
		textPane.setBounds(6, 6, 401, 188);
		messagePane.add(textPane);
		textPane.setText(message);
		
				
		}
	
}
