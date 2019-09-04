package FogOSSecurity;

public class ApplicationMessage extends SecurityMessage {
    ApplicationMessage(byte[] message) {
        super(message);
        contentType = ContentType.APPLICATION;
    }

    @Override
    public byte[] serialize() {
        return new byte[0];
    }

    @Override
    public void deserialize(byte[] message) {

    }
}
