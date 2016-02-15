package com.example.xingliu.uploadtest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Created by xingliu on 2/13/16.
 */
public class Encryptor {

    public static void encrypt(String key, String InFilePath, String OutFilePath) throws Throwable {
        FileInputStream fis = new FileInputStream(InFilePath);
        FileOutputStream fos = new FileOutputStream(OutFilePath);
        encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, fis, fos);
    }

    public static void decrypt(String key, String InFilePath, String OutFilePath) throws Throwable {
        FileInputStream fis = new FileInputStream(InFilePath);
        FileOutputStream fos = new FileOutputStream(OutFilePath);
        encryptOrDecrypt(key, Cipher.DECRYPT_MODE, fis, fos);
    }

    private static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {
        DESKeySpec dks = new DESKeySpec(key.getBytes());
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey desKey = skf.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for SunJCE

        if (mode == Cipher.ENCRYPT_MODE) {
            cipher.init(Cipher.ENCRYPT_MODE, desKey);
            CipherInputStream cis = new CipherInputStream(is, cipher);
            doCopy(cis, os);
        } else if (mode == Cipher.DECRYPT_MODE) {
            cipher.init(Cipher.DECRYPT_MODE, desKey);
            CipherOutputStream cos = new CipherOutputStream(os, cipher);
            doCopy(is, cos);
        }
    }

    private static void doCopy(InputStream is, OutputStream os) throws IOException {
        byte[] bytes = new byte[64];
        int numBytes;
        while ((numBytes = is.read(bytes)) != -1) {
            os.write(bytes, 0, numBytes);
        }
        os.flush();
        os.close();
        is.close();
    }
}
