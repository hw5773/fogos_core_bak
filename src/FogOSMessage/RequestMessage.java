package FogOSMessage;

import FlexID.FlexID;
import FogOSControl.Core.FogOSBroker;

public class RequestMessage extends Message {
    private FlexID peerID;

    public RequestMessage() {
        super(MessageType.REQUEST);
    }

    public RequestMessage(FlexID deviceID) {
        super(MessageType.REQUEST, deviceID);
    }

    @Override
    public Message send(FogOSBroker broker, FlexID deviceID) {
        return null;
    }

    public FlexID getPeerID() {
        return peerID;
    }

    public void setPeerID(FlexID peerID) {
        this.peerID = peerID;
    }
}
