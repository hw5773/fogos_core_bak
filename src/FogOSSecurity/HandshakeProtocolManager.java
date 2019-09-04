package FogOSSecurity;

import FogOSSocket.FlexIDSession;

public class HandshakeProtocolManager extends ProtocolManager {
    HandshakeProtocolManager(SecurityParameters securityParameters, FlexIDSession flexIDSession){
        super(securityParameters, flexIDSession);
    }

    public int doHandshake() {
        int ret = 0;
        return ret;
    }
}
