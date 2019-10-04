package FogOSMessage;

import FlexID.FlexID;
import FogOSCore.FogOSBroker;

import java.util.ArrayList;

public class ReplyMessage extends Message {
    private ArrayList<ReplyEntry> replyList;
    private static final String TAG = "FogOSMessage";

    public ReplyMessage() {
        super(MessageType.REPLY);
        init();
    }

    public ReplyMessage(FlexID deviceID) {
        super(MessageType.REPLY, deviceID);
        init();
    }

    @Override
    public void init() {
        replyList = new ArrayList<ReplyEntry>();
    }

    @Override
    public void test(FogOSBroker broker) {

    }

    public void addReplyEntry(String title, String desc, FlexID flexID) {
        ReplyEntry entry = new ReplyEntry(title, desc, flexID);
        this.replyList.add(entry);
    }

    public ArrayList<ReplyEntry> getReplyList() {
        return replyList;
    }
}
