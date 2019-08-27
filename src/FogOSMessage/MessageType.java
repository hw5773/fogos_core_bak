package FogOSMessage;

import FlexID.FlexID;

public enum MessageType {
    JOIN("/configuration/join"),
    JOIN_ACK("/configuration/join_ack"),
    STATUS("/configuration/status"),
    STATUS_ACK("/configuration/status_ack"),
    LEAVE("/configuration/leave"),
    LEAVE_ACK("/configuration/leave_ack"),
    REGISTER("/configuration/register"),
    REGISTER_ACK("/configuration/register_ack"),
    UPDATE("/configuration/update"),
    UPDATE_ACK("/configuration/update_ack"),
    MAP_UPDATE("/configuration/map_update"),
    MAP_UPDATE_ACK("/configuration/map_update_ack"),
    QUERY("/utilization/query"),
    REPLY("/utilization/reply"),
    REQUEST("/utilization/request"),
    RESPONSE("/utilization/response");

    private final String topic;

    MessageType(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public byte[] getTopicWithDeviceID(FlexID deviceID) {
        byte[] topic = this.getTopic().getBytes();
        byte[] idbytes = deviceID.getIdentity();
        byte[] ret = new byte[topic.length + 1 + idbytes.length];
        System.arraycopy(topic, 0, ret, 0, topic.length);
        ret[topic.length] = '/';
        System.arraycopy(idbytes, 0, ret, topic.length + 1, idbytes.length);
        return ret;
    }
}
