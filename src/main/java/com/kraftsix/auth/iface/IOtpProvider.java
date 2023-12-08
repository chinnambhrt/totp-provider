package com.kraftsix.auth.iface;

import com.kraftsix.auth.core.HashAlgorithm;

public interface IOtpProvider {

    String getOtp(int digits, Long time,String key,int windowSize, HashAlgorithm algorithm);

    String getOtp(int digits, Long time, String key, int windowSize);

    String getOtp(int digits, Long time, String key);

    Boolean verifyOtp(String otp, Long time, String key, int windowSize, HashAlgorithm algorithm);

    Boolean verifyOtp(String otp, Long time, String key, int windowSize);

    Boolean verifyOtp(String otp, Long time, String key);
}
