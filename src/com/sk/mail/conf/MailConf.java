package com.sk.mail.conf;

import java.security.GeneralSecurityException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * 默认使用QQ邮箱发送
 * @author DELL
 *
 */
public class MailConf {
	
	private Logger log = Logger.getLogger(MailConf.class);
	
	private Properties prop;
	
	public MailConf (Properties prop){
		setProp(prop);
	}
	
	/**
	 * 使用默认配置
	 */
	public MailConf(){
		initConf();
	}
	
	private void initConf(){
		prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		MailSSLSocketFactory sf = null;
		try {
			sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			prop.put("mail.smtp.ssl.enable", "true");
			prop.put("mail.smtp.ssl.socketFactory", sf);
		} catch (GeneralSecurityException e) {
			log.error("--->>>>开启SSL加密异常:" + e.getMessage());
		}
	}
	
	public void setMailSSLSocketFactory(MailSSLSocketFactory sf){
		prop.put("mail.smtp.ssl.socketFactory", sf);
	}
	
	public void setProp(Properties prop){
		if(prop == null){
			log.warn("--->>>>the prop is null,using default config");
			initConf();
		}else{
			this.prop = prop;
		}
	}
	
	public Properties getProp(){
		return prop;
	}

}
