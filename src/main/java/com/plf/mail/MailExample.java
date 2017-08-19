package com.plf.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailExample {
	private static String sendMail="";
	private static String password="";
	private static String receiveMail="";
	
	public static void main(String[] args) throws UnsupportedEncodingException, MessagingException{
		new MailExample().sendMail(receiveMail, "���ã���ֻ�ǲ���һ��");
	}
	public void sendMail(String target,String context) throws UnsupportedEncodingException, MessagingException{
		Properties props=new Properties();
		props.setProperty("mail.transport.protocol", "smtp");   // ʹ�õ�Э�飨JavaMail�淶Ҫ��
        props.setProperty("mail.smtp.host", "smtp.163.com");   // �����˵������ SMTP ��������ַ
        props.setProperty("mail.smtp.auth", "true");            // ��Ҫ������֤
        //��������
        Session session = Session.getDefaultInstance(props);
        //session.setDebug(true); //javax.mail-api��jar���ﲻ�ṩ���Եģ���Ҫjavax.mail�ģ�����maven repositoryû�ҵ�
        //�����ʼ��Ķ���
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendMail, "��Ҷ�滨", "UTF-8"));//��������
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(target, "Ŀ���û�", "UTF-8"));//�ռ�����
        message.setSubject("�����ʼ�");//����
        message.setContent(context,"text/html;charset=UTF-8");//����
        message.setSentDate(new Date());//����ʱ��
        message.saveChanges();//����
        //��ȡ�ʼ��������
        Transport transport = session.getTransport();
        transport.connect(sendMail, password);
        transport.sendMessage(message, message.getAllRecipients());//��ȡ���е��ռ�����
        transport.close();//�ر�����
	}
}
