package FogOSMessage;

import FlexID.FlexID;
import FogOSCore.FogOSBroker;

public class RequestMessage extends Message {
    private FlexID peerID;

    public RequestMessage() {
        super(MessageType.REQUEST);
        init();
    }

    public RequestMessage(FlexID deviceID) {
        super(MessageType.REQUEST, deviceID);
        init();
    }

    @Override
    public void init() {

    }

    @Override
    public void test(FogOSBroker broker) {

    }

    public FlexID getPeerID() {
        return peerID;
    }

    public void setPeerID(FlexID peerID) {
        this.peerID = peerID;
    }
}
