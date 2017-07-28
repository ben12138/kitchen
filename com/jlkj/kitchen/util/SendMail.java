package com.jlkj.kitchen.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.Security;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.StringTokenizer;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import sun.misc.BASE64Encoder;

public class SendMail {
	private String server_mail = "smtp.163.com";
	private  String subject = "验证码，请在5分钟内验证，过期无效";
	private String content;
	private static final String password = "br2br2";
	private static final BASE64Encoder encode = new BASE64Encoder();
	private String myEmailAccount = "13151567657@163.com";
	private boolean isSuccess = true;
	public SendMail() {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for(int i = 0;i < 4;i++){
			sb.append(base.charAt(random.nextInt(base.length())));
		}
		content = new String(sb);
	}
	public void setSubject(String subject){
		this.subject = subject;
	}
	public String getContent(){
		return this.content;
	}
	public void setContent(String content){
		this.content = content;
	}
	public boolean send(String targetMail) throws Exception{
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		// Get a Properties object
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", server_mail);
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		final String username = "13151567657@163.com";
		final String password = "br2br2";
		Session session = Session.getDefaultInstance(props,
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		session.setDebug(true);
		// -- Create a new message --
		Message msg = new MimeMessage(session);

		// -- Set the FROM and TO fields --
		msg.setFrom(new InternetAddress(username));
		msg.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(targetMail, false));
		msg.setSubject(subject);
		msg.setText(content);
		msg.setSentDate(new Date());
		Transport.send(msg);

		return true;
		
	}
	public MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "尊敬的用户", "UTF-8"));
        message.setSubject(subject, "UTF-8");
        message.setContent(content, "text/html;charset=UTF-8");
        message.setSentDate(new java.util.Date());
        message.saveChanges();
        return message;
	}
}
