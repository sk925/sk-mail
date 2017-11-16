package com.sk.mail.bean;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class MailMsg {
	
	private final Logger log = Logger.getLogger(MailMsg.class);
	
	private final String emailAddressPattern = "\\b(^['_A-Za-z0-9-]+(\\.['_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
	
	
	private String title;
	
	private String content;
	
	private String sender;
	
	private String authCode;
	
	private String [] toList;
	
	private String []cList;
	
	private String smptHost;
	
	public MailMsg(String title, String content, String sender, String authCode, String[] toList, String []cList, String smptHost) {
		super();
		this.title = title;
		this.content = content;
		setSender(sender);
		this.authCode = authCode;
		setToList(toList);
		setcList(cList);
		this.smptHost = smptHost;
	}
	
	public MailMsg(String sender, String authCode, String[] toList, String[] cList, String smptHost) {
		this(null,null,sender,authCode,toList,cList,smptHost);
	}


	public MailMsg(){}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		if(!isEmail(sender)){
			throw new RuntimeException("--->>>invalid sender:" + sender);
		}
		this.sender = sender;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String[] getToList() {
		return toList;
	}

	public void setToList(String[] toList) {
		this.toList = new String[toList.length];
		for(int i = 0;i<toList.length;i++){
			String to = toList[i];
			if(!isEmail(to)){
				log.warn("--->>>>invalid receiver:" + to);
				continue;
			}
			this.toList[i] = to;
		}
		
	}

	public String[] getcList() {
		return cList;
	}

	public void setcList(String[] cList) {
		this.cList = new String[cList.length];
		for(int i = 0;i<cList.length;i++){
			String c = cList[i];
			if(!isEmail(c)){
				log.warn("--->>>>invalid receiver:" + c);
				continue;
			}
			this.cList[i] = c;
		}
	}
	
	
	
	public String getSmptHost() {
		return smptHost;
	}

	public void setSmptHost(String smptHost) {
		this.smptHost = smptHost;
	}

	private boolean isEmail(String email) {
		return Pattern.matches(emailAddressPattern,email);
		//"\\b(^['_A-Za-z0-9-]+(\\.['_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b",
	}
	
	

}
