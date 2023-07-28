package com.lara.app1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lara.app1.dto.ChangePasswordStatus;
import com.lara.app1.dto.EmailVerificationStatus;
import com.lara.app1.dto.LoginSuccessStatus;
import com.lara.app1.dto.ResetPasswordStatus;
import com.lara.app1.dto.SendMailOnForgottenPasswordStatus;
import com.lara.app1.dto.SignupSucessStatus;
import com.lara.app1.entity.AppUser;
import com.lara.app1.repository.AppUserRepository;

@Service	//	every class should have this

public class AppUserService 
{
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private MailService mailService;
	public SignupSucessStatus save(AppUser appUser) throws Exception 
	{
		String token = generateToken();
		appUser.setToken(token);
		appUserRepository.save(appUser);
		mailService.sendMailToVerifyEmailIdString(token, appUser.getEmail());
		SignupSucessStatus signupSucessStatus = new SignupSucessStatus();
		signupSucessStatus.setMessage//	this is a message to send a client
		("Signup is very success.we have sent a mail. pls verify your email by clicking on activating email from your email box.");
		signupSucessStatus.setStatus(true);
		return signupSucessStatus;
	}
	private String generateToken()
	{
		String str = 
				"abcderijesiefjfiejSEWEFIEREGEEIFKEK95413544";
					
				String token = "";
				for (int i = 1; i <= 5; i++) 
				{
					token += str.charAt((int)(str.length()*Math.random()));
				//Math.random generate double number between zero to one
				//it generate diff-diff number in compilation
				}
				return token;
	}
	public LoginSuccessStatus login(AppUser clientObject)
	{
		LoginSuccessStatus loginSuccessStatus = new LoginSuccessStatus();
		loginSuccessStatus.setMessage("User is not available");

			Optional <AppUser> optional= Optional.ofNullable(appUserRepository.findById(clientObject.getEmail()).get());
			
			if (optional != null) 
			{
				AppUser dbObject = optional.get();
				loginSuccessStatus.setMessage("invalid password");
				if (dbObject.getPassword().equals(clientObject.getPassword())) 
				{
					loginSuccessStatus.setMessage("Email Id is not verified"
												+"we have sent a mail to mail box"
												+"pls verify your mail id from your mail box");
					if (dbObject.getStatus()==1)
					{
						loginSuccessStatus.setMessage("Login Success");
						loginSuccessStatus.setStatus(true);	//whenever login status is success login status will be true
					}
				}
			}
			return loginSuccessStatus;
	}
	public EmailVerificationStatus verifyMailId(String token, String email)
	{
		EmailVerificationStatus emailVerificationStatus = new EmailVerificationStatus();
		emailVerificationStatus.setMessage("Sorry, the token was wrong");
		AppUser appUser= appUserRepository.findByTokenAndEmail(token,email);
		if(appUser != null)
		{
			appUser.setStatus(1);
			appUserRepository.save(appUser);
			emailVerificationStatus.setStatus(true);
			emailVerificationStatus.setMessage("YOUR EMAIL VERIFIES SUCCESSFULLY");
		}
		return emailVerificationStatus;
	}
	public SendMailOnForgottenPasswordStatus 
	sendMailOnForgottenPassword(AppUser appUser) throws Exception
	{
		Optional<AppUser> optional = 
				appUserRepository.findById(appUser.getEmail());
		SendMailOnForgottenPasswordStatus forgottenPasswordStatus = 
				new SendMailOnForgottenPasswordStatus();
		forgottenPasswordStatus.setMessage("This user is not available");
		if(optional.isPresent())
		{
			mailService.sendMailOnForgottenPassword(appUser.getEmail());
			forgottenPasswordStatus.setMessage("Mail sent to " + 
					appUser.getEmail() + " with a password reset form");
			forgottenPasswordStatus.setStatus(true);
		}
		return forgottenPasswordStatus;
	}
	
	public ResetPasswordStatus
	resetPassword(String email,
				String password,
				String confirmPassword)
	{
		ResetPasswordStatus resetPasswordStatus = 
				new ResetPasswordStatus();
		resetPasswordStatus.setMessage
		("password and confirm password" + "both are not same");
		if (password.equals(confirmPassword)) 
		{
			AppUser appUser = appUserRepository.findById(email).get();
			appUser.setPassword(password);
			appUserRepository.save(appUser);
			
			resetPasswordStatus.setMessage("Password changed successfully");
			resetPasswordStatus.setStatus(true);
		}
		return resetPasswordStatus;
	}
	
	public ChangePasswordStatus 
	changePassword(String email, 
			String oldPassword,
			String password, 
			String confirmPassword)
	{
		ChangePasswordStatus changePasswordStatus = new ChangePasswordStatus();
		changePasswordStatus.setMessage("Old password is not correct");
		AppUser appUser = appUserRepository.findById(email).get();
		if(appUser.getPassword().equals(oldPassword))
		{
			changePasswordStatus.setMessage("password and confirm password "
					+ "both are not same");

		if(password.equals(confirmPassword))
			{
				appUser.setPassword(password);
				appUserRepository.save(appUser);	
				changePasswordStatus.setMessage("Password changed successfully");
				changePasswordStatus.setStatus(true);
			}
		}
		return changePasswordStatus;
	}
	/*
	{
		"email": "a@a.com",
		"oldPassword": "123",
		"password": "456",
		"confirmPassword": "456"
	}
	*/
}
