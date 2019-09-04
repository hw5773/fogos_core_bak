package FogOSSecurity;

import FogOSSocket.FlexIDSession;

public class RecordProtocolManager extends ProtocolManager {
    RecordProtocolManager(SecurityParameters securityParameters, FlexIDSession flexIDSession) {
        super(securityParameters, flexIDSession);
    }

    public int send(byte[] msg, int len) {
        return 0;
    }

    public int recv(byte[] msg, int len) {
        return 0;
    }
}
