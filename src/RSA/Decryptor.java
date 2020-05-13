package RSA;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Base64;

import static RSA.Fme.modPower;

public class Decryptor {

    private BigInteger privateKey;
    private BigInteger[] publicKeys;

    public Decryptor(BigInteger privatekey, BigInteger[] publickeys){
        privateKey = privatekey;
        publicKeys = publickeys;
    }

    public String decrypt(BigInteger massage){

        BigInteger decryptedM = modPower(massage,privateKey,publicKeys[0]);
        byte[] allBytes = decryptedM.toByteArray();

        return new String(allBytes);

    }
}
