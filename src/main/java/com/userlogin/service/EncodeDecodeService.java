package com.userlogin.service;

import java.util.Base64;

public class EncodeDecodeService {
	
	public String encodePassword(String strPassword) {
		strPassword = new TokenService().getToken() + strPassword;
		Base64.Encoder encoder = Base64.getEncoder();  
		return encoder.encodeToString(strPassword.getBytes());
	}
	
	public String decodePassword(String strEncodedPassword) {
		 Base64.Decoder decoder = Base64.getDecoder();  
		 return new String(decoder.decode(strEncodedPassword)).substring(6);  
	}
}
