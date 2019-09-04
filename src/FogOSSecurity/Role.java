package FogOSSecurity;

public enum Role {
    INITIATOR(0),
    RESPONDER(1);

    private final int role;

    Role(int role) {
        this.role = role;
    }
}
