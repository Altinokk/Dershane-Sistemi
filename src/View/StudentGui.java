package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Helper.helper;
import Model.Yonetici;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class StudentGui extends JFrame {
	Yonetici yonetici = new Yonetici();
	private JPanel contentPane;
	private JTable studentTable;
	private JTextField st_tc_text;
	private JTextField st_name_text;
	private JTextField st_tel_text;
	private JTextField st_vtel_text;
	private JTextField st_mail_text;
	private JTextField sel_id;
	private DefaultTableModel studentModel = null;
	private Object[] studentData = null;
	private JTextField st_ücret_text;
	private JTextField search_text;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentGui frame = new StudentGui();
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
	 * @throws SQLException
	 */
	public StudentGui() throws SQLException {
		setResizable(false);

		studentModel = new DefaultTableModel();
		Object[] colOgrName = new Object[4];
		colOgrName[0] = "ID";
		colOgrName[1] = "Ad Soyad";
		colOgrName[2] = "Tc No";
		colOgrName[3] = "Sýnýf";
		studentModel.setColumnIdentifiers(colOgrName);
		studentData = new Object[4];
		for (int i = 0; i < yonetici.getStudentList().size(); i++) {
			studentData[0] = yonetici.getStudentList().get(i).getId();
			studentData[1] = yonetici.getStudentList().get(i).getName();
			studentData[2] = yonetici.getStudentList().get(i).getTcno();
			studentData[3] = yonetici.getStudentList().get(i).getBrans();
			studentModel.addRow(studentData);

		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 955, 590);
		contentPane = new JPanel();
		setTitle("Dershane Sistemi");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_baslik = new JLabel("\u00D6\u011Frenci Y\u00F6netim Sistemi");
		lbl_baslik.setForeground(Color.DARK_GRAY);
		lbl_baslik.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_baslik.setBackground(Color.BLACK);
		lbl_baslik.setBounds(25, 11, 214, 31);
		contentPane.add(lbl_baslik);

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
		btnExit.setBounds(834, 25, 80, 36);
		contentPane.add(btnExit);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 113, 378, 412);
		contentPane.add(scrollPane);

		studentTable = new JTable(studentModel);
		studentTable.setForeground(Color.BLACK);
		studentTable.setBackground(Color.WHITE);
		scrollPane.setViewportView(studentTable);
		studentTable.getColumnModel().getColumn(0).setPreferredWidth(2);
		studentTable.getColumnModel().getColumn(3).setPreferredWidth(5);
		studentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					sel_id.setText(studentTable.getValueAt(studentTable.getSelectedRow(), 0).toString());
				} catch (Exception ex) {

				}
			}
		});

		JLabel lblNewLabel_2 = new JLabel("*T.C. Numaraniz :");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblNewLabel_2.setBounds(518, 165, 124, 31);
		contentPane.add(lblNewLabel_2);

		st_tc_text = new JTextField();
		st_tc_text.setColumns(10);
		st_tc_text.setBounds(653, 172, 195, 23);
		contentPane.add(st_tc_text);

		JLabel lblNewLabel_2_1_1 = new JLabel("*Ad Soyad :");
		lblNewLabel_2_1_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblNewLabel_2_1_1.setBounds(518, 221, 124, 23);
		contentPane.add(lblNewLabel_2_1_1);

		st_name_text = new JTextField();
		st_name_text.setColumns(10);
		st_name_text.setBounds(653, 224, 195, 23);
		contentPane.add(st_name_text);

		JLabel lblNewLabel_2_1_2 = new JLabel("Tel No :");
		lblNewLabel_2_1_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblNewLabel_2_1_2.setBounds(518, 275, 124, 23);
		contentPane.add(lblNewLabel_2_1_2);

		st_tel_text = new JTextField();
		st_tel_text.setColumns(10);
		st_tel_text.setBounds(653, 278, 195, 23);
		contentPane.add(st_tel_text);

		JLabel lblNewLabel_2_1_3 = new JLabel("Veli Tel No :");
		lblNewLabel_2_1_3.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblNewLabel_2_1_3.setBounds(518, 329, 124, 23);
		contentPane.add(lblNewLabel_2_1_3);

		st_vtel_text = new JTextField();
		st_vtel_text.setColumns(10);
		st_vtel_text.setBounds(653, 332, 195, 23);
		contentPane.add(st_vtel_text);

		JLabel lblNewLabel_2_1 = new JLabel("*Sinif :");
		lblNewLabel_2_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(518, 118, 80, 23);
		contentPane.add(lblNewLabel_2_1);

		JLabel lbl_brans = new JLabel("E-Mail :");
		lbl_brans.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lbl_brans.setBounds(518, 375, 124, 23);
		contentPane.add(lbl_brans);

		JDateChooser sel_date = new JDateChooser();
		sel_date.setBounds(734, 72, 114, 20);
		contentPane.add(sel_date);

		st_mail_text = new JTextField();
		st_mail_text.setColumns(10);
		st_mail_text.setBounds(653, 378, 195, 23);
		contentPane.add(st_mail_text);

		JLabel lblNewLabel_1 = new JLabel("Kullanici ID");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		lblNewLabel_1.setBounds(494, 472, 77, 14);
		contentPane.add(lblNewLabel_1);

		sel_id = new JTextField();
		sel_id.setColumns(10);
		sel_id.setBounds(488, 497, 98, 23);
		contentPane.add(sel_id);

		JComboBox cmb = new JComboBox();
		cmb.setModel(new DefaultComboBoxModel(new String[] { "12.S\u0131n\u0131f", "11.S\u0131n\u0131f",
				"10.S\u0131n\u0131f", "9.S\u0131n\u0131f", "8.S\u0131n\u0131f", "7.S\u0131n\u0131f",
				"6.S\u0131n\u0131f", "5.S\u0131n\u0131f", "4.S\u0131n\u0131f" }));
		cmb.setBounds(653, 121, 195, 22);
		contentPane.add(cmb);

		JButton btn_del = new JButton("Sil");
		btn_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (sel_id.getText().length() == 0) {
					helper.showMsg("Lütfen Bir Öðrenci Seçiniz");
				} else {
					if (helper.askmsg("sure")) {
						int selectID = Integer.parseInt(sel_id.getText());
						try {
							boolean control = yonetici.delStudent(selectID);
							if (control) {
								helper.showMsg("succsess");
								sel_id.setText(null);
								updateStudentModel();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}

			}
		});
		btn_del.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btn_del.setBackground(new Color(224, 255, 255));
		btn_del.setBounds(618, 490, 114, 35);
		contentPane.add(btn_del);

		JButton btn_add = new JButton((Icon) null);
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = "";
				try {
					date = sdf.format(sel_date.getDate());
				} catch (Exception e2) {
					// TODO: handle exception
				}

				if (st_tc_text.getText().length() == 0 || st_name_text.getText().length() == 0 || date.length() == 0) {
					helper.showMsg("fill");

				} else {

					try {
						String sinif = cmb.getSelectedItem().toString();
						int ücret = Integer.parseInt(st_ücret_text.getText());
						boolean control = yonetici.addStudent(st_tc_text.getText(), st_name_text.getText(), sinif,
								st_tel_text.getText(), st_mail_text.getText(), st_vtel_text.getText(), ücret, date);
						if (control) {
							helper.showMsg("succsess");
							st_tc_text.setText(null);
							st_name_text.setText(null);
							st_tel_text.setText(null);
							st_mail_text.setText(null);
							st_vtel_text.setText(null);
							st_ücret_text.setText(null);
							updateStudentModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		btn_add.setText("Ekle");
		btn_add.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btn_add.setBackground(new Color(224, 255, 255));
		btn_add.setBounds(754, 490, 114, 35);
		contentPane.add(btn_add);

		JLabel lbl_ucret = new JLabel("*\u00DCcret :");
		lbl_ucret.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lbl_ucret.setBounds(518, 424, 124, 23);
		contentPane.add(lbl_ucret);

		st_ücret_text = new JTextField();
		st_ücret_text.setColumns(10);
		st_ücret_text.setBounds(653, 427, 195, 23);
		contentPane.add(st_ücret_text);

		JLabel lblNewLabel_2_2 = new JLabel("*Kay\u0131t Tarihi :");
		lblNewLabel_2_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblNewLabel_2_2.setBounds(620, 72, 104, 23);
		contentPane.add(lblNewLabel_2_2);

		JLabel search_lbl = new JLabel("Ara :");
		search_lbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		search_lbl.setBounds(22, 69, 56, 23);
		contentPane.add(search_lbl);

		search_text = new JTextField();
		search_text.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent e) {
				String searchKey = search_text.getText();
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(studentModel);
				studentTable.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter(searchKey));
			}
		});
		search_text.setColumns(10);
		search_text.setBounds(88, 72, 237, 23);
		contentPane.add(search_text);

		JLabel Backgraound = new JLabel(new ImageIcon(getClass().getResource("bluee.jpeg")));
		Backgraound.setBounds(0, 0, 941, 553);
		contentPane.add(Backgraound);

		studentTable.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selid = Integer.parseInt(studentTable.getValueAt(studentTable.getSelectedRow(), 0).toString());
					String selname = studentTable.getValueAt(studentTable.getSelectedRow(), 1).toString();
					String seltcno = studentTable.getValueAt(studentTable.getSelectedRow(), 2).toString();
					String selbrans = studentTable.getValueAt(studentTable.getSelectedRow(), 3).toString();

					try {
						boolean control = yonetici.updateStudent(selid, seltcno, selname, selbrans);

						if (control) {
							helper.showMsg("Güncelleme basarili");
						} else {
							helper.showMsg("mistake");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

		});

	}

	public void updateStudentModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) studentTable.getModel();
		clearModel.setRowCount(0);
		studentData = new Object[4];
		for (int i = 0; i < yonetici.getStudentList().size(); i++) {
			studentData[0] = yonetici.getStudentList().get(i).getId();
			studentData[1] = yonetici.getStudentList().get(i).getName();
			studentData[2] = yonetici.getStudentList().get(i).getTcno();
			studentData[3] = yonetici.getStudentList().get(i).getBrans();
			studentModel.addRow(studentData);
		}
	}
}
