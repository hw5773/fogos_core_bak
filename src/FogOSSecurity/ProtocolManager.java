package FogOSSecurity;

import FogOSSocket.FlexIDSession;

public class ProtocolManager {
    protected SecurityParameters securityParameters;
    protected FlexIDSession flexIDSession;

    public ProtocolManager(SecurityParameters securityParameters, FlexIDSession flexIDSession) {
        this.securityParameters = securityParameters;
        this.flexIDSession = flexIDSession;
    }
}
