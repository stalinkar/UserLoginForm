package com.userlogin.service;

public class TokenService {
	private String strToken;
	private int intTokenLength = 6;

	public String getToken() {
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(intTokenLength);
		for (int i = 0; i < intTokenLength; i++) {
			int index = (int) (alphaNumericString.length() * Math.random());
			sb.append(alphaNumericString.charAt(index));
		}
		return sb.toString();
	}
}
