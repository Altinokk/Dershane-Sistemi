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
import Helper.Item;
import Helper.helper;
import Model.Yonetici;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

public class TeacherAddGui extends JFrame {

	Yonetici yonetici = new Yonetici();
	private JPanel contentPane;
	private JTextField ogr_tc_text;
	private JTextField ogr_name_text;
	private JTextField ogr_pass_text;
	private JTextField ogr_tel_text;
	private JTextField ogr_mail_text;
	private JTable ogr_table;
	private DefaultTableModel ogretmenModel = null;
	private Object[] ogretmenData = null;
	private JTextField fld_sel_id;
	private JTextField ogr_veltel_text;
	private JTextField ogr_maas_text;
	private JTextField search_text;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherAddGui frame = new TeacherAddGui();
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
	public TeacherAddGui() throws SQLException {
		setResizable(false);

		ogretmenModel = new DefaultTableModel();
		Object[] colOgrName = new Object[4];
		colOgrName[0] = "ID";
		colOgrName[1] = "Ad Soyad";
		colOgrName[2] = "Tc No";
		colOgrName[3] = "Branþ";
		ogretmenModel.setColumnIdentifiers(colOgrName);
		ogretmenData = new Object[4];
		for (int i = 0; i < yonetici.getOgretmenList().size(); i++) {
			ogretmenData[0] = yonetici.getOgretmenList().get(i).getId();
			ogretmenData[1] = yonetici.getOgretmenList().get(i).getName();
			ogretmenData[2] = yonetici.getOgretmenList().get(i).getTcno();
			ogretmenData[3] = yonetici.getOgretmenList().get(i).getBrans();
			ogretmenModel.addRow(ogretmenData);

		}
		setTitle("Dershane Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 955, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

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
		btnExit.setBounds(822, 25, 80, 36);
		contentPane.add(btnExit);

		JLabel lblNewLabel_2 = new JLabel("*T.C. Numaran\u0131z :");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblNewLabel_2.setBounds(510, 148, 124, 31);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("*\u015Eifre :");
		lblNewLabel_2_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(510, 248, 124, 23);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("*Ad Soyad :");
		lblNewLabel_2_1_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblNewLabel_2_1_1.setBounds(510, 198, 124, 23);
		contentPane.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_1_2 = new JLabel("Tel No :");
		lblNewLabel_2_1_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblNewLabel_2_1_2.setBounds(510, 298, 124, 23);
		contentPane.add(lblNewLabel_2_1_2);

		JComboBox cmb_brans = new JComboBox();
		cmb_brans.setModel(new DefaultComboBoxModel(new String[] { "Matematik", "T\u00FCrkce", "Tarih", "Cografya",
				"Kimya", "Geometri", "Biyoloji", "Fizik", "Rehberlik", "Yabanci Dil", "Diger" }));
		cmb_brans.setBounds(640, 107, 199, 22);
		contentPane.add(cmb_brans);

		JLabel lblNewLabel_2_1_3 = new JLabel("E-Mail :");
		lblNewLabel_2_1_3.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblNewLabel_2_1_3.setBounds(510, 398, 124, 23);
		contentPane.add(lblNewLabel_2_1_3);

		JDateChooser sel_date = new JDateChooser();
		sel_date.setBounds(725, 72, 114, 20);
		contentPane.add(sel_date);

		JButton btn_add = new JButton(new ImageIcon(getClass().getResource("")));
		btn_add.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = "";
				try {
					date = sdf.format(sel_date.getDate());
				} catch (Exception e2) {
					// TODO: handle exception
				}

				if (ogr_tc_text.getText().length() == 0 || ogr_name_text.getText().length() == 0
						|| ogr_pass_text.getText().length() == 0 || date.length() == 0
						|| ogr_maas_text.getText().length() == 0) {
					helper.showMsg("fill");
				} else {
					try {
						String brans = cmb_brans.getSelectedItem().toString();
						int ücret = Integer.parseInt(ogr_maas_text.getText());
						boolean control = yonetici.addOgretmen(ogr_tc_text.getText(), ogr_name_text.getText(),
								ogr_pass_text.getText(), brans, ogr_tel_text.getText(), ogr_mail_text.getText(), ücret,
								date);
						if (control) {
							helper.showMsg("succsess");
							ogr_tc_text.setText(null);
							ogr_name_text.setText(null);
							ogr_pass_text.setText(null);
							ogr_tel_text.setText(null);
							ogr_mail_text.setText(null);
							ogr_maas_text.setText(null);
							updateOgretmenModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		btn_add.setBackground(new Color(224, 255, 255));
		btn_add.setText("Ekle");
		btn_add.setBounds(769, 508, 114, 35);
		contentPane.add(btn_add);

		ogr_tc_text = new JTextField();
		ogr_tc_text.setColumns(10);
		ogr_tc_text.setBounds(644, 153, 195, 23);
		contentPane.add(ogr_tc_text);

		ogr_name_text = new JTextField();
		ogr_name_text.setColumns(10);
		ogr_name_text.setBounds(644, 203, 195, 23);
		contentPane.add(ogr_name_text);

		ogr_pass_text = new JTextField();
		ogr_pass_text.setColumns(10);
		ogr_pass_text.setBounds(644, 253, 195, 23);
		contentPane.add(ogr_pass_text);

		ogr_tel_text = new JTextField();
		ogr_tel_text.setColumns(10);
		ogr_tel_text.setBounds(644, 303, 195, 23);
		contentPane.add(ogr_tel_text);

		ogr_mail_text = new JTextField();
		ogr_mail_text.setColumns(10);
		ogr_mail_text.setBounds(644, 403, 195, 23);
		contentPane.add(ogr_mail_text);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 110, 382, 418);
		contentPane.add(scrollPane);

		ogr_table = new JTable(ogretmenModel);
		ogr_table.setForeground(Color.BLACK);
		ogr_table.setBackground(Color.WHITE);
		scrollPane.setViewportView(ogr_table);
		ogr_table.getColumnModel().getColumn(0).setPreferredWidth(2);
		ogr_table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_sel_id.setText(ogr_table.getValueAt(ogr_table.getSelectedRow(), 0).toString());
				} catch (Exception ex) {

				}
			}
		});

		ogr_table.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selID = Integer.parseInt(ogr_table.getValueAt(ogr_table.getSelectedRow(), 0).toString());
					String selectname = ogr_table.getValueAt(ogr_table.getSelectedRow(), 1).toString();
					String selTc = ogr_table.getValueAt(ogr_table.getSelectedRow(), 2).toString();
					String selBrans = ogr_table.getValueAt(ogr_table.getSelectedRow(), 3).toString();
					try {
						boolean control = yonetici.updateOgretmen(selID, selTc, selectname, selBrans);
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

		fld_sel_id = new JTextField();
		fld_sel_id.setBounds(500, 515, 98, 23);
		contentPane.add(fld_sel_id);
		fld_sel_id.setColumns(10);

		JButton btn_del = new JButton("Sil");
		btn_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_sel_id.getText().length() == 0) {
					helper.showMsg("Lütfen Bir Öðretmen Seçiniz");
				} else {
					if (helper.askmsg("sure")) {
						int selectID = Integer.parseInt(fld_sel_id.getText());
						try {
							boolean control = yonetici.delOgretmen(selectID);
							if (control) {
								helper.showMsg("succsess");
								fld_sel_id.setText(null);
								updateOgretmenModel();
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
		btn_del.setText("Sil");
		btn_del.setBackground(new Color(224, 255, 255));
		btn_del.setBounds(631, 508, 114, 35);
		contentPane.add(btn_del);

		JLabel lblNewLabel_1 = new JLabel("Kullanici ID");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		lblNewLabel_1.setBounds(500, 500, 77, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lbl_brans = new JLabel("*Bran\u015F");
		lbl_brans.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lbl_brans.setBounds(510, 104, 124, 23);
		contentPane.add(lbl_brans);

		JLabel lblNewLabel_2_1_2_1 = new JLabel("2 .Tel No :");
		lblNewLabel_2_1_2_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblNewLabel_2_1_2_1.setBounds(510, 348, 124, 23);
		contentPane.add(lblNewLabel_2_1_2_1);

		ogr_veltel_text = new JTextField();
		ogr_veltel_text.setColumns(10);
		ogr_veltel_text.setBounds(644, 353, 195, 23);
		contentPane.add(ogr_veltel_text);

		JLabel lbl_baslik = new JLabel("\u00D6\u011Fretmen Y\u00F6netim Sistemi");
		lbl_baslik.setBackground(Color.BLACK);
		lbl_baslik.setForeground(Color.DARK_GRAY);
		lbl_baslik.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_baslik.setBounds(25, 11, 214, 31);
		contentPane.add(lbl_baslik);

		JLabel lbl_brans_1 = new JLabel("*Maa\u015F :");
		lbl_brans_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lbl_brans_1.setBounds(510, 448, 124, 23);
		contentPane.add(lbl_brans_1);

		ogr_maas_text = new JTextField();
		ogr_maas_text.setColumns(10);
		ogr_maas_text.setBounds(644, 451, 195, 23);
		contentPane.add(ogr_maas_text);

		JLabel lblNewLabel_2_2 = new JLabel("*Kay\u0131t Tarihi :");
		lblNewLabel_2_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblNewLabel_2_2.setBounds(592, 72, 104, 23);
		contentPane.add(lblNewLabel_2_2);

		search_text = new JTextField();
		search_text.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent e) {
				String keySearch = search_text.getText();
				TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<DefaultTableModel>(ogretmenModel);
				ogr_table.setRowSorter(tableRowSorter);
				tableRowSorter.setRowFilter(RowFilter.regexFilter(keySearch));
			}
		});
		search_text.setColumns(10);
		search_text.setBounds(95, 72, 237, 23);
		contentPane.add(search_text);

		JLabel search_lbl = new JLabel("Ara :");
		search_lbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		search_lbl.setBounds(29, 72, 56, 23);
		contentPane.add(search_lbl);

		JLabel lblNewLabel = new JLabel(new ImageIcon(getClass().getResource("bluee.jpeg")));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(0, 0, 941, 553);
		contentPane.add(lblNewLabel);

	}

	public void updateOgretmenModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) ogr_table.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < yonetici.getOgretmenList().size(); i++) {
			ogretmenData[0] = yonetici.getOgretmenList().get(i).getId();
			ogretmenData[1] = yonetici.getOgretmenList().get(i).getName();
			ogretmenData[2] = yonetici.getOgretmenList().get(i).getTcno();
			ogretmenData[3] = yonetici.getOgretmenList().get(i).getBrans();
			ogretmenModel.addRow(ogretmenData);
		}
	}
}
