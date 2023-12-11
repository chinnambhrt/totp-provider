package com.kraftsix.auth.core;

public enum PasswordCharacterSet {

    ALPHABET_SPECIAL("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+"),
    ALPHABET("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"),
    ALPHABET_BASE32("ABCDEFGHIJKLMNOPQRSTUVWXYZ234567");

    private String charset;

    PasswordCharacterSet(String charset){
        this.charset=charset;
    }

    public String getCharset(){
        return this.charset;
    }

    @Override
    public String toString() {
        return this.charset;
    }
}
