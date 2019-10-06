package FogOSSecurity;

import FlexID.FlexID;
import FogOSSocket.FlexIDSession;

import java.util.logging.Level;

public class SecureFlexIDSession {
    private SecurityParameters securityParameters;
    private HandshakeProtocolManager handshakeManager;
    private RecordProtocolManager recordManager;
    private FlexIDSession flexIDSession;
    private final String TAG = "FogOSSecurity";

    public SecureFlexIDSession(Role role, FlexID dFID) {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: Initialize SecureFlexIDSession");
        initialization(role);
        FlexIDSession.accept(dFID);
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: Initialize SecureFlexIDSession");
    }

    public SecureFlexIDSession(Role role, FlexID sFID, FlexID dFID) {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: Initialize SecureFlexIDSession");
        initialization(role);
        flexIDSession = new FlexIDSession(sFID, dFID);
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: Initialize SecureFlexIDSession");
    }

    public SecureFlexIDSession(Role role, FlexIDSession flexIDSession) {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: Initialize SecureFlexIDSession");
        initialization(role);
        setFlexIDSession(flexIDSession);
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: Initialize SecureFlexIDSession");
    }

    private void initialization(Role role) {
        this.securityParameters = new SecurityParameters(role);
        this.handshakeManager = new HandshakeProtocolManager(this.securityParameters, this.flexIDSession);
        this.recordManager = new RecordProtocolManager(this.securityParameters, this.flexIDSession);
    }

    public int doHandshake() {
        return this.handshakeManager.doHandshake();
    }

    public int send(byte[] msg, int len) {
        return this.recordManager.send(msg, len);
    }

    public int send(String msg) {
        return this.recordManager.send(msg.getBytes(), msg.length());
    }

    public int recv(byte[] buf, int len) {
        return this.recordManager.recv(buf, len);
    }

    public SecurityParameters getSecurityParameters() {
        return securityParameters;
    }

    public FlexIDSession getFlexIDSession() {
        return flexIDSession;
    }

    public void setFlexIDSession(FlexIDSession flexIDSession) {
        this.flexIDSession = flexIDSession;
    }
}
