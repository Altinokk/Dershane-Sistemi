package View;

import java.awt.EventQueue;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import Model.Yonetici;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class TeacherListGui extends JFrame {

	private JPanel contentPane;
	Yonetici yonetici = new Yonetici();
	private DefaultTableModel teacherListModel;
	private Object[] teacherListData = null;
	private JTable teacherListTable;
	private JLabel lbl_welcome;
	private JLabel search_lbl;
	private JTextField search_text;
	private JLabel lblNewLabel;
	private JLabel toplam;
	private JTextField giden_text;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherListGui frame = new TeacherListGui();
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
	public TeacherListGui() throws SQLException {
		setResizable(false);

		teacherListModel = new DefaultTableModel();
		Object[] colStuName = new Object[7];
		colStuName[0] = "ID";
		colStuName[1] = "Tcno";
		colStuName[2] = "Ad Soyad";
		colStuName[3] = "Branþ";
		colStuName[4] = "Tarih";
		colStuName[5] = "Maaþ";
		colStuName[6] = "Telno";
		teacherListModel.setColumnIdentifiers(colStuName);
		teacherListData = new Object[7];
		for (int i = 0; i < yonetici.getOgretmenList().size(); i++) {
			teacherListData[0] = yonetici.getOgretmenList().get(i).getId();
			teacherListData[1] = yonetici.getOgretmenList().get(i).getTcno();
			teacherListData[2] = yonetici.getOgretmenList().get(i).getName();
			teacherListData[3] = yonetici.getOgretmenList().get(i).getBrans();
			teacherListData[4] = yonetici.getOgretmenList().get(i).getWdate();
			teacherListData[5] = yonetici.getOgretmenList().get(i).getÜcret();
			teacherListData[6] = yonetici.getOgretmenList().get(i).getTelno();
			teacherListModel.addRow(teacherListData);

		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 863, 561);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 105, 827, 348);
		contentPane.add(scrollPane);

		teacherListTable = new JTable(teacherListModel);
		teacherListTable.setForeground(Color.BLACK);
		teacherListTable.setBackground(Color.WHITE);
		scrollPane.setViewportView(teacherListTable);
		teacherListTable.getColumnModel().getColumn(0).setPreferredWidth(3);

		lbl_welcome = new JLabel("\u00D6\u011Fretmen Rapor Sistemi");
		lbl_welcome.setForeground(Color.DARK_GRAY);
		lbl_welcome.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_welcome.setBounds(10, 11, 193, 28);
		contentPane.add(lbl_welcome);

		search_lbl = new JLabel("Ara :");
		search_lbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		search_lbl.setBounds(10, 49, 56, 23);
		contentPane.add(search_lbl);

		search_text = new JTextField();
		search_text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String searchKey = search_text.getText();
				TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<DefaultTableModel>(
						teacherListModel);
				teacherListTable.setRowSorter(tableRowSorter);
				tableRowSorter.setRowFilter(RowFilter.regexFilter(searchKey));

			}
		});
		search_text.setColumns(10);
		search_text.setBounds(76, 52, 237, 23);
		contentPane.add(search_text);

		JButton btnExit = new JButton(new ImageIcon(getClass().getResource("exitt.png")));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MuhasebeMenuGui Mmenu;
				try {
					Mmenu = new MuhasebeMenuGui();
					Mmenu.setVisible(true);
					dispose();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnExit.setOpaque(false);
		btnExit.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btnExit.setBackground(new Color(224, 255, 255));
		btnExit.setBounds(757, 17, 80, 36);
		contentPane.add(btnExit);

		toplam = new JLabel("Giden Para :");
		toplam.setForeground(Color.BLACK);
		toplam.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		toplam.setBounds(519, 478, 85, 25);
		contentPane.add(toplam);

		giden_text = new JTextField();
		giden_text.setForeground(Color.BLACK);
		giden_text.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		giden_text.setColumns(10);
		giden_text.setBounds(603, 478, 89, 23);
		contentPane.add(giden_text);

		btnNewButton = new JButton("Hesapla");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				getSum();
			}
		});
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setBounds(735, 478, 89, 23);
		contentPane.add(btnNewButton);

		lblNewLabel = new JLabel(new ImageIcon(getClass().getResource("bluee.jpeg")));
		lblNewLabel.setBounds(0, 0, 847, 522);
		contentPane.add(lblNewLabel);
	}

	private void getSum() {

		int sum = 0;
		for (int i = 0; i < teacherListTable.getRowCount(); i++) {
			sum = sum + Integer.parseInt(teacherListTable.getValueAt(i, 5).toString());

		}
		giden_text.setText(Integer.toString(sum));
	}
}
