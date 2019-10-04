package FogOSClient;

import FlexID.FlexID;
import FogOSMessage.QueryMessage;
import FogOSMessage.ReplyMessage;
import FogOSMessage.RequestMessage;
import FogOSMessage.ResponseMessage;

public interface FogOSClientAPI {

    // QueryMessage-related
    QueryMessage makeQueryMessage();
    QueryMessage makeQueryMessage(String keywords);
    void sendQueryMessage(QueryMessage queryMessage);

    // ReplyMessage-related
    ReplyMessage getReplyMessage();

    // RequestMessage-related
    RequestMessage makeRequestMessage();
    RequestMessage makeRequestMessage(FlexID id);
    void sendRequestMessage(RequestMessage requestMessage);

    // ResponseMessage-related
    ResponseMessage getResponseMessage();
}
