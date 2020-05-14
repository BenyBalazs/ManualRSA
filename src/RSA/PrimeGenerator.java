package RSA;

import java.math.BigInteger;
import java.security.SecureRandom;

import static RSA.Fme.modPower;

public class PrimeGenerator {

    private Integer bitLength;
    private Integer numberOfTests;

    public PrimeGenerator(Integer bitlength, Integer numberoftests){
        bitLength = bitlength;
        numberOfTests = numberoftests;
    }

    public BigInteger getProbablePrime() {

        BigInteger randomNum = generateSecureRandomNumber(bitLength);

        while (!millerTest(randomNum)){
            randomNum = generateSecureRandomNumber(bitLength);
        }

        return randomNum;
    }

    private boolean millerTest(BigInteger numberToTest) {

        //Constant numbers for the calculations.
        BigInteger two = new BigInteger("2");
        BigInteger one = new BigInteger("1");
        BigInteger zero = new BigInteger("0");
        BigInteger three = new BigInteger("3");

        if(numberToTest.compareTo(one) == 0)
            return false;
        else if(numberToTest.compareTo(three) < 0)
            return true;
        //Number of times we can divide by 2.
        Integer s = 0;
        //Odd number
        BigInteger d = numberToTest.subtract(one);

        //Gets how many times we can divide by two.
        while (d.mod(two).equals(zero)){
            d = d.divide(two);
            s++;
        }

        //Runs the test for the given times.
        for(int k = 0; k < numberOfTests; k++) {

            BigInteger a = randomBetween(two, numberToTest.subtract(one));
            BigInteger result = modPower(a,d, numberToTest);

            if (result.equals(one) || result.equals(numberToTest.subtract(one)))
                continue;

            int i = 0;
            for (; i < s; i++) {
                result = modPower(result,two,numberToTest);
                if (result.equals(one))
                    return false;
                else if (result.equals(numberToTest.subtract(one))) {
                    break;
                }
            }

            if(i == s)
                return false;
        }
        return true;
    }
    //Generates random number between min and max.
    private BigInteger randomBetween(BigInteger min, BigInteger max){
        BigInteger a;

        do{
            a = new BigInteger(min.bitLength(), new SecureRandom());
        }while (a.compareTo(min) < 0|| a.compareTo(max) > 0);

        return a;
    }

    private BigInteger generateSecureRandomNumber(Integer bitLength){

        SecureRandom srg = new SecureRandom();
        return new BigInteger(bitLength,srg);
    }

}
