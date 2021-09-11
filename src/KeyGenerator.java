import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
public class KeyGenerator {
    byte[] secret;
    byte[] hmac;

    public byte[] generateKey () {
        this.secret = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(secret);
        return secret;
    }

    public byte[] getSecret() {
        return secret;
    }

    public byte[] getHmac() {
        return hmac;
    }

    public byte[] generateHMAC (byte[] secret, String turnAi) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret, "HmacSHA256");
        byte[] message = turnAi.getBytes();
        mac.init(secretKey);
        hmac = mac.doFinal(message);
        return hmac;
    }

    public String byteToHex (byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

}
