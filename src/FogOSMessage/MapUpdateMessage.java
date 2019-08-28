package FogOSMessage;

import FlexID.FlexID;
import FogOSControl.Core.FogOSBroker;

public class MapUpdateMessage extends Message {

    public MapUpdateMessage() {
        super(MessageType.MAP_UPDATE);
    }

    public MapUpdateMessage(FlexID deviceID) {
        super(MessageType.MAP_UPDATE, deviceID);
    }

    @Override
    public Message send(FogOSBroker broker, FlexID deviceID) {
        return null;
    }
}
