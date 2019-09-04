package FogOSSecurity;

public class SecurityParameters {
    // parameters
    private byte[] masterSecret;
    private byte[] i2rSecret; // The session key from an initiator to a responder
    private byte[] r2iSecret; // The session key from an responder to an initiator

    SecurityParameters() {

    }

    public void setMasterSecret(byte[] masterSecret) {
        this.masterSecret = masterSecret;
    }

    public void generateKeys() {
        // TODO: generate keys based on the master secret, error if the master secret is not set.
    }
}
