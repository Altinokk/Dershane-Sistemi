package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.crypto.Data;

import Helper.helper;
import Model.Yonetici;
import Model.grup;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.Icon;

public class GrupGui extends JFrame {

	private JPanel contentPane;
	private JTable grupTable;
	private DefaultTableModel grupModel = null;
	private Object[] grupData = null;
	private DefaultTableModel subeModel = null;
	private Object[] subeData = null;
	Yonetici yonetici = new Yonetici();
	grup g = new grup();
	private JTextField name_text;
	private JTable subeTable;
	private JTextField search_text;
	private JTextField ara_text;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GrupGui frame = new GrupGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public GrupGui() throws SQLException {
		setResizable(false);
		setTitle("Dershane Sistemi");
		
		grupModel = new DefaultTableModel();
		Object[] colOgrName = new Object[3];
		colOgrName[0] = "Ad Soyad";
		colOgrName[1] = "Tc No";
		colOgrName[2] = "Sinif";
		;
		grupModel.setColumnIdentifiers(colOgrName);
		grupData = new Object[3];
		for (int i = 0; i < yonetici.getStudentList().size(); i++) {
			grupData[0] = yonetici.getStudentList().get(i).getName();
			grupData[1] = yonetici.getStudentList().get(i).getTcno();
			grupData[2] = yonetici.getStudentList().get(i).getBrans();
			grupModel.setColumnIdentifiers(colOgrName);
			grupModel.addRow(grupData);

		}
		
		subeModel = new DefaultTableModel();
		Object[] colsinif = new Object[2];
		//colOgrName[0] = "Ad Soyad";
		colsinif[0] = "Tc No";
		colsinif[1] = "Sinif";
		subeModel.setColumnIdentifiers(colsinif);
		subeData = new Object[2];
		for (int i = 0; i < g.getgrupList().size(); i++) {
			subeData[0] = g.getgrupList().get(i).getName();
			subeData[1] = g.getgrupList().get(i).getSube();
			subeModel.setColumnIdentifiers(colsinif);
			subeModel.addRow(subeData);

		}
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 101, 309, 375);
		contentPane.add(scrollPane);
		
		grupTable = new JTable(grupModel);
		scrollPane.setViewportView(grupTable);
		
		grupTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				try {
					name_text.setText(grupTable.getValueAt(grupTable.getSelectedRow(), 0).toString());

				} catch (Exception ex) {
					// TODO: handle exception
				}
			}

		});
		
		
		JComboBox sinifBox = new JComboBox();
		sinifBox.setForeground(new Color(0, 0, 0));
		sinifBox.setBounds(456, 227, 68, 26);
		sinifBox.setBackground(new Color(220, 220, 220));
		sinifBox.setModel(new DefaultComboBoxModel(new String[] {"12", "11", "10", "9", "8", "7", "6", "5", "4"}));
		contentPane.add(sinifBox);
		
		JComboBox sinifsube = new JComboBox();
		sinifsube.setBounds(456, 315, 68, 26);
		sinifsube.setBackground(new Color(220, 220, 220));
		sinifsube.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "C", "D", "E", "F", "G", "H"}));
		contentPane.add(sinifsube);
		
		name_text = new JTextField();
		name_text.setBounds(378, 152, 146, 20);
		contentPane.add(name_text);
		name_text.setColumns(10);
		
		JButton add_btn = new JButton();
		add_btn.setText("Ekle");
		add_btn.setBackground(new Color(152, 251, 152));
		add_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sayi = sinifBox.getSelectedItem().toString();
				String harf = sinifsube.getSelectedItem().toString();
				
				if (sayi.length() == 0 || harf.length() == 0 || name_text.getText().length() == 0) {
					helper.showMsg("fill");

				} else {
					try {
						String sinif= sayi +"-"+ harf; 
						boolean control = g.addsinif(name_text.getText(),sinif);
						if (control) {
							helper.showMsg("succsess");	
							updateGrupModel();	
							updateSinifModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			
				
			}
		});
		add_btn.setBounds(399, 399, 102, 26);
		contentPane.add(add_btn);
		
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(564, 101, 294, 375);
		contentPane.add(scrollPane_1);
		
		subeTable = new JTable(subeModel);
		scrollPane_1.setViewportView(subeTable);
		
		search_text = new JTextField();
		search_text.setBounds(93, 59, 159, 23);
		contentPane.add(search_text);
		search_text.setColumns(10);
		search_text.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent e) {
				String searchKey= search_text.getText();
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(grupModel);
				grupTable.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter(searchKey));
			}
		});
		
		JLabel search_lbl = new JLabel("Ara :");
		search_lbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		search_lbl.setBounds(27, 59, 56, 23);
		contentPane.add(search_lbl);
		
		JLabel search_lbl_1 = new JLabel("Ara :");
		search_lbl_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		search_lbl_1.setBounds(594, 59, 56, 23);
		contentPane.add(search_lbl_1);
		
		ara_text = new JTextField();
		ara_text.setColumns(10);
		ara_text.setBounds(660, 59, 159, 23);
		contentPane.add(ara_text);		
		
		JLabel lblNewLabel_2_1_1 = new JLabel("*Ad Soyad :");
		lblNewLabel_2_1_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblNewLabel_2_1_1.setBounds(378, 118, 124, 23);
		contentPane.add(lblNewLabel_2_1_1);
		ara_text.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent e) {
				String searchKey= ara_text.getText();
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(subeModel);
				subeTable.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter(searchKey));
			}
		});
		
		JLabel lbl_baslik = new JLabel("Grupland\u0131rma Y\u00F6netim Sistemi");
		lbl_baslik.setForeground(Color.DARK_GRAY);
		lbl_baslik.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_baslik.setBackground(Color.BLACK);
		lbl_baslik.setBounds(23, 11, 214, 31);
		contentPane.add(lbl_baslik);
		
		
		
		JButton btnExit = new JButton(new ImageIcon(getClass().getResource("exitt.png")));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					grupMenuGui grupMG = new grupMenuGui();
					 grupMG.setVisible(true);
					 dispose();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnExit.setOpaque(false);
		btnExit.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btnExit.setBackground(new Color(224, 255, 255));
		btnExit.setBounds(798, 12, 80, 36);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel_2_1 = new JLabel("*Sinif :");
		lblNewLabel_2_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(383, 227, 73, 23);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("*\u015Eube :");
		lblNewLabel_2_1_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblNewLabel_2_1_2.setBounds(383, 314, 73, 23);
		contentPane.add(lblNewLabel_2_1_2);
		
		JLabel Backgraound = new JLabel(new ImageIcon(getClass().getResource("bluee.jpeg")));
		Backgraound.setBounds(0, 0, 888, 499);
		contentPane.add(Backgraound);
		
		
	}
	public void updateGrupModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) grupTable.getModel();
		clearModel.setRowCount(0);
		grupData = new Object[3];
		for (int i = 0; i < yonetici.getStudentList().size(); i++) {
			grupData[0] = yonetici.getStudentList().get(i).getName();
			grupData[1] = yonetici.getStudentList().get(i).getTcno();
			grupData[2] = yonetici.getStudentList().get(i).getBrans();
			grupModel.addRow(grupData);
		}
	}
	public void updateSinifModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel)subeTable.getModel();
		clearModel.setRowCount(0);
		subeData = new Object[2];
		for (int i = 0; i < g.getgrupList().size(); i++) {
			subeData[0] = g.getgrupList().get(i).getName();
			subeData[1] = g.getgrupList().get(i).getSube();
			subeModel.addRow(subeData);
		}
	}
}
