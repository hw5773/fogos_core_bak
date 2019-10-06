package FogOSSocket;

import java.net.*;
import java.util.Enumeration;
import java.util.logging.Level;

public class FlexIDServerSocket {
    private ServerSocket server;
    private FlexIDSocket socket;
    private static final String TAG = "FogOSSocket";

    FlexIDServerSocket() {
        try {
            server = new ServerSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    FlexIDServerSocket(int port) {
        try {
            server = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    FlexIDSocket accept() {
        try {
            java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: FlexIDSockst accept: port: " + server.getLocalPort());
            Socket sock = server.accept();
            socket = new FlexIDSocket(sock);
            System.out.println("Accept success.");
            return socket;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    void bind(SocketAddress bindpoint) {
        try {
            server.bind(bindpoint);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    void close() {
        try {
            server.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String getInetAddress() {
        return server.getInetAddress().getHostAddress();
    }

    public int getPort() {
        return server.getLocalPort();
    }
}
