package FogOSMessage;

import FlexID.FlexID;
import FogOSControl.Core.FogOSBroker;

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
