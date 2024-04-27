package com.framework.testlibrary;

import org.testng.annotations.Test;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class MailTestClass {

	@Test
	public void Reports() throws EmailException {
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("AllTests.html");// path of the Test report
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Test Report");
		attachment.setName("AllTests.html");

		MultiPartEmail email = new MultiPartEmail();
		System.out.println("mail sending...");
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587); // 465
		email.setAuthenticator(new DefaultAuthenticator("tejalgavade.wohlig@gmail.com", "ekxnjxqzexoonjot"));
		email.setSSLOnConnect(true);
		email.setFrom("tejalgavade.wohlig@gmail.com");
		email.setSubject("Fairplay Reset Password Page Report");
		
		email.setMsg("Test report for Reset Password page."
				+"\n"
				+ "\nThanks & Regards"
				+ "\nTejal Gavade");
		
	//	email.addTo("pratiksha.vaidya@wohlig.com");
	//	email.addTo("danish@wohlig.com");
		email.addTo("wilfred.william@wohlig.com ");
		email.addTo("darshan.satardekar@wohlig.com");
		email.addTo("smit.chheda@wohlig.com");
		email.addTo("idris.galiyara@wohlig.com");
		email.addTo("tejal.gavade@wohlig.com");
		email.addTo("sameer.surve@wohlig.com");
		email.attach(attachment);
		email.send();
		System.out.println("Mail sent");

	}
}
