package com.kraftsix.auth.core;

public enum HashAlgorithm {

    SHA_1("HmacSHA1",20),
    SHA_256("HmacSHA256",32),
    SHA_512("HmacSHA512",64);

    private final String algorithm;

    private final int keyLength;

    HashAlgorithm(String algorithm,int keyLength){
        this.algorithm=algorithm;
        this.keyLength = keyLength;
    }
    @Override
    public String toString() {
        return this.algorithm;
    }

    public String getAlgorithm(){
        return this.algorithm;
    }

    public int getKeyLength(){
        return this.keyLength;
    }
}
