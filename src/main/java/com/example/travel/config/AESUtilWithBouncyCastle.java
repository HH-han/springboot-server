package com.example.travel.config;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.Security;
import java.util.Base64;

public class AESUtilWithBouncyCastle {
    private static final String ALGORITHM = "AES";
    private static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS7Padding";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    // 生成AES密钥
    public static SecretKey generateKey(int keySize) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM, "BC");
        keyGen.init(keySize);
        return keyGen.generateKey();
    }

    // AES加密
    public static String encrypt(String plainText, SecretKey key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION, "BC");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // AES解密
    public static String decrypt(String encryptedText, SecretKey key, byte[] iv) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION, "BC");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

    public static void main(String[] args) throws Exception {
        // 生成AES密钥
        SecretKey secretKey = generateKey(256); // 可选择128、192或256
        byte[] iv = new byte[16]; // 初始化向量，长度为16字节，实际使用中应随机生成

        String plainText = "Hello, Bouncy Castle AES!";
        String encryptedText = encrypt(plainText, secretKey, iv);
        System.out.println("Encrypted: " + encryptedText);

        String decryptedText = decrypt(encryptedText, secretKey, iv);
        System.out.println("Decrypted: " + decryptedText);
    }
}