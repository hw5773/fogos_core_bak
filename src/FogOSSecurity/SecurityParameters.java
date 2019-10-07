package FogOSSecurity;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityParameters {
    // parameters
    private Role role;
    private final int KEY_LENGTH = 16;
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
        generateKeys();
    }

    public byte[] getMasterSecret() {
        return masterSecret;
    }

    public void generateKeys() {
        byte[] tmp;
        byte[] key = new byte[KEY_LENGTH];
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(masterSecret);
            tmp = digest.digest();
            System.arraycopy(tmp, 0, key, 0, KEY_LENGTH);
            i2rSecret = new SecretKeySpec(key, "AES");

            System.out.println("I2R AES Key (" + key.length + " bytes)");
            System.out.println ("{ " + byteArrayToHex(key) + " }");
            System.out.println("");

            digest.reset();
            digest.update(tmp);
            tmp = digest.digest();
            System.arraycopy(tmp, 0, key, 0, KEY_LENGTH);
            r2iSecret = new SecretKeySpec(key, "AES");

            System.out.println("R2I AES Key (" + key.length + " bytes)");
            System.out.println ("{ " + byteArrayToHex(key) + " }");
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
