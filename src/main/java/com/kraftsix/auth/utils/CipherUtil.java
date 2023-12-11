package com.kraftsix.auth.utils;

import com.kraftsix.auth.core.HashAlgorithm;
import com.kraftsix.auth.core.PasswordCharacterSet;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

public class CipherUtil {

    private static final String ALPHABET_SPECIAL = "";

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";

    //private static final Logger logger= Lo;

    /**
     * Generate random password/string of specified bytes
     * @param length length of string in bytes
     * @param characterSet character set that needs to be used
     * @return String with given length
     */
    public String generateRandomPassword(int length, PasswordCharacterSet characterSet){

        String character_set = characterSet.getCharset();

        SecureRandom random = new SecureRandom((""+new Date().getTime()).getBytes());

        StringBuilder sb = new StringBuilder();

        int bound = character_set.length();

        for(int i = 0; i< (length); i++){

            int idx = Math.abs(random.nextInt()) % bound;

            sb.append(character_set.charAt(idx));

        }

        return sb.toString();

    }

    /**
     * Generate random password/string of specified bytes
     * @param length length of string in bytes
     * @return String with given length
     */
    public String generateRandomPassword(int length){
        return  generateRandomPassword(length,PasswordCharacterSet.ALPHABET);
    }


    /**
     * Generates random key for the provided SHA algorithm
     * @param algorithm HashAlgorithm {@link HashAlgorithm} for which key has to be generated
     * @return A random key suitable for generating SHA
     */
    public String generateRandomKeyForOTP(HashAlgorithm algorithm){
        return generateRandomPassword(algorithm.getKeyLength(), PasswordCharacterSet.ALPHABET_BASE32);
    }



    /**
     * This method uses the JCE to provide the crypto algorithm.
     * HMAC computes a Hashed Message Authentication Code with the
     * crypto hash algorithm as a parameter.
     *
     * @param algorithm: the crypto algorithm (HmacSHA1, HmacSHA256,
     *                             HmacSHA512)
     * @param key: the bytes to use for the HMAC key
     * @param text_to_get_hash: the message or text to be authenticated
     */

    public byte[] getSHA(HashAlgorithm algorithm, byte[] key, byte[] text_to_get_hash){

        try
        {
            Mac mac = Mac.getInstance(algorithm.getAlgorithm());

            SecretKeySpec macKey = new SecretKeySpec(key, "RAW");

            mac.init(macKey);

            return mac.doFinal(text_to_get_hash);

        }
        catch (Exception excp)
        {
            excp.printStackTrace();
            return null;
        }
    }

    /**
     * This method uses the JCE to provide the crypto algorithm.
     * HMAC computes a Hashed Message Authentication Code with the
     * crypto hash algorithm as a parameter.
     *
     * @param crypto: the crypto algorithm (HmacSHA1, HmacSHA256,
     *                             HmacSHA512)
     * @param keyBytes: the bytes to use for the HMAC key
     * @param text: the message or text to be authenticated
     */


    public  byte[] hmac_sha(String crypto, byte[] keyBytes,
                                   byte[] text){
        try {
            Mac hmac;
            hmac = Mac.getInstance(crypto);
            SecretKeySpec macKey =
                    new SecretKeySpec(keyBytes, "RAW");
            hmac.init(macKey);
            return hmac.doFinal(text);
        } catch (GeneralSecurityException gse) {
            throw new UndeclaredThrowableException(gse);
        }
    }

    /**
     * This method converts a HEX string to Byte[]
     *
     * @param hex: the HEX string
     *
     * @return: a byte array
     */
    public byte[] hexToByteArray(String hex){
        // Adding one byte to get the right conversion
        // Values starting with "0" can be converted
        byte[] bArray = new BigInteger("10" + hex,16).toByteArray();

        // Copy all the REAL bytes, not the "first"
        byte[] ret = new byte[bArray.length - 1];
        for (int i = 0; i < ret.length; i++)
            ret[i] = bArray[i+1];
        return ret;
    }

    /**
     * Generates a HOTP compliant (RFC 4226) hex string from a provided time instance
     * @param time utc time instance for which HOTP hex bytes to be generated
     * @param window period for which a HOTP has to refreshed.Typically 30/60
     * @returns A byte array representing hex
     */
    public byte[] generateHOTPHex(Long time,int window){
        Long T0 = 0L;
        long T = (time-T0)/window;
        StringBuilder steps = new StringBuilder(Long.toHexString(T));
        while(steps.length()<16) steps.insert(0, "0");
        return hexToByteArray(steps.toString());
    }

}
