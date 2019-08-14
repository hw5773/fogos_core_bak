package FogOSControl.Client;

import FogOSMessage.QueryMessage;
import FogOSMessage.ReplyMessage;
import FogOSMessage.RequestMessage;
import FogOSMessage.ResponseMessage;

public interface FogOSClientAPI {

    // QueryMessage-related
    QueryMessage makeQueryMessage();
    ReplyMessage sendQueryMessage(QueryMessage queryMessage);

    // ReplyMessage-related

    // RequestMessage-related
    RequestMessage makeRequestMessage();
    ResponseMessage sendRequestMessage(RequestMessage requestMessage);

    // ResponseMessage-related
}
