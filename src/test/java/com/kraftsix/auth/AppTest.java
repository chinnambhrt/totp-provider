package com.kraftsix.auth;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.kraftsix.auth.core.HashAlgorithm;
import com.kraftsix.auth.iface.IOtpProvider;

import java.time.Instant;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void generateTotp1(){

        IOtpProvider provider = new TotpProvider();

        String otp=provider.getOtp(8,1111111109000L,"3132333435363738393031323334353637383930",30, HashAlgorithm.SHA_1);

//        System.out.println(otp);

        assertTrue("69480427".equals(otp));

    }

    @Test
    public void generateTotp2(){

        IOtpProvider provider = new TotpProvider();

        String otp=provider.getOtp(6, 1702023397L,"nofun",30, HashAlgorithm.SHA_1);

//        System.out.println(now+":"+otp);

        assertTrue("230514".equals(otp));

    }





}
