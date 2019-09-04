package FogOSSecurity;

public enum ContentType {
    NONE(0),
    HANDSHAKE(22),
    APPLICATION(20);

    private final byte number;

    ContentType(int number) {
        this.number = (byte) number;
    }

    public byte getNumber() {
        return number;
    }

    public static ContentType get(int value) {
        ContentType ret = ContentType.NONE;
        for (ContentType type : values()) {
            if (type.getNumber() == value) {
                ret = type;
            }
        }
        return ret;
    }
}
