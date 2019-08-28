package FogOSMessage;

import FlexID.FlexID;
import FogOSControl.Core.FogOSBroker;

public class QueryMessage extends Message {

    public QueryMessage() {
        super(MessageType.QUERY);
    }

    public QueryMessage(FlexID deviceID) {
        super(MessageType.QUERY, deviceID);
    }

    @Override
    public Message send(FogOSBroker broker, FlexID deviceID) {
        ReplyMessage replyMessage = new ReplyMessage();
        return null;
    }
}
