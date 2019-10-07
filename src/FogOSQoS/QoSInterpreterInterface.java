package FogOSQoS;

import FogOSMessage.QueryMessage;
import FogOSMessage.RequestMessage;

import java.util.Hashtable;

public interface QoSInterpreterInterface {
    // TODO: Check the Query message
    // Please add some attributes with values if they are not included in the query message
    // The following function is invoked in sendMessage() of FogOSCore (Please refer to it)
    public void checkQueryMessage(QueryMessage queryMessage);

    // TODO: Check the Request message
    // Please add some attributes with values if they are not included in the request message
    // The following function is invoked in sendMessage() of FogOSCore (Please refer to it)
    public void checkRequestMessage(RequestMessage requestMessage);
}
