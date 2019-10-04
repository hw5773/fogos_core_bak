package FogOSMessage;

import FlexID.FlexID;
import FogOSCore.FogOSBroker;

public class UpdateMessage extends Message {

    public UpdateMessage() {
        super(MessageType.UPDATE);
        init();
    }

    public UpdateMessage(FlexID deviceID) {
        super(MessageType.UPDATE, deviceID);
        init();
    }

    @Override
    public void init() {

    }

    @Override
    public void test(FogOSBroker broker) {

    }
}
