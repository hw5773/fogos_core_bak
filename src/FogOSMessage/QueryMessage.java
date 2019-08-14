package FogOSMessage;

import FogOSControl.Core.FogOSBroker;
import versatile.flexid.FlexID;

public class QueryMessage extends Message {

    public QueryMessage() {
        super(MessageType.QUERY);
    }

    public QueryMessage(FlexID deviceID) {
        super(MessageType.QUERY, deviceID);
    }

    @Override
    public Message send(FogOSBroker broker) {
        return null;
    }
}
