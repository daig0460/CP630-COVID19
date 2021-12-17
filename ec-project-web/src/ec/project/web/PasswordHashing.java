package ec.project.web;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashing {

	public static String HashPassword(String password) throws Exception {
		
		MessageDigest md;

		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}

		md.update(password.getBytes());
		byte[] digest = md.digest();
		
		StringBuilder sb = new StringBuilder(32);
		
		for (byte b : digest)
		{
			sb.append(String.format("%02x", b & 0xff));
		}
		
		return sb.toString();
	}
}