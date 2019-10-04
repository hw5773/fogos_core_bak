package FogOSCore;

import java.util.logging.Level;

public class ResourceReporter implements Runnable {
    private FogOSCore core;
    private static final String TAG = "FogOSResourceReporter";

    ResourceReporter(FogOSCore core) {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: Initialize ResourceReporter");
        this.core = core;
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: Initialize ResourceReporter");
    }

    public void run() {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: Run ResourceReporter");
    }
}
