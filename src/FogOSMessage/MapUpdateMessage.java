package FogOSMessage;

import FlexID.FlexID;
import FogOSCore.FogOSBroker;

public class MapUpdateMessage extends Message {
    public MapUpdateMessage() {
        super(MessageType.MAP_UPDATE);
        init();
    }

    public MapUpdateMessage(FlexID deviceID) {
        super(MessageType.MAP_UPDATE, deviceID);
        init();
    }

    @Override
    public void init() {
        this.addAttrValuePair("mapUpdateID", Integer.toString(random.nextInt()));
        this.addAttrValuePair("flexID", getDeviceID().getStringIdentity());
    }

    @Override
    public void test(FogOSBroker broker) {

    }
}
