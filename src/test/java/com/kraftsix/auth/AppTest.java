package com.kraftsix.auth;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.kraftsix.auth.core.HashAlgorithm;
import com.kraftsix.auth.iface.IOtpProvider;


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

        String otp=provider.getOtp(6, Instant.now().getEpochSecond(),"nofun",30, HashAlgorithm.SHA_1);

        System.out.println(otp);

        //assertTrue("312685".equals(otp));

    }





}
