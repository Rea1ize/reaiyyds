package com.hsae.ccs2plus;

public class AesUtils {

    static {
        System.loadLibrary("Aes128");
    }

    /**
     * AES加密, CBC, PKCS5Padding
     */
    public static native int encryptFile(String oldFile, String newFile);

    /**
     * AES解密, CBC, PKCS5Padding
     */
    public static native int decryptFile(String oldFile, String newFile);



    public int nativeEncryptFile(String oldFile, String newFile){
       int ret =  encryptFile(oldFile, newFile);
        return ret;
    }
}
