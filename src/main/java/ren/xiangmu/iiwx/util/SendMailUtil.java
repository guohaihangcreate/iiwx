package ren.xiangmu.iiwx.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class SendMailUtil {
	
	@Autowired
	private Environment env;

	private String host = ""; // smtp������
	private String from = ""; // �����˵�ַ
	private String to = ""; // �ռ��˵�ַ
	private String affix = ""; // ������ַ
	private String affixName = ""; // ��������s
	private String user = ""; // �û���
	private String pwd = ""; // ����
	private String subject = ""; // �ʼ�����

	private static SendMailUtil instance = null;

	public void setAddress(String from, String to, String subject) {
		this.from = from;
		this.to = to;
		this.subject = subject;
	}

	public void setAffix(String affix, String affixName) {
		this.affix = affix;
		this.affixName = affixName;
	}

	// ��Ӷ������
	public void addTach(String fileList[], Multipart multipart)
			throws MessagingException, UnsupportedEncodingException {
		for (int index = 0; index < fileList.length; index++) {
			MimeBodyPart mailArchieve = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(fileList[index]);
			mailArchieve.setDataHandler(new DataHandler(fds));
			mailArchieve.setFileName(MimeUtility.encodeText(fds.getName(),
					"GBK", "B"));
			multipart.addBodyPart(mailArchieve);
		}
	}

	private String getMailList(String[] mailArray) {

		StringBuffer toList = new StringBuffer();
		int length = mailArray.length;
		if (mailArray != null && length < 2) {
			toList.append(mailArray[0]);
		} else {
			for (int i = 0; i < length; i++) {
				toList.append(mailArray[i]);
				if (i != (length - 1)) {
					toList.append(",");
				}

			}
		}
		return toList.toString();

	}

	public static SendMailUtil getInstance() {
		if (instance == null) {
			instance = new SendMailUtil();
		}
		return instance;
	}

	public static void main(String args[]) {

		// SendMailUtil cn = new SendMailUtil();
		// String affix =
		// "D:\\workspace\\create\\WebRoot\\sendedOffer\\������offer&&14531982775378517.doc";
		// String affixTitle = "������offer.doc";
		// String emailTitle = "��Ŀ����������ļ���";
		// String receiveEmail = "guohaihang0512@163.com";
		// String[] arg = new String[2];
		// arg[0] = "hr@xiangmu.ren";
		// arg[1] = "AAaa1234";
		// String text = "������,\n
		// ��ã�\n�ܸ��������ҹ�˾��Ŀ��Ƹ��ΪJava��������ʦһְ������������¼ȡ֪ͨ�飬������offer��Ҫ��׼����Ӧ��ְ���ϣ�ϣ�������µĹ�˾���µ�������ջ�";
		// cn.sendOfferEmail(affix, affixTitle, emailTitle, receiveEmail, arg,
		// text);

		// SendMailUtil send = SendMailUtil.getInstance();
		// String to[] = { "guohaihang0512@163.com" };
		// String cs[] = {"1182112519@qq.com"};
		// String ms[] = {"407564206@qq.com"};;
		// String subject = "����һ��";
		// String content = "�����ʼ����ݣ������ǲ��ԣ�����Ҫ�ظ�";
		// String formEmail = "hr@xiangmu.ren";
		// String[] arrArchievList = new String[2];
		// arrArchievList[0] = "D:\\������.doc";
		// arrArchievList[1] = "D:\\������.html";
		// // 2.����������
		// send.send_(to, cs, ms, subject, content, formEmail,
		// arrArchievList,"smtp.mxhichina.com","hr@xiangmu.ren", "AAaa1234");

	}

	public void send_(String to[], String cs[], String ms[], String subject,
			String content, String formEmail, String fileList[], String host,
			String user, String pwd) throws Exception {
		this.host = host;
		this.user = user;
		this.pwd = pwd;

		Properties p = new Properties();
		p.put("mail.smtp.auth", "true");
		p.put("mail.transport.protocol", "smtp");
		p.put("mail.smtp.host", host);
		p.put("mail.smtp.port", "25");
		// �����Ự
		Session session = Session.getInstance(p);
		Message msg = new MimeMessage(session); // ������Ϣ
		BodyPart messageBodyPart = new MimeBodyPart();
		Multipart multipart = new MimeMultipart();
		msg.setFrom(new InternetAddress(formEmail)); // ������

		String toList = null;
		String toListcs = null;
		String toListms = null;

		// ����,
		if (to != null) {
			toList = getMailList(to);
			InternetAddress[] iaToList = new InternetAddress()
					.parse(toList);
			msg.setRecipients(Message.RecipientType.TO, iaToList); // �ռ���
		}

		// ����
		if (cs != null) {
			toListcs = getMailList(cs);
			InternetAddress[] iaToListcs = new InternetAddress()
					.parse(toListcs);
			msg.setRecipients(Message.RecipientType.CC, iaToListcs); // ������
		}

		// ����
		if (ms != null) {
			toListms = getMailList(ms);
			InternetAddress[] iaToListms = new InternetAddress()
					.parse(toListms);
			msg.setRecipients(Message.RecipientType.BCC, iaToListms); // ������
		}
		msg.setSentDate(new Date()); // ��������
		msg.setSubject(subject); // ����
		msg.setText(content); // ����
		// ��ʾ��html��ʽ���ı�����
		messageBodyPart.setContent(content, "text/html;charset=gbk");
		multipart.addBodyPart(messageBodyPart);

		// 2.����������
		if (fileList != null) {
			addTach(fileList, multipart);
		}

		msg.setContent(multipart);
		// �ʼ�������������֤
		Transport tran = session.getTransport("smtp");
		tran.connect(host, user, pwd);
		tran.sendMessage(msg, msg.getAllRecipients()); // ����
		System.out.println("�ʼ����ͳɹ�");

	}

	public void send(String host, String user, String pwd, String text) {
		this.host = host;
		this.user = user;
		this.pwd = pwd;

		Properties props = new Properties();
		// ��Ҫ������Ȩ��Ҳ�����л����������У�飬��������ͨ����֤��һ��Ҫ����һ����
		props.put("mail.smtp.auth", "true");
		// ���÷����ʼ����ʼ������������ԣ�����ʹ�����׵�smtp��������
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "25");
		// �øո����úõ�props���󹹽�һ��session
		Session session = Session.getDefaultInstance(props);
		// ������������ڷ����ʼ��Ĺ�������console����ʾ������Ϣ��������ʹ
		// �ã�������ڿ���̨��console)�Ͽ��������ʼ��Ĺ��̣�
		session.setDebug(true);

		// ��sessionΪ����������Ϣ����
		MimeMessage message = new MimeMessage(session);
		try {
			// ���ط����˵�ַ
			message.setFrom(new InternetAddress(from));

			// message.setRecipient(Message.RecipientType.CC, iaToListcs);

			// �����ռ��˵�ַ
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			// ���ر���
			message.setSubject(subject);

			// ��multipart����������ʼ��ĸ����������ݣ������ı����ݺ͸���
			Multipart multipart = new MimeMultipart();

			// �����ʼ����ı�����
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setText(text);
			multipart.addBodyPart(contentPart);
			// ��Ӹ���
			BodyPart messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(affix);
			// ��Ӹ���������
			messageBodyPart.setDataHandler(new DataHandler(source));
			// ��Ӹ����ı���
			// �������Ҫ��ͨ�������Base64�����ת�����Ա�֤������ĸ����������ڷ���ʱ����������
			sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
			messageBodyPart.setFileName("=?GBK?B?"
					+ enc.encode(affixName.getBytes()) + "?=");
			multipart.addBodyPart(messageBodyPart);

			// ��multipart����ŵ�message��
			message.setContent(multipart);
			// �����ʼ�
			message.saveChanges();
			// �����ʼ�
			Transport transport = session.getTransport("smtp");
			// ���ӷ�����������
			transport.connect(host, user, pwd);
			// ���ʼ����ͳ�ȥ
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * affix����λ�ã�affixTitle�������⣬emailTitle�ʼ����� args[0]�����˻���args[1]��������
	 * receiveEmail�ռ���ַ text �ʼ�����
	 */
	public void sendOfferEmail(String affix, String affixTitle,
			String emailTitle, String receiveEmail, String args[], String text) {
		// smtp������
		String emailsmtp = env.getProperty("emailsmtp");
		// �����ʼ��˻�
		String emailaddress = "";
		// �����˻�����
		String emailpass = "";

		if (args != null && args.length > 0) {
			emailaddress = args[0];
		} else {
			emailaddress = env.getProperty("emailaddress");
		}
		if (args != null && args.length > 0) {
			emailpass = args[1];
		} else {
			emailpass = env.getProperty("emailpass");
		}

		SendMailUtil cn = new SendMailUtil();
		// ���÷����˵�ַ���ռ��˵�ַ���ʼ�����
		cn.setAddress(emailaddress, receiveEmail, emailTitle);
		// ����Ҫ���͸�����λ�úͱ���
		cn.setAffix(affix, affixTitle);
		// ����smtp�������Լ�������ʺź�����
		// cn.send("smtp.mxhichina.com", "hr@xiangmu.ren", "AAaa1234");
		cn.send(emailsmtp, emailaddress, emailpass, text);
	}

}