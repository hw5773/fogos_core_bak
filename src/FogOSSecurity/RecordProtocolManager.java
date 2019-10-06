package FogOSSecurity;

import FogOSSocket.FlexIDSession;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.logging.Level;

public class RecordProtocolManager extends ProtocolManager {
    private static final String TAG = "FogOSSecurity";

    RecordProtocolManager(SecurityParameters securityParameters, FlexIDSession flexIDSession) {
        super(securityParameters, flexIDSession);
    }

    public int send(byte[] msg, int len) {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: send()");
        try {
            java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "msg: " + new String(msg, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int ret;
        byte[] ciph = null;
        if (this.securityParameters.getRole() == Role.INITIATOR) {
            ciph = encrypt(this.securityParameters.getI2rSecret(), msg, len);
        } else {
            ciph = encrypt(this.securityParameters.getR2iSecret(), msg, len);
        }
        this.flexIDSession.send(ciph);

        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: send()");
        return len;
    }

    public int recv(byte[] msg, int len) {
        byte[] ciph = new byte[16384];
        int rcvd = this.flexIDSession.receive(ciph);
        byte[] ret;
        if (this.securityParameters.getRole() == Role.INITIATOR) {
            ret = decrypt(this.securityParameters.getR2iSecret(), ciph, rcvd);
        } else {
            ret = decrypt(this.securityParameters.getI2rSecret(), ciph, rcvd);
        }
        System.arraycopy(ret, 0, msg, 0, ret.length);
        return ret.length;
    }

    byte[] encrypt(SecretKeySpec key, byte[] msg, int len) {
        byte[] ret = null;
        byte[] buf = new byte[len];
        System.arraycopy(msg, 0, buf, 0, len);
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/PKCS5Padding");
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update((byte) this.securityParameters.getWriteSequence());
            digest.update(this.securityParameters.getMasterSecret());
            byte[] iv = digest.digest();

            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
            ret = cipher.doFinal(msg);
            this.securityParameters.setWriteSequence(this.securityParameters.getWriteSequence() + 1);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return ret;
    }

    byte[] decrypt(SecretKeySpec key, byte[] ciph, int len) {
        byte[] ret = null;
        byte[] buf = new byte[len];
        System.arraycopy(ciph, 0, buf, 0, len);
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/PKCS5Padding");
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update((byte) this.securityParameters.getReadSequence());
            digest.update(this.securityParameters.getMasterSecret());
            byte[] iv = digest.digest();

            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
            ret = cipher.doFinal(ciph);

            this.securityParameters.setReadSequence(this.securityParameters.getReadSequence() + 1);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
