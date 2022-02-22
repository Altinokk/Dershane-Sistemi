package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Yonetici;

import javax.swing.JLabel;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.Icon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class YoneticiGui extends JFrame {
	static Yonetici yonetici = new Yonetici();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YoneticiGui frame = new YoneticiGui(yonetici);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */
	public YoneticiGui(Yonetici yonetici) throws SQLException {

		setResizable(false);
		setTitle("Y\u00F6netici Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnogretmen = new JButton(new ImageIcon(getClass().getResource("teacher.png")));
		btnogretmen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TeacherAddGui yonGui;
				try {
					yonGui = new TeacherAddGui();
					yonGui.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnogretmen.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btnogretmen.setText("Öðretmen Ýþlemleri" + " ");
		btnogretmen.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnogretmen.setHorizontalTextPosition(SwingConstants.CENTER);

		btnogretmen.setBackground(new Color(220, 220, 220));
		btnogretmen.setOpaque(false);
		btnogretmen.setBounds(105, 149, 141, 109);
		contentPane.add(btnogretmen);

		JButton btnogrenci = new JButton(new ImageIcon(getClass().getResource("education.png")));
		btnogrenci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentGui stuGui;
				try {
					stuGui = new StudentGui();
					stuGui.setVisible(true);
					dispose();

				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});
		btnogrenci.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btnogrenci.setBackground(new Color(224, 255, 255));
		btnogrenci.setText(" " + "  \u00D6\u011Frenci Kay\u0131t  " + " ");
		btnogrenci.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnogrenci.setHorizontalTextPosition(SwingConstants.CENTER);
		btnogrenci.setOpaque(false);
		btnogrenci.setBounds(336, 149, 141, 109);
		contentPane.add(btnogrenci);

		JLabel lbl_welcome = new JLabel("Ho\u015Fgeldiniz Say\u0131n Yönetici");
		lbl_welcome.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_welcome.setForeground(Color.DARK_GRAY);
		lbl_welcome.setBounds(25, 25, 260, 36);
		contentPane.add(lbl_welcome);

		JButton btn_muhasebe = new JButton(new ImageIcon(getClass().getResource("cash.png")));
		btn_muhasebe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MuhasebeMenuGui Mmenu = new MuhasebeMenuGui();
				Mmenu.setVisible(true);
				dispose();

			}
		});
		btn_muhasebe.setBackground(new Color(224, 255, 255));
		btn_muhasebe.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btn_muhasebe.setBounds(568, 149, 141, 109);
		btn_muhasebe.setText("Muhasebe" + " ");
		btn_muhasebe.setVerticalTextPosition(SwingConstants.BOTTOM);
		btn_muhasebe.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_muhasebe.setOpaque(false);
		contentPane.add(btn_muhasebe);

		JButton btnExit = new JButton(new ImageIcon(getClass().getResource("exitt.png")));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGui login = new LoginGui();
				login.setVisible(true);
				dispose();
			}
		});
		btnExit.setBackground(new Color(224, 255, 255));
		btnExit.setBounds(754, 25, 80, 36);
		btnExit.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btnExit.setOpaque(false);
		contentPane.add(btnExit);

		JButton btn_mail = new JButton(new ImageIcon(getClass().getResource("training.png")));
		btn_mail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MailGui mail;
				try {
					mail = new MailGui();
					mail.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_mail.setVerticalTextPosition(SwingConstants.BOTTOM);
		btn_mail.setText("Mail \u0130\u015Flemleri ");
		btn_mail.setOpaque(false);
		btn_mail.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_mail.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btn_mail.setBackground(new Color(220, 220, 220));
		btn_mail.setBounds(105, 340, 141, 109);
		contentPane.add(btn_mail);

		JButton btn_grup_ = new JButton(new ImageIcon(getClass().getResource("people.png")));
		btn_grup_.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					grupMenuGui gM = new grupMenuGui();
					gM.setVisible(true);
					dispose();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btn_grup_.setVerticalTextPosition(SwingConstants.BOTTOM);
		btn_grup_.setText("Grup \u0130\u015Flemleri");
		btn_grup_.setOpaque(false);
		btn_grup_.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_grup_.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btn_grup_.setBackground(new Color(220, 220, 220));
		btn_grup_.setBounds(336, 340, 141, 109);
		contentPane.add(btn_grup_);

		JLabel lblNewLabel = new JLabel(new ImageIcon(getClass().getResource("bluee.jpeg")));
		lblNewLabel.setOpaque(false);
		lblNewLabel.setBounds(0, 0, 874, 571);
		contentPane.add(lblNewLabel);

	}
}
