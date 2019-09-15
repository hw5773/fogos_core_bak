package FogOSMessage;

import FlexID.FlexID;
import FogOSCore.FogOSBroker;

import java.util.ArrayList;

public class ReplyMessage extends Message {
    private ArrayList<ReplyEntry> replyList;
    private static final String TAG = "FogOSMessage";

    public ReplyMessage() {
        super(MessageType.REPLY);
        replyList = new ArrayList<ReplyEntry>();
    }

    public ReplyMessage(FlexID deviceID) {
        super(MessageType.REPLY, deviceID);
        replyList = new ArrayList<ReplyEntry>();
    }

    @Override
    public Message send(FogOSBroker broker, FlexID deviceID) {
        return null;
    }

    public void addReplyEntry(String title, String desc, FlexID flexID) {
        ReplyEntry entry = new ReplyEntry(title, desc, flexID);
        this.replyList.add(entry);
    }

    public ArrayList<ReplyEntry> getReplyList() {
        return replyList;
    }
}
