package com.sk.mail.service;

import java.io.UnsupportedEncodingException;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;

import com.sk.mail.bean.MailMsg;
import com.sk.mail.conf.MailConf;


public class MailManager {
	
	private MailConf conf;
	
	private Logger log = Logger.getLogger(MailManager.class);
	
	public MailManager(MailConf conf){
		if(conf == null){
			throw new NullPointerException("the conf is null");
		}
		this.conf = conf;
	}
	
	public void sendMSG(MailMsg msg){
		if(msg == null){
			throw new NullPointerException("invalid msg 【MailMsg.class】");
		}
		//创建会话
		Session session = Session.getInstance(conf.getProp());
		/* 构造邮件消息体 */
		MimeMessage message = new MimeMessage(session);
		
		addFiles();
		
		try {
			message.setFrom(new InternetAddress(msg.getSender()));
			//组装收件人列表
			message.setRecipients(Message.RecipientType.TO, getList(msg.getToList()));
			//组装抄送人列表
			if(msg.getcList() != null){
				message.setRecipients(Message.RecipientType.CC, getList(msg.getcList()));
			}
			message.setSubject(MimeUtility.encodeText(msg.getTitle(),"utf-8","B"));
			message.setContent(msg.getContent(), "text/html;charset=utf-8");
			
			/* 发送 */
			Transport transport = session.getTransport();
			transport.connect(msg.getSmptHost(), msg.getSender(), msg.getAuthCode());
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			log.info("--->>>>send success");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	private Address[] getList(String []list){
		Address []addresses = null;
		if(list != null && list.length > 0){
			addresses = new Address[list.length];
			for(int i=0;i<list.length;i++){
				String mail = list[i];
				try {
					addresses[i] = new InternetAddress(mail);
				} catch (AddressException e) {
					e.printStackTrace();
				};
			}
		}
		return addresses;
	}

    /**
     * 添加附件
     */
	private void addFiles() {
		
	}

}
