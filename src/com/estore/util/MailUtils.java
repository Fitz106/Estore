package com.estore.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {

	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		// 1.创建一个程序与邮件服务器会话对象 Session

		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");// 指定验证为true
//		props.setProperty("mail.transport.protocol", "SMTP");
		props.setProperty("mail.host", "smtp.sina.com");
		
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");  
        props.setProperty("mail.smtp.socketFactory.fallback", "false");  
        props.setProperty("mail.smtp.port", "465");  
        props.setProperty("mail.smtp.socketFactory.port", "465");  
//		// 创建验证器
//		Authenticator auth = new Authenticator() {
//			public PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication("jiyuep", "hackersince1994");
//			}
//		};
		
        // 设置环境信息  
        // Session session = Session.getInstance(props);  
        Session session = Session.getDefaultInstance(props, new Authenticator() {  
            protected PasswordAuthentication getPasswordAuthentication() {  
                return new PasswordAuthentication("jiyuep", "hackersince1994");  
            }  
        }); 
		
        
     // 创建邮件对象  
        Message msg = new MimeMessage(session);  
        try {  
            msg.setSubject("用户激活");  
            // 设置邮件内容  
            msg.setContent(emailMsg, "text/html;charset=utf-8");  
            // 设置发件人  
            msg.setFrom(new InternetAddress("jiyuep@sina.com"));  
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));  
            Transport.send(msg);  
        } catch (MessagingException e) {  
            e.printStackTrace();  
        }  
        
//		try{
//		Session session = Session.getInstance(props, auth);
//
//		// 2.创建一个Message，它相当于是邮件内容
//		Message message = new MimeMessage(session);
//
//		message.setFrom(new InternetAddress("jiyuep@sina.com")); // 设置发送者
//
//		message.setRecipient(RecipientType.TO, new InternetAddress(email)); // 设置发送方式与接收者
//		
//		message.setSubject("用户激活");
//		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");
//
//		message.setContent(emailMsg, "text/html;charset=utf-8");
//
//		// 3.创建 Transport用于将邮件发送
////		System.out.println("ok3");
//		Transport.send(message);
////		System.out.println("ok7");
//		}
//		catch(Exception e){
//			e.printStackTrace();;
//		}
		
	}
}
