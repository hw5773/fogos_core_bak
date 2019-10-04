package FogOSCore;

import java.util.logging.Level;

public class QoSInterpreter implements Runnable {
    private FogOSCore core;
    private static final String TAG = "FogOSQoSInterpreter";

    QoSInterpreter(FogOSCore core) {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: Initialize QoSInterpreter");
        this.core = core;
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: Initialize QoSInterpreter");
    }

    public void run() {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: Run QoSInterpreter");
    }
}
