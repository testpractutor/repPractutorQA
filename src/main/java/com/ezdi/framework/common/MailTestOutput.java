package com.ezdi.framework.common;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.ezdi.framework.driverscript.DriverTestNG;

public class MailTestOutput
{

	public static void sendEmailWithAttachments(String host, String port, final String userName, final String password, String toAddress, String subject, String message, File attachFiles) throws Exception
	{
		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", userName);
		properties.put("mail.password", password);

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator()
		{
			public PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(userName, password);
			}
		};
		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		//InternetAddress[] toAddresses = { new InternetAddress("jay.p@ezdi.us") };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		//msg.setRecipient(Message.RecipientType.CC, address);
		msg.setSubject(subject);
		msg.setSentDate(new Date());

		// creates message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(message, "text/html");

		// creates multi-part
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		/*
		 * // adds attachments if (attachFiles != null && attachFiles.length >
		 * 0) { for (String filePath : attachFiles) { MimeBodyPart attachPart =
		 * new MimeBodyPart();
		 * 
		 * try { attachPart.attachFile(filePath); } catch (IOException ex) {
		 * ex.printStackTrace(); }
		 * 
		 * multipart.addBodyPart(attachPart); } }
		 */
		// for add zip file in email
		MimeBodyPart attachPart = new MimeBodyPart();
		attachPart.attachFile(attachFiles);
		multipart.addBodyPart(attachPart);
		// sets the multi-part as e-mail's content
		msg.setContent(multipart);

		// sends the e-mail
		Transport.send(msg);

	}

	/**
	 * Test sending e-mail with attachments
	 */
	public static void sendMail() throws Exception
	{
		// SMTP info
		String host = "smtp.gmail.com";
		String port = "587";
		//String mailFrom = "ishan_chhotu@yahoo.com";
		//String password = "P@ssw0rd@123";

		String mailFrom = "ezdi.automation.report@gmail.com";
		String password = "P@ssw0rd@123";

		// message info
		//	String mailTo = "sagar.ezdi@gmail.com";
		String subject = "New email with attachment";
		String message = "Please find the automation status report in attachment";
		String strCCMail = "jay.p@ezid.us";

		// attachments
		// String[] attachFiles = new String[1];
		/*
		 * attachFiles[0] =
		 * "/home/likewise-open/EZDI-DOMAIN/skhalasi/Desktop/SeleniumTest/testOrigionalWebsite/test-output/index.html"
		 * ; attachFiles[1] =
		 * "/home/likewise-open/EZDI-DOMAIN/skhalasi/Pictures/images.jpg";
		 */
		//ZipFiles.zipp();
		File fis = new File("test-output/emailable-report.html");

		try
		{
			sendEmailWithAttachments(host, port, mailFrom, password, DriverTestNG.mailTo, subject, message, fis);
			Log4J.logp.info("Email sent.");
		}
		catch (Exception ex)
		{
			Log4J.logp.error("Could not send email.");
			ex.printStackTrace();
		}
	}
	/*
	 * @SuppressWarnings("finally") public boolean zipFiles(String srcFolder,
	 * String destZipFile) { boolean result = false; try {
	 * System.out.println("Program Start zipping the given files");
	 * 
	 * send to the zip procedure
	 * 
	 * zipFolder(srcFolder, destZipFile); result = true;
	 * System.out.println("Given files are successfully zipped"); } catch
	 * (Exception e) {
	 * System.out.println("Some Errors happned during the zip process"); }
	 * finally { return result; } }
	 * 
	 * private void zipFolder(String srcFolder, String destZipFile) throws
	 * Exception { ZipOutputStream zip = null; FileOutputStream fileWriter =
	 * null;
	 * 
	 * create the output stream to zip file result
	 * 
	 * fileWriter = new FileOutputStream(destZipFile); zip = new
	 * ZipOutputStream(fileWriter);
	 * 
	 * add the folder to the zip
	 * 
	 * addFolderToZip("", srcFolder, zip);
	 * 
	 * close the zip objects
	 * 
	 * zip.flush(); zip.close(); }
	 * 
	 * 
	 * recursively add files to the zip files
	 * 
	 * private void addFileToZip(String path, String srcFile, ZipOutputStream
	 * zip, boolean flag) throws Exception {
	 * 
	 * create the file object for inputs
	 * 
	 * File folder = new File(srcFile);
	 * 
	 * 
	 * if the folder is empty add empty folder to the Zip file
	 * 
	 * if (flag == true) { zip.putNextEntry(new ZipEntry(path + "/" +
	 * folder.getName() + "/")); } else { if the current name is directory,
	 * recursively traverse it to get the files
	 * 
	 * if (folder.isDirectory()) {
	 * 
	 * if folder is not empty
	 * 
	 * addFolderToZip(path, srcFile, zip); } else {
	 * 
	 * write the file to the output
	 * 
	 * byte[] buf = new byte[1024]; int len; FileInputStream in = new
	 * FileInputStream(srcFile); zip.putNextEntry(new ZipEntry(path + "/" +
	 * folder.getName())); while ((len = in.read(buf)) > 0) {
	 * 
	 * Write the Result
	 * 
	 * zip.write(buf, 0, len); } } } }
	 * 
	 * 
	 * add folder to the zip file
	 * 
	 * private void addFolderToZip(String path, String srcFolder,
	 * ZipOutputStream zip) throws Exception { File folder = new
	 * File(srcFolder);
	 * 
	 * 
	 * check the empty folder
	 * 
	 * if (folder.list().length == 0) { System.out.println(folder.getName());
	 * addFileToZip(path, srcFolder, zip, true); } else {
	 * 
	 * list the files in the folder
	 * 
	 * for (String fileName : folder.list()) { if (path.equals("")) {
	 * addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip, false); }
	 * else { addFileToZip(path + "/" + folder.getName(), srcFolder + "/" +
	 * fileName, zip, false); } } } }
	 */
}
