package FogOSMessage;

import FlexID.FlexID;
import FogOSControl.Core.FogOSBroker;

public class ResponseMessage extends Message {
    public ResponseMessage() {
        super(MessageType.RESPONSE);
    }

    public ResponseMessage(FlexID deviceID) {
        super(MessageType.RESPONSE, deviceID);
    }

    @Override
    public Message send(FogOSBroker broker) {
        return null;
    }
}
