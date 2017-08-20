package com.plf.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class MailExample {
	private static String sendMail="";
	private static String password="";
	private static String receiveMail="";
	
	public static void main(String[] args) throws UnsupportedEncodingException, MessagingException{
		new MailExample().sendPicAndWordMail(receiveMail);
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
	
	public void sendPicAndWordMail(String target) throws UnsupportedEncodingException, MessagingException{
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
        message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(target, "Ŀ���û�", "UTF-8"));//�ռ�����
        message.setSubject("�����ʼ�");//����
        
        
        //����ͼƬ�ڵ�
        MimeBodyPart img=new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource("E:\\temp\\35.jpg")); // ��ȡ�����ļ�
        img.setDataHandler(dh);
        img.setContentID("img_id");
        
        //�����ı��ڵ�
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("����һ��ͼƬ<br/><img src='cid:img_id'/>", "text/html;charset=UTF-8");
        
        
        //���ı���ͼƬ�ڵ���
        MimeMultipart mm_text_image = new MimeMultipart();
        mm_text_image.addBodyPart(text);
        mm_text_image.addBodyPart(img);
        mm_text_image.setSubType("related");    // ������ϵ
        
        //�� �ı�+ͼƬ �Ļ�ϡ��ڵ㡱��װ��һ����ͨ���ڵ㡱   
        MimeBodyPart text_image = new MimeBodyPart();
        text_image.setContent(mm_text_image);

        //�����ı��ڵ�
        MimeBodyPart attachment = new MimeBodyPart();
        DataHandler dh2 = new DataHandler(new FileDataSource("E:\\����\\Java����.docx"));  // ��ȡ�����ļ�
        attachment.setDataHandler(dh2);                                     // ������������ӵ����ڵ㡱
        attachment.setFileName(MimeUtility.encodeText(dh2.getName()));
        
        
        // ���ã��ı�+ͼƬ���� ���� �Ĺ�ϵ���ϳ�һ����Ļ�ϡ��ڵ㡱 / Multipart ��
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text_image);
        mm.addBodyPart(attachment);     // ����ж�����������Դ������������
        mm.setSubType("mixed"); 
       
        //������������ӽ�ȥ
        message.setContent(mm);
        
        message.setSentDate(new Date());//����ʱ��
        message.saveChanges();//����
        //��ȡ�ʼ��������
        Transport transport = session.getTransport();
        transport.connect(sendMail, password);
        transport.sendMessage(message, message.getAllRecipients());//��ȡ���е��ռ�����
        transport.close();//�ر�����
	}
	
}
