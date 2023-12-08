# TOTP Provider

- Basic totp provider library to generate TOTP ( RFC 6238 )

## Sample code
Sample code for generating TOTP
```java

import com.kraftsix.auth.core.HashAlgorithm;
import com.kraftsix.auth.iface.IOtpProvider;
import java.time.Instant;

public class Generator{
    
    public static void main(String[] args) {

        IOtpProvider provider = new TotpProvider();

        String otp=provider.getOtp(6, Instant.now().getEpochSecond(),"nofun",30, HashAlgorithm.SHA_1);

        System.out.println(otp);
    }
}

```

## Roadmap
Roadmap items for this library
- [x] Implement basic library to generate TOTP
- [ ] Ability to generate QR
- [ ] Improvements