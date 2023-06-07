package com.spring.JWTSpringSecurity.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.JWTSpringSecurity.Model.AuthenticateRequest;
import com.spring.JWTSpringSecurity.Model.AuthenticateResponse;
import com.spring.JWTSpringSecurity.Service.myUserDetailssService;
import com.spring.JWTSpringSecurity.Util.JwtUtil;

@RestController
public class WelcomeController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private myUserDetailssService service;

	@Autowired
	private JwtUtil jutUtilToken;

	@RequestMapping("/welcome")
	public String print() {
		return "Hello and welcome";
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticateRequest authenticationrequest)
			throws Exception {
		
		//Do Authentication whether the password is correct for the user or not
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationrequest.getUserName(), authenticationrequest.getPassWord()));
		} catch (Exception e) {
			throw new Exception("Invalid UserName and Password",e);
		}
		//If user and password is correct then generated jwt token for user
		final UserDetails userDetails = service.loadUserByUsername(authenticationrequest.getUserName());
		final String jwt = jutUtilToken.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticateResponse(jwt));
	}
}