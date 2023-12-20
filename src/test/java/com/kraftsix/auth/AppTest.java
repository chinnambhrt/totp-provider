package com.kraftsix.auth;

import static org.junit.Assert.assertTrue;

import com.kraftsix.auth.qr.DefaultQRGenerator;
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

    @Test
    public void generateTotp3(){

        String key = "ZUYFPVUOK4OFHRS52CG3";

        IOtpProvider provider = new TotpProvider();

        String otp=provider.getOtp(6, 1702438009L, key,30, HashAlgorithm.SHA_1);

        System.out.println("Epoch:"+otp);

//        assertTrue("230514".equals(otp));

    }

    @Test
    public void generateQR(){

        String otpauth_url = "otpauth://totp/kraftsix%20com:chinnambhrt?secret=RA37PQXYKDEUPYJ4B45M&issuer=kraftsix%20com&algorithm=SHA1&digits=6&period=30";

        DefaultQRGenerator generator = new DefaultQRGenerator();

        String base64 = generator.createQR(otpauth_url);

    }





}
