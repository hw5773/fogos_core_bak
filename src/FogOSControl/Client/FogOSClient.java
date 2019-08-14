package FogOSControl.Client;

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

    public ReplyMessage sendQueryMessage(QueryMessage queryMessage) {
        return null;
    }

    public RequestMessage makeRequestMessage() {
        return null;
    }

    public ResponseMessage sendRequestMessage(RequestMessage requestMessage) {
        return null;
    }
}
