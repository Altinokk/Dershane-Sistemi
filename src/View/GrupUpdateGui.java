package View;

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

import Model.grup;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JTable;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GrupUpdateGui extends JFrame {

	private JPanel contentPane;
	private JTextField text_search;
	private JTable grupUpdateTable;
	private DefaultTableModel subeModel = null;
	private Object[] subeData = null;
	grup g = new grup();
	private JTextField textField;
	private JLabel search_lbl_1;
	private JLabel lbl_welcome;
	private JButton del_btn;
	private JTextField name_text;
	private JLabel search_lbl_2;
	private JButton btnExit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GrupUpdateGui frame = new GrupUpdateGui();
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
	public GrupUpdateGui() throws SQLException {
		setResizable(false);

		subeModel = new DefaultTableModel();
		Object[] colsinif = new Object[3];
		colsinif[0] = "id";
		colsinif[1] = "Name";
		colsinif[2] = "Sinif";
		subeModel.setColumnIdentifiers(colsinif);
		subeData = new Object[3];
		for (int i = 0; i < g.getsinifList().size(); i++) {
			subeData[0] = g.getsinifList().get(i).getId();
			subeData[1] = g.getsinifList().get(i).getName();
			subeData[2] = g.getsinifList().get(i).getSube();
			subeModel.setColumnIdentifiers(colsinif);
			subeModel.addRow(subeData);

		}

		setTitle("Dershane Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		text_search = new JTextField();
		text_search.setBackground(new Color(255, 255, 255));
		text_search.setColumns(10);
		text_search.setBounds(146, 65, 159, 23);
		contentPane.add(text_search);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 101, 376, 367);
		contentPane.add(scrollPane);

		grupUpdateTable = new JTable(subeModel);
		scrollPane.setViewportView(grupUpdateTable);
		text_search.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent e) {
				String searchKey = text_search.getText();
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(subeModel);
				grupUpdateTable.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter(searchKey));
			}
		});

		grupUpdateTable.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {

					String selname = grupUpdateTable.getValueAt(grupUpdateTable.getSelectedRow(), 0).toString();
					String selsinif = grupUpdateTable.getValueAt(grupUpdateTable.getSelectedRow(), 1).toString();

					try {
						boolean control = g.updateSinif(selsinif, selname);
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
		grupUpdateTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					name_text.setText(grupUpdateTable.getValueAt(grupUpdateTable.getSelectedRow(), 0).toString());
				} catch (Exception ex) {

				}
			}
		});

		JLabel search_lbl = new JLabel("Ara :");
		search_lbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		search_lbl.setBounds(57, 62, 56, 23);
		contentPane.add(search_lbl);

		textField = new JTextField();
		textField.setBounds(558, 186, 64, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton();
		btnNewButton.setBackground(new Color(250, 128, 114));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setText("Bul");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCount();
			}
		});

		btnNewButton.setBounds(503, 238, 89, 30);
		contentPane.add(btnNewButton);

		search_lbl_1 = new JLabel("S\u0131n\u0131f Mevcudu :");
		search_lbl_1.setForeground(Color.BLACK);
		search_lbl_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		search_lbl_1.setBounds(437, 183, 110, 23);
		contentPane.add(search_lbl_1);

		lbl_welcome = new JLabel("Grup G\u00FCncelleme ve Silme");
		lbl_welcome.setForeground(Color.BLACK);
		lbl_welcome.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lbl_welcome.setBounds(25, 11, 193, 36);
		contentPane.add(lbl_welcome);

		del_btn = new JButton(new ImageIcon(getClass().getResource("del.png")));
		del_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (name_text.getText().length() == 0) {
					helper.showMsg("Lütfen Bir Öðrenci Seçiniz");
				} else {
					if (helper.askmsg("sure")) {
						int selectID = Integer.parseInt(name_text.getText());
						try {
							boolean control = g.delStudent(selectID);
							if (control) {
								helper.showMsg("succsess");
								name_text.setText(null);
								updateSinifModel();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}

			}
		});
		del_btn.setBounds(492, 376, 89, 36);
		del_btn.setOpaque(false);
		contentPane.add(del_btn);

		name_text = new JTextField();
		name_text.setBounds(566, 306, 56, 20);
		contentPane.add(name_text);
		name_text.setColumns(10);

		search_lbl_2 = new JLabel("Se\u00E7ilen ID :");
		search_lbl_2.setForeground(Color.BLACK);
		search_lbl_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		search_lbl_2.setBounds(447, 303, 92, 23);
		contentPane.add(search_lbl_2);

		btnExit = new JButton(new ImageIcon(getClass().getResource("exitt.png")));
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
		btnExit.setBounds(583, 21, 80, 36);
		contentPane.add(btnExit);

		JLabel Backgraound = new JLabel(new ImageIcon(getClass().getResource("bluee.jpeg")));
		Backgraound.setBounds(0, 0, 681, 507);
		contentPane.add(Backgraound);

	}

	private void getCount() {
		textField.setText(" " + grupUpdateTable.getRowCount());
	}

	public void updateSinifModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) grupUpdateTable.getModel();
		clearModel.setRowCount(0);
		subeData = new Object[3];
		for (int i = 0; i < g.getsinifList().size(); i++) {
			subeData[0] = g.getsinifList().get(i).getId();
			subeData[1] = g.getsinifList().get(i).getName();
			subeData[2] = g.getsinifList().get(i).getSube();
			subeModel.addRow(subeData);
		}
	}
}
