package FogOSMessage;

import FlexID.FlexID;
import FogOSControl.Core.FogOSBroker;

public class MapUpdateAckMessage extends Message {

    public MapUpdateAckMessage() {
        super(MessageType.MAP_UPDATE_ACK);
    }

    public MapUpdateAckMessage(FlexID deviceID) {
        super(MessageType.MAP_UPDATE_ACK, deviceID);
    }

    @Override
    public Message send(FogOSBroker broker, FlexID deviceID) {
        return null;
    }
}
