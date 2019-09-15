package FogOSSecurity;

import FlexID.FlexID;
import FogOSSocket.FlexIDSession;

public class SecureFlexIDSession {
    private Role role;
    private SecurityParameters securityParameters;
    private HandshakeProtocolManager handshakeManager;
    private RecordProtocolManager recordManager;
    private FlexIDSession flexIDSession;

    public SecureFlexIDSession(Role role, FlexID sFID, FlexID dFID) {
        initialization(role);
        flexIDSession = new FlexIDSession(sFID, dFID);
    }

    public SecureFlexIDSession(Role role, FlexIDSession flexIDSession) {
        initialization(role);
        setFlexIDSession(flexIDSession);
    }

    private void initialization(Role role) {
        this.role = role;
        this.securityParameters = new SecurityParameters();
        this.handshakeManager = new HandshakeProtocolManager(this.securityParameters, this.flexIDSession);
        this.recordManager = new RecordProtocolManager(this.securityParameters, this.flexIDSession);
    }

    public int doHandshake() {
        return this.handshakeManager.doHandshake();
    }

    public int send(byte[] msg, int len) {
        return this.recordManager.send(msg, len);
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
