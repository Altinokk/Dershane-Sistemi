package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Yonetici;
import Model.grup;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class grupMenuGui extends JFrame {
	grup g = new grup();
	Yonetici yonetici = new Yonetici();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					grupMenuGui frame = new grupMenuGui();
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
	public grupMenuGui() {
		setResizable(false);
		setTitle("Derhane Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_welcome = new JLabel("Gruplama  Men\u00FC");
		lbl_welcome.setForeground(Color.DARK_GRAY);
		lbl_welcome.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_welcome.setBounds(10, 23, 205, 36);
		contentPane.add(lbl_welcome);
		
		JButton btnGrupOlusturma = new JButton(new ImageIcon(getClass().getResource("reportt.png")));
		btnGrupOlusturma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GrupGui	grupGui = new  GrupGui();
					grupGui.setVisible(true);
					dispose();

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnGrupOlusturma.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnGrupOlusturma.setText("Grup Olu\u015Fturma");
		btnGrupOlusturma.setOpaque(false);
		btnGrupOlusturma.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGrupOlusturma.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btnGrupOlusturma.setBackground(new Color(220, 220, 220));
		btnGrupOlusturma.setBounds(167, 153, 128, 103);
		contentPane.add(btnGrupOlusturma);
		
		JButton btnGrupGncellemeVe = new JButton(new ImageIcon(getClass().getResource("report.png")));
		btnGrupGncellemeVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try {
						GrupUpdateGui grupGui = new  GrupUpdateGui();
						grupGui.setVisible(true);
						dispose();

					} catch (Exception e2) {
						e2.printStackTrace();
					}
			}
			
		});
		btnGrupGncellemeVe.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnGrupGncellemeVe.setText("Grup G\u00FCncelleme");
		btnGrupGncellemeVe.setOpaque(false);
		btnGrupGncellemeVe.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGrupGncellemeVe.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btnGrupGncellemeVe.setBackground(new Color(220, 220, 220));
		btnGrupGncellemeVe.setBounds(440, 153, 128, 103);
		contentPane.add(btnGrupGncellemeVe);
		
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
		btnExit.setBounds(665, 23, 80, 36);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(getClass().getResource("bluee.jpeg")));
		lblNewLabel.setBounds(0, 0, 767, 442);
		contentPane.add(lblNewLabel);
	}

}
