package FogOSSecurity;

public abstract class SecurityMessage {
    protected ContentType contentType;

    SecurityMessage(byte[] message) {
        deserialize(message);
    }

    public abstract byte[] serialize();
    public abstract void deserialize(byte [] message);

    public ContentType getContentType() {
        return contentType;
    }
}
