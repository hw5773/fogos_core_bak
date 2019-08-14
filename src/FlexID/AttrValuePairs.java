package FlexID;

import java.util.Hashtable;

public class AttrValuePairs {
    int numberOfAVPs;
    Hashtable<String, String> table;

    public AttrValuePairs() {
        numberOfAVPs = 0;
        table = new Hashtable<String, String>();
    }

    public void addAttrValuePair(String attr, String value) {
        table.put(attr, value);
    }

    public String getValueByAttr(String attr) {
        return table.get(attr);
    }

    public int getNumberOfAVPs() {
        return numberOfAVPs;
    }

    public Hashtable<String, String> getTable() {
        return table;
    }
}
