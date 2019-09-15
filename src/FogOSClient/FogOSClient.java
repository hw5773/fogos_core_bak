package FogOSClient;

import FlexID.FlexID;
import FogOSCore.FogOSCore;
import FogOSMessage.*;
import FogOSSecurity.Role;
import FogOSSecurity.SecureFlexIDSession;

import java.util.logging.Level;

public class FogOSClient implements FogOSClientAPI {
    private FogOSCore core;
    private static final String TAG = "FogOSClient";

    public FogOSClient() {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: Initialize FogOSClient");
        core = new FogOSCore();
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: Initialize FogOSClient");
    }

    public QueryMessage makeQueryMessage() {
        return (QueryMessage) core.generateMessage(MessageType.QUERY);
    }

    public QueryMessage makeQueryMessage(String query) {
        QueryMessage queryMessage = (QueryMessage) core.generateMessage(MessageType.QUERY);
        queryMessage.addAttrValuePair("keywords", query);
        return queryMessage;
    }

    public ReplyMessage sendQueryMessage(QueryMessage queryMessage) {
        return (ReplyMessage) core.sendMessage(queryMessage);
    }

    public RequestMessage makeRequestMessage() {
        RequestMessage requestMessage = (RequestMessage) core.generateMessage(MessageType.REQUEST);
        return requestMessage;
    }

    public RequestMessage makeRequestMessage(FlexID id) {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: makeRequestMessage()");
        RequestMessage requestMessage = (RequestMessage) core.generateMessage(MessageType.REQUEST);
        requestMessage.setPeerID(id);
        requestMessage.addAttrValuePair("id", id.getStringIdentity());
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: makeRequestMessage()");
        return requestMessage;
    }

    public ResponseMessage sendRequestMessage(RequestMessage requestMessage) {
        return (ResponseMessage) core.sendMessage(requestMessage);
    }

    public SecureFlexIDSession createSecureFlexIDSession(Role role, FlexID sFID, FlexID dFID)
    {
        return core.createSecureFlexIDSession(role, sFID, dFID);
    }
}
