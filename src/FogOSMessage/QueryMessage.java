package FogOSMessage;

import FlexID.FlexID;
import FogOSCore.FogOSBroker;

public class QueryMessage extends Message {

    public QueryMessage() {
        super(MessageType.QUERY);
        init();
    }

    public QueryMessage(FlexID deviceID) {
        super(MessageType.QUERY, deviceID);
        init();
    }

    @Override
    public void init() {

    }

    @Override
    public void test(FogOSBroker broker) {

    }
}
