package FogOSSocket;

import java.util.ArrayList;

public class SessionLogger {

    ArrayList<LogItem> items;
    int lastRetrieved;

    public SessionLogger() {
        this.items = new ArrayList<>();
        this.lastRetrieved = 0;
    }

    public void addSessionLog(LogType type, double value, String from, String to)
    {
        if (type == LogType.REBINDING) {
            System.out.println("[SessionLogger] REBINDING log is added: value: " + value + " / From: " + from + " / To: " + to);
        } else if (type == LogType.DATA) {
            System.out.println("[SessionLogger] DATA log is added: value: " + value + " / From: " + from + " / To: " + to);
        }
        items.add(new LogItem(type, value, from, to));
    }

    public int getNumOfLog() {
        LogItem item = null;
        int ret = 0;

        for (int i=0; i<items.size(); i++) {
            item = items.get(i);
            if (item.isRetrieved() == false) {
                ret++;
            }
        }
        return ret;
    }

    public LogItem getSessionLog() {
        LogItem item = null;
        for (int i=0; i<items.size(); i++) {
            item = items.get(i);
            if (item.isRetrieved() == false) {
                item.retrieved();
                return item;
            }
        }
        return null;
    }
}

