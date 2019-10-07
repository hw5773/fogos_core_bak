package FogOSSecurity;

import FogOSSocket.FlexIDSession;

public class ProtocolManager {
    protected SecurityParameters securityParameters;
    protected FlexIDSession flexIDSession;

    public ProtocolManager(SecurityParameters securityParameters, FlexIDSession flexIDSession) {
        this.securityParameters = securityParameters;
        this.flexIDSession = flexIDSession;
    }

    static String byteArrayToHex(byte[] a, int len) {
        if (len < 0)
            len = a.length;
        byte[] tmp = new byte[len];
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        System.arraycopy(a, 0, tmp, 0, len);

        for (final byte b: tmp) {
            sb.append(String.format("0x%02x, ", b & 0xff));
            idx++;
            if (idx % 8 == 0)
                sb.append("\n");
        }
        return sb.toString();
    }
}
