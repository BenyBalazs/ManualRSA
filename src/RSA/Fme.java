package RSA;

import java.math.BigInteger;

public final class Fme {

    private static BigInteger zero = new BigInteger("0");
    private static BigInteger one = new BigInteger("1");
    private static BigInteger two = new BigInteger("2");

    private Fme(){}

    public static BigInteger modPower(BigInteger base, BigInteger exponent, BigInteger modulus) {

        if (modulus.equals(one))
            return zero;

        BigInteger result = one;
        base = base.mod(modulus);

        while (exponent.compareTo(zero) > 0) {
            if (exponent.mod(two).equals(one)) {
                result = (result.multiply(base)).mod(modulus);
            }
            exponent = exponent.shiftRight(1);
            base = (base.multiply(base)).mod(modulus);
        }
        return result;
    }
}
