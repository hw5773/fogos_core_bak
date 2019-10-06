package FogOSSecurity;

public enum Role {
    INITIATOR(0, "initiator"),
    RESPONDER(1, "responder");

    private final int role;
    private final String str;

    Role(int role, String str) {
        this.role = role;
        this.str = str;
    }
}
