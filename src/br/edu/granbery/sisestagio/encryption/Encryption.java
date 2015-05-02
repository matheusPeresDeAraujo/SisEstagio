package br.edu.granbery.sisestagio.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class Encryption {
	private static Encryption instance = new Encryption();

	public static Encryption getInstance() {
		return instance;
	}

	public String encryptionMd5(String valor) throws Exception {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(valor.getBytes());
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(digest.digest());
			//System.out.println(encoder.encode(digest.digest()));
			//return valor;
		} catch (NoSuchAlgorithmException ns) {
			throw new Exception();
		}
	}

}
