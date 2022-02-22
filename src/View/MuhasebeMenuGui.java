package View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Model.Yonetici;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MuhasebeMenuGui extends JFrame {

	private JPanel contentPane;
	Yonetici yonetici = new Yonetici();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MuhasebeMenuGui frame = new MuhasebeMenuGui();
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
	public MuhasebeMenuGui() {
		setResizable(false);
		setTitle("Derhane Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 789, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_welcome = new JLabel("Muhasebe Men\u00FC");
		lbl_welcome.setForeground(Color.DARK_GRAY);
		lbl_welcome.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_welcome.setBounds(10, 11, 260, 36);
		contentPane.add(lbl_welcome);

		JButton btnrencidemeSistemi = new JButton(new ImageIcon(getClass().getResource("payment.png")));
		btnrencidemeSistemi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				stuPayGui MGui;
				try {
					MGui = new stuPayGui();
					MGui.setVisible(true);
					dispose();

				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}

		});
		btnrencidemeSistemi.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnrencidemeSistemi.setText("\u00D6\u011Frenci \u00D6deme ");
		btnrencidemeSistemi.setOpaque(false);
		btnrencidemeSistemi.setHorizontalTextPosition(SwingConstants.CENTER);
		btnrencidemeSistemi.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btnrencidemeSistemi.setBackground(new Color(220, 220, 220));
		btnrencidemeSistemi.setBounds(174, 103, 128, 103);
		contentPane.add(btnrencidemeSistemi);

		JButton btnremnciRapor = new JButton(new ImageIcon(getClass().getResource("report.png")));
		btnremnciRapor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StuRaporGui rGui;
				try {
					rGui = new StuRaporGui();
					rGui.setVisible(true);
					dispose();

				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});

		JButton btnExit = new JButton(new ImageIcon(getClass().getResource("exitt.png")));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				YoneticiGui yon;
				try {
					yon = new YoneticiGui(yonetici);
					yon.setVisible(true);

					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		btnExit.setOpaque(false);
		btnExit.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btnExit.setBackground(new Color(224, 255, 255));
		btnExit.setBounds(669, 30, 80, 36);
		contentPane.add(btnExit);

		btnremnciRapor.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnremnciRapor.setText("\u00D6\u011Frenci Rapor");
		btnremnciRapor.setOpaque(false);
		btnremnciRapor.setHorizontalTextPosition(SwingConstants.CENTER);
		btnremnciRapor.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btnremnciRapor.setBackground(new Color(220, 220, 220));
		btnremnciRapor.setBounds(439, 103, 128, 103);
		contentPane.add(btnremnciRapor);

		JButton btnretmenRapor = new JButton(new ImageIcon(getClass().getResource("reportt.png")));
		btnretmenRapor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherListGui tListGui;
				try {
					tListGui = new TeacherListGui();
					tListGui.setVisible(true);
					dispose();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnretmenRapor.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnretmenRapor.setText("\u00D6\u011Fretmen Rapor");
		btnretmenRapor.setOpaque(false);
		btnretmenRapor.setHorizontalTextPosition(SwingConstants.CENTER);
		btnretmenRapor.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btnretmenRapor.setBackground(new Color(220, 220, 220));
		btnretmenRapor.setBounds(306, 270, 128, 103);
		contentPane.add(btnretmenRapor);

		JLabel lblNewLabel = new JLabel(new ImageIcon(getClass().getResource("bluee.jpeg")));
		lblNewLabel.setBounds(0, 0, 773, 465);
		contentPane.add(lblNewLabel);
	}
}
