package com.kraftsix.auth;

import com.kraftsix.auth.core.HashAlgorithm;
import com.kraftsix.auth.iface.IOtpProvider;
import com.kraftsix.auth.utils.CipherUtil;
import org.apache.commons.codec.binary.Base32;

public class TotpProvider implements IOtpProvider {

    private static final int[] DIGITS_POWER  = {1,10,100,1000,10000,100000,1000000,10000000,100000000 };

    private final CipherUtil cipherUtil = new CipherUtil();

    private  final HashAlgorithm DEFAULT_ALGORITHM = HashAlgorithm.SHA_1;

    private  final int DEFAULT_WINDOW = 30;


    @Override
    public String getOtp(int digits, Long time, String key, int windowSize, HashAlgorithm algorithm) {

        String result="";

        byte[] msg_hash = cipherUtil.generateHOTPHex(time,windowSize);

        byte[] k = (new Base32().decode(key.getBytes()));

        byte[] hash = cipherUtil.getSHA(algorithm,k,msg_hash);

        int offset = hash[hash.length - 1] & 0xf;

        int binary =
                ((hash[offset] & 0x7f) << 24) |
                        ((hash[offset + 1] & 0xff) << 16) |
                        ((hash[offset + 2] & 0xff) << 8) |
                        (hash[offset + 3] & 0xff);

        int otp = binary % DIGITS_POWER[digits];

        result = Integer.toString(otp);
        while (result.length() < digits) {
            result = "0" + result;
        }

        return result;
    }


    @Override
    public String getOtp(int digits, Long time, String key, int windowSize) {
        return getOtp(digits,time,key,windowSize,DEFAULT_ALGORITHM);
    }

    @Override
    public String getOtp(int digits, Long time, String key) {
        return getOtp(digits,time,key,DEFAULT_WINDOW,DEFAULT_ALGORITHM);
    }


    @Override
    public Boolean verifyOtp(String otp, Long time, String key, int windowSize, HashAlgorithm algorithm) {
        String expected = this.getOtp(otp.length(),time,key,windowSize,algorithm);
        return expected.equals(otp);
    }

    @Override
    public Boolean verifyOtp(String otp, Long time, String key, int windowSize) {
        String expected = this.getOtp(otp.length(),time,key,windowSize,DEFAULT_ALGORITHM);
        return expected.equals(otp);
    }

    @Override
    public Boolean verifyOtp(String otp, Long time, String key) {
        String expected = this.getOtp(otp.length(),time,key,DEFAULT_WINDOW,DEFAULT_ALGORITHM);
        return expected.equals(otp);
    }
}
