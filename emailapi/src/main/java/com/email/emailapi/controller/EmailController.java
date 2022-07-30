package com.email.emailapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.email.emailapi.model.EmailRequest;
import com.email.emailapi.model.EmailResponse;
import com.email.emailapi.service.EmailService;

@RestController
@CrossOrigin
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping("/welcome")
	public String welcome() {
		return "hello this is my email api!";
	}
	
	@RequestMapping(value = "/sendemail", method=RequestMethod.POST)
	public ResponseEntity<?> sendMail(@RequestBody EmailRequest request) {
		System.out.println(request);
		boolean result=this.emailService.sendMail(request.getSubject(),request.getMessage(),request.getTo());
		
		if(result) {
			return ResponseEntity.ok(new EmailResponse("Email is Sent Successfully!"));
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email not Sent!"));
		}
	}
}
