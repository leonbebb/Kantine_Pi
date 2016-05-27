/*
 * Quelle : http://stackoverflow.com/questions/20227/how-do-i-use-3des-encryption-decryption-in-java
 * @author shivshankar pal
 * Now, 3DES comes in two main flavors: The "2 key" version; 
 * in this version, the 3DES key consists of two DES keys; k1k1 and k2k2, and we implicitly assume that k1=k3k1k3. 
 * DES keys are typically represented in 64 bits, and so this version of 3DES has 128 bit keys.
 */
package kantine_pi;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

/**
 * DES3_Verschlüsselung sorgt dafür dass die Daten verschlüsselt und
 * entschlüsselt werden also für Sicherheit sorgen
 *
 * @author shivshankar pal this code is working properly. doing proper
 * encryption and description note:- it will work only with jdk8
 *
 */
public class DES3_Verschlüsselung {
//    private static byte[] key = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
//            0x00, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x02, 0x02,
//            0x02, 0x02, 0x02, 0x02, 0x02, 0x02 };
//
//    private static byte[] keyiv = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
//            0x00 };

    private static byte[] key1;
    private static byte[] key2;
    private static boolean keys_geladen = false;

    public static void keys_laden(byte[] k1, byte[] k2) {
        key1 = k1;
        key2 = k2;
        keys_geladen = true;
    }

    public static String encode(String args) {
        if (!keys_geladen) {
            return null;
        }

        byte[] encoding;
        try {
            encoding = Base64.getEncoder().encode(args.getBytes("UTF-8"));
            byte[] str5 = des3EncodeCBC(key1, key2, encoding);

            byte[] encoding1 = Base64.getEncoder().encode(str5);
            return new String(encoding1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decode(String args) {
        if (!keys_geladen) {
            return "key nicht geladen";
        }

        try {

            byte[] decode = Base64.getDecoder().decode(args.getBytes("UTF-8"));
            byte[] str6 = des3DecodeCBC(key1, key2, decode);
            String data = new String(str6);
            byte[] decode1 = Base64.getDecoder().decode(data.trim().getBytes("UTF-8"));
            return new String(decode1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "u mistaken in try block";

    }

    private static byte[] des3EncodeCBC(byte[] key, byte[] keyiv, byte[] data) {
        try {
            Key deskey = null;
            DESedeKeySpec spec = new DESedeKeySpec(key);
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            deskey = keyfactory.generateSecret(spec);

            Cipher cipher = Cipher.getInstance("desede/ CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(keyiv);
            cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
            byte[] bout = cipher.doFinal(data);
            return bout;

        } catch (Exception e) {
            System.out.println("methods qualified name" + e);
        }
        return null;

    }

    private static byte[] des3DecodeCBC(byte[] key, byte[] keyiv, byte[] data) {
        try {
            Key deskey = null;
            DESedeKeySpec spec = new DESedeKeySpec(key);
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            deskey = keyfactory.generateSecret(spec);

            Cipher cipher = Cipher.getInstance("desede/ CBC/NoPadding");//PKCS5Padding NoPadding
            IvParameterSpec ips = new IvParameterSpec(keyiv);
            cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

            byte[] bout = cipher.doFinal(data);

            return bout;

        } catch (Exception e) {
            System.out.println("methods qualified name" + e);
        }

        return null;

    }

}
