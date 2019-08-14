package FogOSMessage;

import FogOSControl.Core.FogOSBroker;
import versatile.flexid.FlexID;

public class ReplyMessage extends Message {
    public ReplyMessage() {
        super(MessageType.REPLY);
    }

    public ReplyMessage(FlexID deviceID) {
        super(MessageType.REPLY, deviceID);
    }

    @Override
    public Message send(FogOSBroker broker) {
        return null;
    }
}
