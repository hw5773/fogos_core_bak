package FogOSMessage;

import FlexID.FlexID;
import FlexID.Locator;
import FlexID.InterfaceType;
import FogOSControl.Core.FogOSBroker;

public class ResponseMessage extends Message {
    private FlexID peerID;

    public ResponseMessage() {
        super(MessageType.RESPONSE);
        peerID = null;
    }

    public ResponseMessage(FlexID peerID)
    {
        super(MessageType.RESPONSE);
        this.peerID = peerID;
    }

    @Override
    public Message send(FogOSBroker broker, FlexID deviceID) {
        return null;
    }

    public void setPeerID(FlexID peerID) {
        this.peerID = peerID;
    }

    public FlexID getPeerID() {
        return peerID;
    }
}
