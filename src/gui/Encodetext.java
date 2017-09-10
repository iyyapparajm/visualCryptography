package gui;

import java.security.*;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;
public class Encodetext {

    private static final String ALGO = "AES";
    private static final byte[] keyValue = new byte[] {'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };

public static String encode(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encodevalue = new BASE64Encoder().encode(encVal);
        return encodevalue;
    }

    public static String decode(String encodeData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encodeData);
        byte[] decValue = c.doFinal(decordedValue);
        String decodeValue = new String(decValue);
        return decodeValue;
    }
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
}
}
