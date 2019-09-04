package FogOSSecurity;

public enum ProtocolVersion {
    VERSION1((byte)1);

    private final byte version;

    ProtocolVersion(byte version) {
        this.version = version;
    }

    public byte getProtocolVersion() {
        return version;
    }

    public static ProtocolVersion get(int value) {
        ProtocolVersion ret = ProtocolVersion.VERSION1;
        for (ProtocolVersion type : values()) {
            if (type.getProtocolVersion() == value) {
                ret = type;
            }
        }
        return ret;
    }
}
