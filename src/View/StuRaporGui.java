package View;

import java.awt.EventQueue;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import Model.Muhasebe;
import Model.Yonetici;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.RowFilter;

public class StuRaporGui extends JFrame {
	stuPayGui gui = new stuPayGui();
	Yonetici yonetici = new Yonetici();
	Muhasebe muhasebe = new Muhasebe();
	private DefaultTableModel muhasebeModel = null;
	private Object[] muhasebeData = null;
	private JPanel contentPane;
	private JTable Muhasebetable;
	private JTextField search_text;
	private JTextField denee;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StuRaporGui frame = new StuRaporGui();
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

	public StuRaporGui() throws SQLException {
		setResizable(false);
		setTitle("Dershane Sistemi");

		muhasebeModel = new DefaultTableModel();
		Object[] colStuName = new Object[6];
		colStuName[0] = "ID";
		colStuName[1] = "Ad Soyad";
		colStuName[2] = "Tarih";
		colStuName[3] = "Ödenen Para";
		colStuName[4] = "Ödeme Tipi";
		colStuName[5] = "Kalan Borç";
		muhasebeModel.setColumnIdentifiers(colStuName);
		muhasebeData = new Object[6];
		for (int i = 0; i < muhasebe.getPayList().size(); i++) {
			muhasebeData[0] = muhasebe.getPayList().get(i).getId();
			muhasebeData[1] = muhasebe.getPayList().get(i).getStu_name();
			muhasebeData[2] = muhasebe.getPayList().get(i).getWdate();
			muhasebeData[3] = muhasebe.getPayList().get(i).getÖdenen();
			muhasebeData[4] = muhasebe.getPayList().get(i).getStatus();
			muhasebeData[5] = muhasebe.getPayList().get(i).getMoney();
			muhasebeModel.addRow(muhasebeData);

		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 863, 561);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 94, 793, 365);
		contentPane.add(scrollPane);

		Muhasebetable = new JTable(muhasebeModel);
		scrollPane.setViewportView(Muhasebetable);
		Muhasebetable.getColumnModel().getColumn(0).setPreferredWidth(2);
		Muhasebetable.getColumnModel().getColumn(3).setPreferredWidth(3);

		JLabel lbl_welcome = new JLabel("\u00D6\u011Frenci Rapor Sistemi");
		lbl_welcome.setForeground(Color.DARK_GRAY);
		lbl_welcome.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_welcome.setBounds(10, 11, 193, 36);
		contentPane.add(lbl_welcome);

		JButton btnExit = new JButton(new ImageIcon(getClass().getResource("exitt.png")));
		btnExit.setForeground(Color.BLACK);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MuhasebeMenuGui Mmenu;
				try {
					updateMuhasebeModel();
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
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(744, 11, 80, 36);
		contentPane.add(btnExit);

		JLabel search_lbl = new JLabel("Ara :");
		search_lbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		search_lbl.setBounds(28, 58, 56, 23);
		contentPane.add(search_lbl);

		search_text = new JTextField();
		search_text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String searchKey = search_text.getText();
				TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<DefaultTableModel>(muhasebeModel);
				Muhasebetable.setRowSorter(tableRowSorter);
				tableRowSorter.setRowFilter(RowFilter.regexFilter(searchKey));
			}
		});
		search_text.setColumns(10);
		search_text.setBounds(94, 61, 237, 23);
		contentPane.add(search_text);

		denee = new JTextField();
		denee.setForeground(Color.BLACK);
		denee.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		denee.setBounds(604, 481, 85, 23);
		contentPane.add(denee);
		denee.setColumns(10);

		JButton btnNewButton = new JButton("Hesapla");
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getSum();
			}
		});
		btnNewButton.setBounds(732, 480, 89, 23);
		contentPane.add(btnNewButton);
		JLabel toplam = new JLabel("Gelen Para :");
		toplam.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		toplam.setForeground(Color.BLACK);
		toplam.setBounds(519, 481, 89, 25);
		contentPane.add(toplam);

		JLabel lblNewLabel = new JLabel(new ImageIcon(getClass().getResource("bluee.jpeg")));
		lblNewLabel.setBounds(0, 0, 940, 551);
		contentPane.add(lblNewLabel);

	}

	private void getSum() {

		int sum = 0;
		for (int i = 0; i < Muhasebetable.getRowCount(); i++) {
			sum = sum + Integer.parseInt(Muhasebetable.getValueAt(i, 3).toString());

		}
		denee.setText(Integer.toString(sum));
	}

	public void updateMuhasebeModel() throws SQLException {
		DefaultTableModel temizModel = (DefaultTableModel) Muhasebetable.getModel();
		temizModel.setRowCount(0);
		muhasebeData = new Object[6];
		for (int i = 0; i < muhasebe.getPayList().size(); i++) {
			muhasebeData[0] = muhasebe.getPayList().get(i).getId();
			muhasebeData[1] = muhasebe.getPayList().get(i).getStu_name();
			muhasebeData[2] = muhasebe.getPayList().get(i).getWdate();
			muhasebeData[3] = muhasebe.getPayList().get(i).getÖdenen();
			muhasebeData[4] = muhasebe.getPayList().get(i).getStatus();
			muhasebeData[5] = muhasebe.getPayList().get(i).getMoney();
			muhasebeModel.addRow(muhasebeData);

		}
	}
}
