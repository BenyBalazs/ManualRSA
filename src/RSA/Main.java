package RSA;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Please Write a massage to encrypt (and press enter):");

        Scanner in = new Scanner(System.in);
        String m = in.nextLine();

        KeyGenerator keys = new KeyGenerator();
        BigInteger[] publicKey = keys.getPublicKey();
        BigInteger privateKey = keys.getPrivateKey();

        try {
            //Encryption
            Encryptor encryptor = new Encryptor(publicKey);
            BigInteger encryptedMassage = encryptor.encrypt(m);

            System.out.println(encryptedMassage);

            //Decryption
            Decryptor decryptor = new Decryptor(privateKey,publicKey);
            String decryptedMassage = decryptor.decrypt(encryptedMassage);

            System.out.println(decryptedMassage);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
