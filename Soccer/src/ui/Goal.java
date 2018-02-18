package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.AttackModel;
import models.GoalModel;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JButton;

public class Goal extends JFrame {

	private JPanel contentPane;

	private GoalModel model;

	private List<JComboBox> components;
	
	private List<JLabel> labels;

	private String options[] = {"High","Medium","Low","Don't Care"};
	
	private String fields[] = {"Potential","Foot","Crossing","Finishing","Short","Attacking","Head"
								,"Volleys","Dribbling","FreeKick","Control","Accel","ShotPow","LongShot"};
	private JFrame window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Offensive_Player frame = new Offensive_Player();
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
	public Goal() {
		window = this;
		this.model = new GoalModel();

		this.components = new ArrayList<JComboBox>();
		this.labels = new ArrayList<JLabel>();
		setTitle("Offensive Player");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 803, 540);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox Potential = new JComboBox(options);
		Potential.setBounds(60, 55, 94, 28);
		Potential.setName("potential");
		contentPane.add(Potential);
		this.components.add(Potential);

		JLabel lblPotential = new JLabel("Potential");
		lblPotential.setBounds(60, 33, 94, 16);
		contentPane.add(lblPotential);
		this.labels.add(lblPotential);

		JButton btnValidate = new JButton("Validate");
		btnValidate.setBounds(339, 455, 97, 25);
		btnValidate.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				boolean empty = checkNotEmpty();
				if(!empty) {
					HashMap<String,Double> map = new HashMap<String,Double>();
					List<Double> listD = new ArrayList<Double>();
					int i = 0;
					for(JComboBox c : components) {
						System.out.println(c.getName());
						map.put(c.getName(), model.choiceToWeight(c.getSelectedItem().toString()));
						listD.add(model.choiceToWeight(c.getSelectedItem().toString()));
					}
					Double res = model.calculateScore(map,listD);
					JOptionPane.showMessageDialog(window,
						    "Result",
						    res.toString(),
						    JOptionPane.INFORMATION_MESSAGE);
					
				}

			}


		});
		contentPane.add(btnValidate);

		String optFoot[] = {"Right","Left"};
		JComboBox diving = new JComboBox(optFoot);
		diving.setName("diving");
		diving.setBounds(193, 55, 94, 28);
		contentPane.add(diving);
		this.components.add(diving);

		JComboBox Handling = new JComboBox(options);
		Handling.setName("handling");
		Handling.setBounds(330, 58, 94, 28);
		contentPane.add(Handling);
		this.components.add(Handling);

		JComboBox Positionning = new JComboBox(options);
		Positionning.setName("positionning");
		Positionning.setBounds(453, 58, 94, 28);
		contentPane.add(Positionning);
		this.components.add(Positionning);

		JComboBox Kicking = new JComboBox(options);
		Kicking.setName("kicking");
		Kicking.setBounds(583, 58, 94, 28);
		contentPane.add(Kicking);
		this.components.add(Kicking);

		JLabel lblDiving = new JLabel("Diving");
		lblDiving.setBounds(193, 33, 94, 16);
		contentPane.add(lblDiving);

		JLabel lblReflexes = new JLabel("Reflexes");
		lblReflexes.setBounds(60, 109, 153, 16);
		contentPane.add(lblReflexes);

		JLabel lblPositionning = new JLabel("Positionning");
		lblPositionning.setBounds(453, 33, 94, 16);
		contentPane.add(lblPositionning);

		JLabel lblKicking = new JLabel("Kicking");
		lblKicking.setBounds(583, 33, 94, 16);
		contentPane.add(lblKicking);

		JComboBox Reflexes = new JComboBox(options);
		Reflexes.setName("reflexes");
		Reflexes.setBounds(60, 144, 94, 28);
		contentPane.add(Reflexes);
		this.components.add(Reflexes);

		JLabel lblHandling = new JLabel("Handling");
		lblHandling.setBounds(330, 33, 94, 16);
		contentPane.add(lblHandling);
	}


	private boolean checkNotEmpty() {
		boolean empty = false;
		for(JComboBox c : this.components) {
			if(c.getSelectedItem().toString().equals("Empty"))
				empty = true;
		}
		if(empty) {
			JOptionPane.showMessageDialog(this,
				    "You have to choose for every field",
				    "Warning",
				    JOptionPane.WARNING_MESSAGE);
		}
		return empty;


	}
}
