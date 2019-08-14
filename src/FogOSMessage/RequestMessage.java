package FogOSMessage;

import FogOSControl.Core.FogOSBroker;
import versatile.flexid.FlexID;

public class RequestMessage extends Message {
    public RequestMessage() {
        super(MessageType.REQUEST);
    }

    public RequestMessage(FlexID deviceID) {
        super(MessageType.REQUEST, deviceID);
    }

    @Override
    public Message send(FogOSBroker broker) {
        return null;
    }
}
