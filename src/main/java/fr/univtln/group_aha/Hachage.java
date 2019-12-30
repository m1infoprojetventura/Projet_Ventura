package fr.univtln.group_aha;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hachage {
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        return md.digest(input.getBytes(StandardCharsets.US_ASCII));
    }

    public static String toHexString(byte[] hash) {
        StringBuilder resultat = new StringBuilder();

        char[] alphabet = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

        for(byte b: hash) {
            int fort = (b & 0xf0) >> 4;
            int faible = b & 0x0f;
            resultat.append(alphabet[fort]);
            resultat.append(alphabet[faible]);
        }

        return resultat.toString();
    }
}
