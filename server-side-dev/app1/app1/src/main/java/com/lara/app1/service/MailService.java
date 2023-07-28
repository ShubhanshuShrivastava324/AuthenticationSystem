package com.lara.app1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {

	@Autowired
	private JavaMailSender javamailSender;
	
	
	public String sendMailToVerifyEmailIdString(String token, String email)throws Exception
	{
		MimeMessage mine = javamailSender.createMimeMessage();
		MimeMessageHelper helper =new MimeMessageHelper(mine, true);
		helper.setTo(email);
		helper.setSubject("Subject Verify your EMail");
		StringBuffer sb = new StringBuffer();
		sb.append("<a href='http://localhost:9090/admin/verifyMail/");
		sb.append(token + "/" + email + "'>verify</a>");
		helper.setText(sb.toString(), true);
		javamailSender.send(mine);
		return "mail sent successfully";
		
	}
	public String sendMailOnForgottenPassword(String email) throws Exception
	{
		MimeMessage mime = javamailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mime, true);
		helper.setTo(email);
		helper.setSubject("Subject: Reset Your Password");
		StringBuffer sb = new  StringBuffer();
		sb.append("<form action='http://localhost:9090/admin/resetPassword' method='post'>");
		sb.append("<input type='hidden' name='email' value='" + email + "'>");
		sb.append("New Password:<input type='password' name='password'><br>");
		sb.append("Confirm Password:<input type='password' name='confirmPassword'><br>");
		sb.append("<input type='submit' value='Reset Password'>");
		sb.append("</form>");
		helper.setText(sb.toString(), true);
		javamailSender.send(mime);
		return "mail sent successfully";
	}
}
