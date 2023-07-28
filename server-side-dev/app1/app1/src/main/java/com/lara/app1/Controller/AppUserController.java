package com.lara.app1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lara.app1.dto.ChangePasswordStatus;
import com.lara.app1.dto.EmailVerificationStatus;
import com.lara.app1.dto.LoginSuccessStatus;
import com.lara.app1.dto.ResetPasswordStatus;
import com.lara.app1.dto.SendMailOnForgottenPasswordStatus;
import com.lara.app1.dto.SignupSucessStatus;
import com.lara.app1.entity.AppUser;
import com.lara.app1.entity.PasswordChange;
import com.lara.app1.service.AppUserService;

@RestController
@RequestMapping("admin")
@CrossOrigin	//	to allow any type of client


public class AppUserController 
{
	@Autowired
	private AppUserService appUserService;
	
	@PostMapping("signup")
	public ResponseEntity <SignupSucessStatus> save(@RequestBody AppUser appUser)
			throws Exception 
	{
		return ResponseEntity.ok(appUserService.save(appUser));
	}
/*
  {
  	"firstName" : "Mohan",
  	"lastName" : "Rao",
  	"email" : "m@gmail.com",
  	"password" : "abc"
  }	
  
  http://localhost:9090/admin/signup
  http://localhost:9090/admin/login
 */
	@PostMapping("login")
	public ResponseEntity <LoginSuccessStatus> login(@RequestBody AppUser appUser)
	{
		return ResponseEntity.ok(appUserService.login(appUser));
	}
	
	
	@PostMapping("sendMailOnForgottenPassword")
	public ResponseEntity<SendMailOnForgottenPasswordStatus> 
			sendMailOnForgottenPassword(@RequestBody AppUser appUser)
					throws Exception
	{
		return ResponseEntity.ok(
				appUserService.sendMailOnForgottenPassword(appUser));
	}
	
	
	@PostMapping("resetPassword")
	public ResponseEntity<ResetPasswordStatus>
	resetPassword(@RequestParam String email,
			@RequestParam String password,
			@RequestParam String confirmPassword)
	{
		return ResponseEntity.ok(appUserService.resetPassword(email,password,confirmPassword));
	}
	
	
	@PostMapping("changePassword")
	public ResponseEntity<ChangePasswordStatus>
	changePassword(@RequestBody PasswordChange passwordChange)
	{
		return ResponseEntity.ok(appUserService.changePassword
									(passwordChange.getEmail(),
									passwordChange.getOldPassword(), 
									passwordChange.getPassword(), 
									passwordChange.getConfirmPassword()));
	}
	
	
	@GetMapping("verifyMailId/{token}/{email}")
	public ResponseEntity <EmailVerificationStatus> verifyMailId(@PathVariable String token,@PathVariable String email)
			throws Exception 
	{
		return ResponseEntity.ok(appUserService.verifyMailId(token, email));
	}
}
