package FogOSCore;

import FlexID.FlexID;
import FogOSMessage.MapUpdateMessage;
import FogOSMessage.Message;
import FogOSMessage.MessageType;
import FogOSSecurity.SecureFlexIDSession;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;
import java.util.logging.Level;

public class MobilityDetector implements Runnable {
    private FogOSCore core;
    private boolean mobilityHappend = false;
    private static final String TAG = "FogOSMobility";
    private HashMap<String, String> interfaceIPAddr;
    private LinkedList<String> mapUpdateIDList;
    private String prev, curr;

    MobilityDetector(FogOSCore core) {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: Initialize MobilityDetector");
        this.core = core;
        interfaceIPAddr = new HashMap<String, String>();
        mapUpdateIDList = new LinkedList<>();
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: Initialize MobilityDetector");
    }

    public void run() {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: Run MobilityDetector");
        MapUpdateACKThread thread = new MapUpdateACKThread();
        thread.start();

        while (true)
        {
            checkAddresses();

            if (mobilityHappend = true) {
                Iterator<SecureFlexIDSession> iter = core.getSessionList().iterator();
                SecureFlexIDSession secureFlexIDSession;

                while (iter.hasNext()) {
                    FlexID sID;
                    String mapUpdateID;
                    secureFlexIDSession = iter.next();
                    sID = secureFlexIDSession.getFlexIDSession().getSFID();
                    if (sID.getLocator().getAddr().equals(prev)) {
                        sID.getLocator().setAddr(curr);
                        Message msg = new MapUpdateMessage(core.getDeviceID());
                        msg.addAttrValuePair("locatorType", sID.getLocator().getType().toString());
                        msg.addAttrValuePair("prevLocator", prev);
                        msg.addAttrValuePair("nextLocator", curr);
                        mapUpdateIDList.add(msg.getValueByAttr("mapUpdateID"));
                        msg.send(core.getBroker());
                    }
                }
                mobilityHappend = false;
            }
        }
    }

    void checkAddresses() {
        //java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: checkAddresses()");

        try {
            String key, addr;
            Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface.getNetworkInterfaces();

            for (NetworkInterface networkInterface : Collections.list(enumNetworkInterfaces)) {
                key = networkInterface.getName();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

                for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                    addr = inetAddress.toString();
                    if (addr.contains(".")) {
                        if (interfaceIPAddr.containsKey(key)) {
                            if (addr.equals(interfaceIPAddr.get(key)) == false) {
                                mobilityHappend = true;
                                prev = interfaceIPAddr.get(key);
                                curr = addr;
                                interfaceIPAddr.remove(key);
                                interfaceIPAddr.put(key, addr);
                            }
                        } else {
                            interfaceIPAddr.put(key, addr);
                        }
                        // java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "key: " + key + " / InetAddress: " + addr);
                    }
                }
            }

        } catch (SocketException e) {
            e.printStackTrace();
        }

        //java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: checkAddresses()");
    }

    class MapUpdateACKThread extends Thread {
        @Override
        public void run() {
            Message msg;
            String mapUpdateID;
            while (true) {
                // Get the received message from the queue
                msg = core.getReceivedMessage(MessageType.MAP_UPDATE_ACK.getTopic());

                // Process the received message if any
                if (msg != null) {
                    java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: Received MAP_UPDATE_ACK");
                    mapUpdateID = msg.getValueByAttr("mapUpdateID");
                    if (mapUpdateIDList.contains(mapUpdateID)) {

                    }
                    java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: Received MAP_UPDATE_ACK");
                }
            }
        }
    }
}