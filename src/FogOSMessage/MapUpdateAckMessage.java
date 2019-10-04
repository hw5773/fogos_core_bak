package FogOSMessage;

import FlexID.FlexID;
import FogOSCore.FogOSBroker;

public class MapUpdateAckMessage extends Message {

    public MapUpdateAckMessage() {
        super(MessageType.MAP_UPDATE_ACK);
        init();
    }

    public MapUpdateAckMessage(FlexID deviceID) {
        super(MessageType.MAP_UPDATE_ACK, deviceID);
        init();
    }

    @Override
    public void init() {

    }

    @Override
    public void test(FogOSBroker broker) {

    }
}
