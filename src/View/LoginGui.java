package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;

import Helper.DBConection;

import Helper.helper;
import Model.Yonetici;

import javax.swing.JButton;
import javax.swing.Icon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginGui extends JFrame {
	private JTextField fld_ogr_tc;
	private JPasswordField fld_ogr_pss;
	private DBConection conn = new DBConection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGui frame = new LoginGui();
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
	public LoginGui() {
		setResizable(false);
		setBackground(new Color(255, 255, 204));
		getContentPane().setBackground(Color.WHITE);
		setTitle("Dersahane Otamasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 476);
		getContentPane().setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Dershane Kay\u0131t Sistemine Ho\u015Fgeldiniz");
		lblNewLabel_1.setBounds(88, 96, 347, 25);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel(new ImageIcon(getClass().getResource("libary.png")));
		lblNewLabel.setBounds(210, 10, 103, 75);
		getContentPane().add(lblNewLabel);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(144, 238, 144));
		tabbedPane.setBounds(38, 132, 446, 284);
		getContentPane().add(tabbedPane);

		JLabel lblbackGround = new JLabel(new ImageIcon(getClass().getResource("blueee.jpeg")));
		lblbackGround.setBackground(Color.WHITE);
		lblbackGround.setBounds(0, 0, 524, 447);
		lblbackGround.setOpaque(false);
		getContentPane().add(lblbackGround);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		tabbedPane.addTab("Yönetici giriþi", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("T.C. Numaran\u0131z :");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblNewLabel_2.setBounds(20, 44, 124, 31);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("\u015Eifre :");
		lblNewLabel_2_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(20, 121, 113, 23);
		panel.add(lblNewLabel_2_1);

		fld_ogr_tc = new JTextField();
		fld_ogr_tc.setBounds(154, 51, 195, 23);
		panel.add(fld_ogr_tc);
		fld_ogr_tc.setColumns(10);

		fld_ogr_pss = new JPasswordField();
		fld_ogr_pss.setBounds(153, 125, 196, 23);
		panel.add(fld_ogr_pss);

		JButton btn_register = new JButton(new ImageIcon(getClass().getResource("loginn.png")));
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_ogr_tc.getText().length() == 0 || fld_ogr_pss.getText().length() == 0) {
					helper.showMsg("fill");
				} else {
					boolean key = true;
					try {
						Connection con = conn.ConnDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while (rs.next()) {
							if (fld_ogr_tc.getText().equals(rs.getString("tcno"))
									&& fld_ogr_pss.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("yönetici")) {
									Yonetici yon = new Yonetici();
									yon.setId(rs.getInt("id"));
									yon.setTcno(rs.getString("tcno"));
									yon.setPassword(rs.getString("password"));
									yon.setName(rs.getString("name"));
									yon.setType(rs.getString("type"));
									yon.setBrans(rs.getString("brans"));
									yon.setEmail(rs.getString("email"));
									yon.setTelno(rs.getString("telno"));
									YoneticiGui yonGui = new YoneticiGui(yon);

									yonGui.setVisible(true);
									dispose();
									key = false;
								}
							}

						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if (key) {
						helper.showMsg("T.C. Kimlik numaranýzý veya Þifrenizi hatalý girdiniz.!");
					}
				}
			}
		});

		btn_register.setOpaque(false);
		btn_register.setForeground(new Color(138, 43, 226));
		btn_register.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btn_register.setBackground(new Color(144, 238, 144));
		btn_register.setBounds(100, 174, 249, 55);
		panel.add(btn_register);

	}
}
