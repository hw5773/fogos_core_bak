package FogOSSecurity;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityParameters {
    // parameters
    private Role role;
    private final int KEY_LENGTH = 32;
    private byte[] masterSecret;
    private SecretKeySpec i2rSecret; // The session key from an initiator to a responder
    private SecretKeySpec r2iSecret; // The session key from an responder to an initiator
    private int writeSequence;
    private int readSequence;

    SecurityParameters(Role role) {
        this.role = role;
        i2rSecret = null;
        r2iSecret = null;
        masterSecret = null;
    }

    public Role getRole() {
        return role;
    }

    public void setMasterSecret(byte[] masterSecret) {
        this.masterSecret = masterSecret;
    }

    public byte[] getMasterSecret() {
        return masterSecret;
    }

    public void generateKeys() {
        byte[] tmp;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA256");
            digest.reset();
            digest.update(masterSecret);
            tmp = digest.digest();
            i2rSecret = new SecretKeySpec(tmp, "AES");

            digest.reset();
            digest.update(tmp);
            tmp = digest.digest();

            System.out.println("I2R AES Key");
            System.out.println ("{ " + byteArrayToHex(tmp) + " }");
            System.out.println("");

            r2iSecret = new SecretKeySpec(tmp, "AES");

            System.out.println("R2I AES Key");
            System.out.println ("{ " + byteArrayToHex(tmp) + " }");
            System.out.println("");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        setWriteSequence(0);
        setReadSequence(0);
    }

    public int getWriteSequence() {
        return writeSequence;
    }

    public void setWriteSequence(int writeSequence) {
        this.writeSequence = writeSequence;
    }

    public int getReadSequence() {
        return readSequence;
    }

    public void setReadSequence(int readSequence) {
        this.readSequence = readSequence;
    }

    private String byteArrayToHex(byte[] a) {
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        for (final byte b: a) {
            sb.append(String.format("0x%02x, ", b & 0xff));
            idx++;
            if (idx % 8 == 0)
                sb.append("\n");
        }
        return sb.toString();
    }

    public SecretKeySpec getI2rSecret() {
        return i2rSecret;
    }

    public SecretKeySpec getR2iSecret() {
        return r2iSecret;
    }
}
