//package Model;
//
////import java.awt.BorderLayout;
////import java.awt.EventQueue;
//import java.io.File;
//import java.io.IOException;
//import java.util.Properties;
//
//import javax.mail.Message;
//import javax.mail.MessagingException;
////import javax.mail.NoSuchProviderException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//
//public class Mail {
//
//	Session newSession = null;
//	MimeMessage mimeMessage = null;
//
//	public static void main(String args[]) throws AddressException, MessagingException, IOException {
//		Mail mail = new Mail();
//		mail.setupServerProperties();
//		mail.draftEmail();
//		mail.sendEmail();
//
//	}
//
//	private void sendEmail() throws MessagingException {
//		String fromUser = "AltunokMehmett@gmail.com";
//		String fromUserPasword = "syso9697.";
//		String emailHost = "smtp.gmail.com";
//		Transport transport = newSession.getTransport("smtp");
//		transport.connect(emailHost, fromUser, fromUserPasword);
//		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
//		transport.close();
//		// System.out.println("email successfully sent");
//	}
//
//	private MimeMessage draftEmail() throws AddressException, MessagingException, IOException {
//		String[] emailReceipients = { "mehmet253245@gmail.com" };
//		String emailSubject = "test mail";
//		String emailBady = "Test Body of my mail";
//		mimeMessage = new MimeMessage(newSession);
//		for (int i = 0; i < emailReceipients.length; i++) {
//			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceipients[i]));
//		}
//		mimeMessage.setSubject(emailSubject);
//
//		MimeBodyPart bodyPart = new MimeBodyPart();
//		bodyPart.setContent(emailBady, "html/text");
//		bodyPart.setText(emailBady);
//
//		MimeBodyPart attachmentBodyPart = new MimeBodyPart();
//		attachmentBodyPart.attachFile(new File("/C:/Users/Dummy/Desktop/pexels.jpeg"));
//		MimeMultipart multipart = new MimeMultipart();
//		multipart.addBodyPart(bodyPart);
//		multipart.addBodyPart(attachmentBodyPart);
//		mimeMessage.setContent(multipart);
//		return mimeMessage;
//	}
//
//	private void setupServerProperties() {
//		Properties properties = System.getProperties();
//		properties.put("mail.smtp.port", "587");
//		properties.put("mail.smtp.auth", "true");
//		properties.put("mail.smtp.starttls.enable", "true");
//		newSession = Session.getDefaultInstance(properties, null);
//
//	}
//
//}
