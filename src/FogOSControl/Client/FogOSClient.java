package FogOSControl.Client;

import FlexID.FlexID;
import FogOSControl.Core.FogOSCore;
import FogOSMessage.*;

public class FogOSClient implements FogOSClientAPI {
    private FogOSCore core;
    public FogOSClient() {
        core = new FogOSCore();
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
        return null;
    }

    public RequestMessage makeRequestMessage() {
        RequestMessage requestMessage = (RequestMessage) core.generateMessage(MessageType.REQUEST);
        return requestMessage;
    }

    public RequestMessage makeRequestMessage(FlexID id) {
        RequestMessage requestMessage = (RequestMessage) core.generateMessage(MessageType.REQUEST);
        requestMessage.addAttrValuePair("id", id.getIdentity().toString());
        return requestMessage;
    }

    public ResponseMessage sendRequestMessage(RequestMessage requestMessage) {
        return null;
    }
}
