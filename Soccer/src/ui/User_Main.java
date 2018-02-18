package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class User_Main extends JFrame {

	private JPanel contentPane;
	
	private JFrame windows;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User_Main frame = new User_Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public User_Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Begin");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(230, 174, 156, 51);
		windows = this;
		
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane option = new JOptionPane();
				String types[] = {"Offensive","Defensive","Goal Keeper","Center_Defensive","Center_Offensive"};
				int ret = option.showOptionDialog(windows, "Choose  the type of your player", "Choose carefully",
						JOptionPane.CLOSED_OPTION,JOptionPane.QUESTION_MESSAGE, null, types, types[0]);
				if(ret == 0) {
					Offensive_Player off = new Offensive_Player();
					off.setVisible(true);
				}
			}
			
		});
		
		contentPane.add(btnNewButton);
		
		JLabel lblWelcome = new JLabel("Welcome football fan");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(140, 13, 308, 16);
		contentPane.add(lblWelcome);
		
		
	}
}
