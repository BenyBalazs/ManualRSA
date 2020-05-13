package RSA;

import java.math.BigInteger;

public class KeyGenerator {

    private static BigInteger zero = new BigInteger("0");
    private static BigInteger one = new BigInteger("1");
    private static BigInteger two = new BigInteger("2");
    private static BigInteger three = new BigInteger("3");

    PrimeGenerator primeGenerator;

    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger phiN;
    private BigInteger e;
    private BigInteger d;


    public KeyGenerator(){

        primeGenerator = new PrimeGenerator(128 ,1000);
        p = primeGenerator.getProbablePrime();
        q = primeGenerator.getProbablePrime();
        n = p.multiply(q);
        phiN = p.subtract(one).multiply(q.subtract(one));
        e = calculateE();
        d = calculateD();
    }

    private BigInteger calculateE(){

        BigInteger e = three;
        boolean notCoprime = true;
        BigInteger[] temp;

        while (notCoprime){

            temp = extendedEuclideanAlgorithm(e,phiN);
            BigInteger gcd = e.multiply(temp[0]).add(phiN.multiply(temp[1]));

            if(gcd.equals(one))
                notCoprime = false;
            else
                e = e.add(one);
        }
        return e;
    }

    private BigInteger calculateD(){

        BigInteger[] temp = extendedEuclideanAlgorithm(e,phiN);
        BigInteger d = temp[0];

        if(d.compareTo(one) > 0 && d.compareTo(phiN) < 0 ){
            return d;
        }
        else{
            return d.add(phiN);
        }
    }

    private BigInteger[] extendedEuclideanAlgorithm(BigInteger a, BigInteger b){

        BigInteger x = zero;
        BigInteger y = one;
        BigInteger lastX = one;
        BigInteger lastY = zero;
        BigInteger temp;

        while (!b.equals(zero))
        {
            BigInteger[] quotientAndRemainder = a.divideAndRemainder(b);
            BigInteger quotient = quotientAndRemainder[0];

            a = b;
            b = quotientAndRemainder[1];

            temp = x;
            x = lastX.subtract(quotient.multiply(x));
            lastX = temp;

            temp = y;
            y = lastY.subtract(quotient.multiply(y));
            lastY = temp;
        }

        BigInteger[] tmp = { lastX, lastY };

        return tmp;
    }

    public BigInteger[] getPublicKey(){
        BigInteger[] tmp = { n, e };
        return tmp;
    }

    public BigInteger getPrivateKey(){
        return d;
    }

}
