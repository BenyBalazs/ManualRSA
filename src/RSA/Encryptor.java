package RSA;

import java.math.BigInteger;


import static RSA.Fme.modPower;

public class Encryptor {

    private BigInteger[] publicKeys;

    public Encryptor(BigInteger[] publickeys){
        publicKeys = publickeys;
    }

    public BigInteger encrypt(String message) throws Exception {

        byte[] allBytes = message.getBytes();
        BigInteger mToEncrypt = new BigInteger(allBytes);

        if(mToEncrypt.compareTo(publicKeys[0]) > 0)
            throw new Exception("Massage Was Too Long");

        mToEncrypt = modPower(mToEncrypt,publicKeys[1],publicKeys[0]);

        return mToEncrypt;
    }
}
