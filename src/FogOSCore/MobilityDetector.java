package FogOSCore;

import FlexID.FlexID;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.logging.Level;

public class MobilityDetector implements Runnable {
    private FogOSCore core;
    private static final String TAG = "FogOSMobility";

    MobilityDetector(FogOSCore core) {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: Initialize MobilityDetector");
        core = this.core;
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: Initialize MobilityDetector");
    }

    public void run() {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: Run MobilityDetector");
        while (true)
        {
            try {
                Thread.sleep(2000);
                checkAddresses();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void checkAddresses() {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: checkAddresses()");

        try {
            Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface.getNetworkInterfaces();


            for (NetworkInterface networkInterface : Collections.list(enumNetworkInterfaces))
                displayInterfaceInformation(networkInterface);

        } catch (SocketException e) {
            e.printStackTrace();
        }

        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: checkAddresses()");
    }

    void displayInterfaceInformation(NetworkInterface networkInterface) throws SocketException {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "  Interface Name: " + networkInterface.getDisplayName());
        Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
            if (inetAddress.toString().contains("."))
                java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "  InetAddress: " + inetAddress);
        }
    }
}