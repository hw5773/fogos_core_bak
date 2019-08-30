package FogOSMessage;

import FlexID.FlexID;

public class ReplyEntry {
    private String title;
    private String desc;
    private FlexID flexID;

    public ReplyEntry(String title, String desc, FlexID flexID) {
        this.title = title;
        this.desc = desc;
        this.flexID = flexID;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public FlexID getFlexID() {
        return flexID;
    }
}