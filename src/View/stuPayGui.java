package View;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import Model.Muhasebe;
import Model.Yonetici;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import Helper.helper;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Label;

public class stuPayGui extends JFrame {
	Yonetici yonetici = new Yonetici();
	Muhasebe muhasebe = new Muhasebe();
	private JPanel contentPane;
	private JTextField ödenen_text;
	private JTextField sel_name;
	private JTextField sel_id;
	private DefaultTableModel studentModel = null;
	private Object[] studentData = null;
	JTable muh_sdu_table;
	private JTextField sel_ücret;
	private JTextField textsearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stuPayGui frame = new stuPayGui();
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
	public stuPayGui() throws SQLException {
		setResizable(false);
		setTitle("Dershane Sistemi");

		studentModel = new DefaultTableModel();
		Object[] colOgrName = new Object[5];

		colOgrName[0] = "ID";
		colOgrName[1] = "Ad Soyad";
		colOgrName[2] = "Tc No";
		colOgrName[3] = "Sýnýf";
		colOgrName[4] = "Ücret";
		studentModel.setColumnIdentifiers(colOgrName);

		studentData = new Object[5];

		for (int i = 0; i < yonetici.getStudentList().size(); i++) {
			studentData[0] = yonetici.getStudentList().get(i).getId();
			studentData[1] = yonetici.getStudentList().get(i).getName();
			studentData[2] = yonetici.getStudentList().get(i).getTcno();
			studentData[3] = yonetici.getStudentList().get(i).getBrans();
			studentData[4] = yonetici.getStudentList().get(i).getÜcret();
			studentModel.addRow(studentData);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 955, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_2_1_1 = new JLabel("Kullan\u0131c\u0131 ID :");
		lblNewLabel_2_1_1.setBounds(570, 140, 124, 31);
		contentPane.add(lblNewLabel_2_1_1);
		lblNewLabel_2_1_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));

		JLabel lblNewLabel_2_1 = new JLabel("Ad Soyad :");
		lblNewLabel_2_1.setBounds(570, 210, 124, 31);
		contentPane.add(lblNewLabel_2_1);
		lblNewLabel_2_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));

		sel_id = new JTextField();
		sel_id.setEditable(false);
		sel_id.setBounds(718, 147, 195, 23);
		contentPane.add(sel_id);
		sel_id.setColumns(10);

		sel_name = new JTextField();
		sel_name.setEditable(false);
		sel_name.setBounds(718, 217, 195, 23);
		contentPane.add(sel_name);
		sel_name.setColumns(10);

		JLabel lblNewLabel_2_2 = new JLabel("Kalan Tutar :");
		lblNewLabel_2_2.setBounds(570, 281, 124, 31);
		contentPane.add(lblNewLabel_2_2);
		lblNewLabel_2_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));

		sel_ücret = new JTextField();
		sel_ücret.setEditable(false);
		sel_ücret.setBounds(718, 289, 195, 20);
		contentPane.add(sel_ücret);
		sel_ücret.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("\u00D6denecek Tutar :");
		lblNewLabel_2.setBounds(570, 356, 124, 31);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));

		ödenen_text = new JTextField();
		ödenen_text.setBounds(718, 363, 195, 23);
		contentPane.add(ödenen_text);
		ödenen_text.setColumns(10);

		JDateChooser sel_date = new JDateChooser();
		sel_date.setBounds(799, 95, 114, 20);
		contentPane.add(sel_date);

		JComboBox status_combo = new JComboBox();
		status_combo.setBounds(802, 421, 111, 22);
		contentPane.add(status_combo);
		status_combo.setModel(new DefaultComboBoxModel(new String[] { "Kredi Kart", "Nakit" }));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 121, 444, 395);
		contentPane.add(scrollPane);

		muh_sdu_table = new JTable(studentModel) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		muh_sdu_table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(muh_sdu_table);

		JButton btn_add = new JButton((Icon) null);
		btn_add.setBounds(658, 478, 114, 35);
		contentPane.add(btn_add);
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = "";
				try {
					date = sdf.format(sel_date.getDate());
				} catch (Exception e2) {
					// TODO: handle exception
				}

				if (sel_id.getText().length() == 0 || sel_name.getText().length() == 0 || date.length() == 0
						|| ödenen_text.getText().length() == 0) {
					helper.showMsg("fill");
				} else {
					try {

						int ödenen = Integer.parseInt(ödenen_text.getText());
						int borc = Integer.parseInt(sel_ücret.getText());
						int money = (borc) - (ödenen);
						int id = Integer.parseInt(sel_id.getText());
						String status = status_combo.getSelectedItem().toString();
						boolean control = muhasebe.addPayStudent(id, sel_name.getText(), date, ödenen, status, money);
						if (control) {
							helper.showMsg("succsess");
							sel_id.setText(null);
							sel_name.setText(null);
							ödenen_text.setText(null);
							sel_ücret.setText(null);
							yonetici.upStudent(id, money);
							updateStudentModel();

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_add.setText("\u00D6de");
		btn_add.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btn_add.setBackground(new Color(224, 255, 255));

		JButton btnExit = new JButton(new ImageIcon(getClass().getResource("exitt.png")));
		btnExit.setForeground(Color.BLACK);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MuhasebeMenuGui Mmenu;
				Mmenu = new MuhasebeMenuGui();
				Mmenu.setVisible(true);

				dispose();

			}
		});
		btnExit.setOpaque(false);
		btnExit.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(849, 22, 80, 36);
		contentPane.add(btnExit);

		JLabel lbl_welcome = new JLabel(" \u00D6\u011Frenci \u00D6deme Sistemi");
		lbl_welcome.setForeground(Color.DARK_GRAY);
		lbl_welcome.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_welcome.setBounds(24, 11, 195, 31);
		contentPane.add(lbl_welcome);

		textsearch = new JTextField();
		textsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String searchKey = textsearch.getText();
				TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<DefaultTableModel>(studentModel);
				muh_sdu_table.setRowSorter(tableRowSorter);
				tableRowSorter.setRowFilter(RowFilter.regexFilter(searchKey));
			}
		});
		textsearch.setBounds(92, 87, 195, 23);
		contentPane.add(textsearch);
		textsearch.setColumns(10);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("Ara :");
		lblNewLabel_2_1_1_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblNewLabel_2_1_1_1.setBounds(26, 84, 61, 23);
		contentPane.add(lblNewLabel_2_1_1_1);

		JLabel lblNewLabel_2_1_1_2 = new JLabel("*\u00D6deme Tarihi :");
		lblNewLabel_2_1_1_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblNewLabel_2_1_1_2.setBounds(658, 95, 124, 23);
		contentPane.add(lblNewLabel_2_1_1_2);

		JLabel lblNewLabel = new JLabel(new ImageIcon(getClass().getResource("bluee.jpeg")));
		lblNewLabel.setBounds(0, 0, 940, 551);
		contentPane.add(lblNewLabel);

		muh_sdu_table.getColumnModel().getColumn(0).setPreferredWidth(2);
		muh_sdu_table.getColumnModel().getColumn(3).setPreferredWidth(2);
		muh_sdu_table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					sel_id.setText(muh_sdu_table.getValueAt(muh_sdu_table.getSelectedRow(), 0).toString());
					sel_name.setText(muh_sdu_table.getValueAt(muh_sdu_table.getSelectedRow(), 1).toString());
					sel_ücret.setText(muh_sdu_table.getValueAt(muh_sdu_table.getSelectedRow(), 4).toString());

				} catch (Exception ex) {

				}
			}
		});

	}

	public void updateStudentModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) muh_sdu_table.getModel();
		clearModel.setRowCount(0);
		studentData = new Object[6];
		for (int i = 0; i < yonetici.getStudentList().size(); i++) {
			studentData[0] = yonetici.getStudentList().get(i).getId();
			studentData[1] = yonetici.getStudentList().get(i).getName();
			studentData[2] = yonetici.getStudentList().get(i).getTcno();
			studentData[3] = yonetici.getStudentList().get(i).getBrans();
			studentData[4] = yonetici.getStudentList().get(i).getÜcret();
			studentModel.addRow(studentData);
		}
	}
}
