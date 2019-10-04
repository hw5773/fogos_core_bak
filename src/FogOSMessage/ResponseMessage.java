package FogOSMessage;

import FlexID.FlexID;
import FogOSCore.FogOSBroker;

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
    public void init() {
        
    }

    @Override
    public void test(FogOSBroker broker) {

    }

    public void setPeerID(FlexID peerID) {
        this.peerID = peerID;
    }

    public FlexID getPeerID() {
        return peerID;
    }
}
