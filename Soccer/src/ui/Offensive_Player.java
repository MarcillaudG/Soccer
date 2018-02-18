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

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JButton;

public class Offensive_Player extends JFrame {

	private JPanel contentPane;

	private AttackModel model;

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
	public Offensive_Player() {
		window = this;
		this.model = new AttackModel();

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
		JComboBox Foot = new JComboBox(optFoot);
		Foot.setName("preferred_foot");
		Foot.setBounds(193, 55, 94, 28);
		contentPane.add(Foot);
		this.components.add(Foot);

		JComboBox Crossing = new JComboBox(options);
		Crossing.setName("crossing");
		Crossing.setBounds(330, 58, 94, 28);
		contentPane.add(Crossing);
		this.components.add(Crossing);

		JComboBox Finishing = new JComboBox(options);
		Finishing.setName("finishing");
		Finishing.setBounds(453, 58, 94, 28);
		contentPane.add(Finishing);
		this.components.add(Finishing);

		JComboBox Short = new JComboBox(options);
		Short.setName("short_passing");
		Short.setBounds(583, 58, 94, 28);
		contentPane.add(Short);
		this.components.add(Short);

		JLabel lblPreferredFoot = new JLabel("Preferred foot");
		lblPreferredFoot.setBounds(193, 33, 94, 16);
		contentPane.add(lblPreferredFoot);

		JLabel lblAttackingWorkRate = new JLabel("Attacking work rate");
		lblAttackingWorkRate.setBounds(60, 109, 153, 16);
		contentPane.add(lblAttackingWorkRate);

		JLabel lblFinishing = new JLabel("Finishing");
		lblFinishing.setBounds(453, 33, 94, 16);
		contentPane.add(lblFinishing);

		JLabel lblShortPassing = new JLabel("Short passing");
		lblShortPassing.setBounds(583, 33, 94, 16);
		contentPane.add(lblShortPassing);

		JComboBox Attacking = new JComboBox(options);
		Attacking.setName("attacking_work_rate");
		Attacking.setBounds(60, 144, 94, 28);
		contentPane.add(Attacking);
		this.components.add(Attacking);

		JLabel lblCrossing = new JLabel("Crossing");
		lblCrossing.setBounds(330, 33, 94, 16);
		contentPane.add(lblCrossing);

		JComboBox Head = new JComboBox(options);
		Head.setName("heading_accuracy");
		Head.setBounds(226, 144, 94, 28);
		contentPane.add(Head);
		this.components.add(Head);

		JLabel lblHeadingAccuracy = new JLabel("Heading accuracy");
		lblHeadingAccuracy.setBounds(226, 109, 113, 16);
		contentPane.add(lblHeadingAccuracy);

		JComboBox Volley = new JComboBox(options);
		Volley.setName("volleys");
		Volley.setBounds(374, 144, 94, 28);
		contentPane.add(Volley);
		this.components.add(Volley);

		JLabel lblVolleys = new JLabel("Volleys");
		lblVolleys.setBounds(374, 109, 113, 16);
		contentPane.add(lblVolleys);

		JComboBox Dribling = new JComboBox(options);
		Dribling.setName("dribbling");
		Dribling.setBounds(514, 144, 94, 28);
		contentPane.add(Dribling);
		this.components.add(Dribling);

		JComboBox FreeKick = new JComboBox(options);
		FreeKick.setName("free_kick_accuracy");
		FreeKick.setBounds(60, 231, 94, 28);
		contentPane.add(FreeKick);
		this.components.add(FreeKick);

		JLabel lblDribling = new JLabel("Dribling");
		lblDribling.setBounds(514, 109, 113, 16);
		contentPane.add(lblDribling);

		JLabel lblFreeKickAccuracy = new JLabel("Free kick accuracy");
		lblFreeKickAccuracy.setBounds(60, 202, 113, 16);
		contentPane.add(lblFreeKickAccuracy);

		JComboBox BallControl = new JComboBox(options);
		BallControl.setName("ball_control");
		BallControl.setBounds(193, 231, 94, 28);
		contentPane.add(BallControl);
		this.components.add(BallControl);

		JLabel lblBallControl = new JLabel("Ball control");
		lblBallControl.setBounds(193, 202, 113, 16);
		contentPane.add(lblBallControl);

		JComboBox Accel = new JComboBox(options);
		Accel.setName("acceleration");
		Accel.setBounds(330, 231, 94, 28);
		contentPane.add(Accel);
		this.components.add(Accel);

		JLabel lblAcceleration = new JLabel("Acceleration");
		lblAcceleration.setBounds(330, 202, 113, 16);
		contentPane.add(lblAcceleration);

		JComboBox ShotPow = new JComboBox(options);
		ShotPow.setName("shot_power");
		ShotPow.setBounds(466, 231, 94, 28);
		contentPane.add(ShotPow);
		this.components.add(ShotPow);

		JLabel lblShotPower = new JLabel("Shot power");
		lblShotPower.setBounds(466, 202, 113, 16);
		contentPane.add(lblShotPower);

		JComboBox LongShots = new JComboBox(options);
		LongShots.setName("long_shots");
		LongShots.setBounds(605, 231, 94, 28);
		contentPane.add(LongShots);
		this.components.add(LongShots);

		JLabel lblLongShots = new JLabel("Long shots");
		lblLongShots.setBounds(605, 202, 113, 16);
		contentPane.add(lblLongShots);
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
