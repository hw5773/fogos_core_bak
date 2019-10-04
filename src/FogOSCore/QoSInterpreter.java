package FogOSCore;

import FogOSMessage.QueryMessage;
import FogOSMessage.RequestMessage;

import java.util.Hashtable;
import java.util.logging.Level;

public class QoSInterpreter {
    private FogOSCore core;
    private static final String TAG = "FogOSQoSInterpreter";

    QoSInterpreter(FogOSCore core) {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: Initialize QoSInterpreter");
        this.core = core;
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: Initialize QoSInterpreter");
    }

    // TODO: Check the Query message
    // Please add some attributes with values if they are not included in the query message
    // The following function is invoked in sendMessage() of FogOSCore (Please refer to it)
    public void checkQueryMessage(QueryMessage queryMessage) {
        Hashtable<String, String> avps = queryMessage.getAttrValueTable();

    }

    // TODO: Check the Request message
    // Please add some attributes with values if they are not included in the request message
    // The following function is invoked in sendMessage() of FogOSCore (Please refer to it)
    public void checkRequestMessage(RequestMessage requestMessage) {
        Hashtable<String, String> avps = requestMessage.getAttrValueTable();

    }
}
