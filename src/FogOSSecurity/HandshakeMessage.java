package FogOSSecurity;

public class HandshakeMessage extends SecurityMessage {

    HandshakeMessage(byte[] message) {
        super(message);
        contentType = ContentType.HANDSHAKE;
    }

    @Override
    public byte[] serialize() {
        return new byte[0];
    }

    @Override
    public void deserialize(byte[] message) {

    }
}
