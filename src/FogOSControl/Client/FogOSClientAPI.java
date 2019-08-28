package FogOSControl.Client;

import FlexID.FlexID;
import FogOSMessage.QueryMessage;
import FogOSMessage.ReplyMessage;
import FogOSMessage.RequestMessage;
import FogOSMessage.ResponseMessage;

public interface FogOSClientAPI {

    // QueryMessage-related
    QueryMessage makeQueryMessage();
    QueryMessage makeQueryMessage(String keywords);
    ReplyMessage sendQueryMessage(QueryMessage queryMessage);

    // ReplyMessage-related

    // RequestMessage-related
    RequestMessage makeRequestMessage();
    RequestMessage makeRequestMessage(FlexID id);
    ResponseMessage sendRequestMessage(RequestMessage requestMessage);

    // ResponseMessage-related
}
