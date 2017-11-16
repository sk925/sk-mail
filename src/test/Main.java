package test;

import com.sk.mail.bean.MailMsg;
import com.sk.mail.conf.MailConf;
import com.sk.mail.service.MailManager;

public class Main {

	public static void main(String[] args) {
		MailMsg msg = new MailMsg();
		msg.setAuthCode("wvrtwxbexygubadd");
		msg.setcList(new String[]{"312124968@qq.com"});
		msg.setContent("helo");
		msg.setSender("1508434724@qq.com");
		msg.setSmptHost("smtp.qq.com");
		msg.setTitle("test");
		msg.setToList(new String[]{"3046769024@qq.com"});
		MailManager mg = new MailManager(new MailConf());
		mg.sendMSG(msg);
	}

}
