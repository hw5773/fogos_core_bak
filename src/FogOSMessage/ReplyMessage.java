package FogOSMessage;

import FlexID.FlexID;
import FogOSControl.Core.FogOSBroker;
import org.json.JSONArray;

public class ReplyMessage extends Message {
    private JSONArray idList;

    public ReplyMessage() {
        super(MessageType.REPLY);
        idList = null;
    }

    public ReplyMessage(FlexID deviceID) {
        super(MessageType.REPLY, deviceID);
    }

    @Override
    public Message send(FogOSBroker broker, FlexID deviceID) {
        return null;
    }

    public JSONArray getIDList() { return idList; }
}
