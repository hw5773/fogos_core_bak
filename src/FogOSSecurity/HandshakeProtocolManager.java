package FogOSSecurity;

import FogOSSocket.FlexIDSession;

import java.util.logging.Level;

public class HandshakeProtocolManager extends ProtocolManager {
    private static final String TAG = "FogOSSecurity";

    HandshakeProtocolManager(SecurityParameters securityParameters, FlexIDSession flexIDSession){
        super(securityParameters, flexIDSession);
    }

    public int doHandshake() {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: doHandshake(): role: " + this.securityParameters.getRole().toString());
        int ret = 0;
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: doHandshake(): role: " + this.securityParameters.getRole().toString());
        return ret;
    }
}
