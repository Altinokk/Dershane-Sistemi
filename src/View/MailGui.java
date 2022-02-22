package View;

//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Helper.helper;
import Model.Yonetici;

import javax.swing.JTextField;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
//import java.awt.event.KeyListener;
//import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
//import javax.swing.SwingConstants;
import javax.swing.JTextArea;
//import javax.swing.JTextPane;
import javax.swing.RowFilter;
//import javax.swing.Icon;
import java.awt.Color;

public class MailGui extends JFrame {
	Yonetici yonetici = new Yonetici();
	private JPanel contentPane;
	private JTextField text_Email;
	Session newSession = null;
	MimeMessage mimeMessage = null;
	private JTextField text_baslik;
	private JLabel lblNewLabel_3;
	private JTable table_mail;
	private JLabel lbl_mail_text;
	private JLabel lbl_mail_text_1;
	private JLabel lbl_mail_text_2;
	private DefaultTableModel studentModel = null;
	private Object[] studentData = null;
	private JTextArea text_icerik;
	private JButton btnExit;
	private JLabel search_lbl;
	private JTextField search_text;
	private JLabel lblNewLabel;
	private JTextArea text_file;

	/**
	 * Launch the application.
	 * 
	 * @throws IOException
	 * @throws MessagingException
	 * @throws AddressException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws AddressException, MessagingException, IOException, SQLException {
		MailGui mail = new MailGui();
		mail.setupServerProperties();
		mail.draftEmail();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MailGui frame = new MailGui();
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
	public MailGui() throws SQLException {
		setResizable(false);

		studentModel = new DefaultTableModel();
		Object[] colOgrName = new Object[4];
		colOgrName[0] = "ID";
		colOgrName[1] = "Ad Soyad";
		colOgrName[2] = "Tc No";
		colOgrName[3] = "Mail";
		studentModel.setColumnIdentifiers(colOgrName);
		studentData = new Object[4];
		for (int i = 0; i < yonetici.getStudentList().size(); i++) {
			studentData[0] = yonetici.getStudentList().get(i).getId();
			studentData[1] = yonetici.getStudentList().get(i).getName();
			studentData[2] = yonetici.getStudentList().get(i).getTcno();
			studentData[3] = yonetici.getStudentList().get(i).getEmail();
			studentModel.addRow(studentData);

		}

		setTitle("Dershane Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 869, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		text_Email = new JTextField();
		text_Email.setBounds(615, 100, 196, 24);

		contentPane.add(text_Email);

		text_Email.setColumns(10);

		JButton btn_gonder = new JButton("G\u00F6nder");
		btn_gonder.setForeground(new Color(0, 0, 0));
		btn_gonder.setBackground(new Color(173, 255, 47));
		btn_gonder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text_Email.getText().length() == 0 || text_baslik.getText().length() == 0
						|| text_icerik.getText().length() == 0) {
					helper.showMsg("fill");
				} else {
					try {
						setupServerProperties();
						draftEmail();
						sendEmail();
						helper.showMsg("Mail basarili bir sekilde gonderildi");
						text_baslik.setText("");
						text_Email.setText("");
						text_icerik.setText("");
						// updateStudentModel();
					} catch (MessagingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btn_gonder.setBounds(650, 440, 114, 23);
		contentPane.add(btn_gonder);

		text_baslik = new JTextField();
		text_baslik.setBounds(615, 164, 196, 24);
		contentPane.add(text_baslik);
		text_baslik.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 96, 420, 366);
		contentPane.add(scrollPane);

		table_mail = new JTable(studentModel);
		table_mail.setForeground(Color.BLACK);
		table_mail.setBackground(Color.WHITE);
		scrollPane.setViewportView(table_mail);
		table_mail.getColumnModel().getColumn(0).setPreferredWidth(2);

		table_mail.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					text_Email.setText(table_mail.getValueAt(table_mail.getSelectedRow(), 3).toString());
				} catch (Exception ex) {

				}
			}
		});

		lbl_mail_text = new JLabel("E-Mail Metni :");
		lbl_mail_text.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lbl_mail_text.setBounds(481, 234, 124, 23);
		contentPane.add(lbl_mail_text);

		lbl_mail_text_1 = new JLabel("E-Mail Basligi :");
		lbl_mail_text_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lbl_mail_text_1.setBounds(481, 160, 124, 23);
		contentPane.add(lbl_mail_text_1);

		lbl_mail_text_2 = new JLabel("E-Mail Adresi :");
		lbl_mail_text_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lbl_mail_text_2.setBounds(481, 96, 124, 23);
		contentPane.add(lbl_mail_text_2);

		btnExit = new JButton(new ImageIcon(getClass().getResource("exitt.png")));
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
		btnExit.setBounds(762, 23, 80, 36);
		contentPane.add(btnExit);

		search_lbl = new JLabel("Ara :");
		search_lbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		search_lbl.setBounds(22, 54, 56, 23);
		contentPane.add(search_lbl);

		search_text = new JTextField();
		search_text.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String searchKey = search_text.getText();
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(studentModel);
				table_mail.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter(searchKey));
			}
		});

		search_text.setBounds(88, 53, 172, 24);
		contentPane.add(search_text);
		search_text.setColumns(10);

		text_icerik = new JTextArea();
		text_icerik.setBounds(615, 236, 196, 116);
		contentPane.add(text_icerik);

		lblNewLabel = new JLabel("Mail G\u00F6nderme Otomasyonu");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setBackground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel.setBounds(20, 11, 228, 24);
		contentPane.add(lblNewLabel);

		JLabel lbl_file = new JLabel("E-Mail Dosya :");
		lbl_file.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lbl_file.setBounds(481, 379, 124, 23);
		contentPane.add(lbl_file);

		JButton btnAra = new JButton(new ImageIcon(getClass().getResource("add.png")));
		btnAra.setForeground(new Color(0, 0, 0));
		btnAra.setBackground(new Color(230, 230, 250));
		btnAra.setOpaque(false);
		btnAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser fc = new JFileChooser();
					fc.showOpenDialog(btnAra);

					File file = fc.getSelectedFile();
					String filePath = file.getAbsolutePath();
					text_file.setText(filePath);
					// String filePath = file.getPath();

					// BufferedReader br = new BufferedReader(new FileReader(filePath));
					// String s="";
					// String mydata="";
					// while((s=br.readLine())!=null){
					// mydata=mydata+s+"\n";
					// }

					// text_file.setText(mydata);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnAra.setBounds(793, 415, 41, 33);
		contentPane.add(btnAra);

		text_file = new JTextArea();
		text_file.setBounds(616, 381, 196, 23);
		contentPane.add(text_file);

		lblNewLabel_3 = new JLabel(new ImageIcon(getClass().getResource("bluee.jpeg")));
		lblNewLabel_3.setBounds(0, 0, 863, 501);
		contentPane.add(lblNewLabel_3);

	}

	private void sendEmail() throws MessagingException {
		String fromUser = "AltunokMehmett@gmail.com";
		String fromUserPasword = "syso9697.";
		String emailHost = "smtp.gmail.com";
		Transport transport = newSession.getTransport("smtp");
		transport.connect(emailHost, fromUser, fromUserPasword);
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		transport.close();
		System.out.println("email successfully sent");
	}

	private MimeMessage draftEmail() throws AddressException, MessagingException, IOException {

		String emailReceipients = text_Email.getText();
		String emailSubject = text_baslik.getText();
		String emailBady = text_icerik.getText();
		String emailfile = text_file.getText();
		mimeMessage = new MimeMessage(newSession);
		for (int i = 0; i < emailReceipients.length(); i++) {

			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceipients));
			// mimeMessage.setRecipient(Message.RecipientType.TO, new
			// InternetAddress(emailReceipients));
			// mimeMessage.setRecipient(Message.RecipientType.TO, new
			// InternetAddress(""+table_mail.getValueAt(i, 3)));

		}

		mimeMessage.setSubject(emailSubject);

		MimeBodyPart bodyPart = new MimeBodyPart();
		// bodyPart.setContent(emailBady, "html/text");
		bodyPart.setText(emailBady);

		MimeBodyPart attachmentBodyPart = new MimeBodyPart();
		// attachmentBodyPart.setText(emailfile);
		attachmentBodyPart.attachFile(new File(emailfile));
		MimeMultipart multipart = new MimeMultipart();
		multipart.addBodyPart(bodyPart);
		multipart.addBodyPart(attachmentBodyPart);
		mimeMessage.setContent(multipart);

		return mimeMessage;
	}

	private void setupServerProperties() {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		newSession = Session.getDefaultInstance(properties, null);

	}

	public void updateStudentModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_mail.getModel();
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
