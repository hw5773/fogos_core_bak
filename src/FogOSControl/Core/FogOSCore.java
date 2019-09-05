package FogOSControl.Core;

import FogOSMessage.*;
import FlexID.FlexID;
import FlexID.Locator;
import FlexID.InterfaceType;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FogOSCore {
    private final String cloudName = "www.versatile-cloud.com";
    private final int cloudPort = 3333;
    private LinkedList<FogOSBroker> brokers;
    private FogOSBroker broker = new FogOSBroker("147.46.219.79");
    private FlexID deviceID;
    private ContentStore store;
    private MqttClient mqttClient;
    private static final String TAG = "FogOSCore";

    public FogOSCore() {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: Initialize FogOSCore");

        retrieveBrokerList();
        broker = findBestFogOSBroker();
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Result: findBestFogOSBroker() " + broker.getName());
        store = new ContentStore();

        deviceID = FlexID.generateDeviceID();
        initSubscribe(deviceID);
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: Initialize FogOSCore");
    }

    // Access to the cloud to get the list of the FogOS brokers
    void retrieveBrokerList() {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: retrieveBrokerList()");
        // TODO: Implement this function
        // request the list to the cloud
        // parse the response and add brokers to "brokers"
        NetworkThread thread = new NetworkThread();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: retrieveBrokerList()");
    }

    // Ping test and select the best FogOS broker
    FogOSBroker findBestFogOSBroker()
    {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: findBestFogOSBroker()");

        Double rtt1 = 0.0;
        Double rtt2 = 0.0;
        try {
            Process process = Runtime.getRuntime().exec("/system/bin/ping -c 3 " + brokers.get(0).getName());
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;

            while((line = reader.readLine()) != null){
                builder.append(line);
            }
            String result1 = builder.toString();

            process = Runtime.getRuntime().exec("/system/bin/ping -c 3 " + brokers.get(1).getName());
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            builder = new StringBuilder();

            while((line = reader.readLine()) != null){
                builder.append(line);
            }
            String result2 = builder.toString();

            rtt1 = getPingStats(result1);
            rtt2 = getPingStats(result2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: findBestFogOSBroker()");

        if (rtt1 <= rtt2)
            return brokers.get(0);
        else
            return brokers.get(1);
    }

    class NetworkThread extends Thread{

        @Override
        public void run() {
            brokers = new LinkedList<FogOSBroker>();
            try {
                URL url = new URL("http://" + cloudName + ":" + cloudPort + "/brokers");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                if(conn.getResponseCode() == 200){
                    InputStream stream = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "utf-8"));
                    StringBuilder builder = new StringBuilder();
                    String line;

                    while((line = reader.readLine()) != null){
                        builder.append(line);
                    }

                    JSONObject object = new JSONObject(builder.toString());
                    if(object.length() != 0){
                        JSONArray array = object.getJSONArray("brokers");

                        for(int i = 0; i < array.length(); i++){
                            String name = array.getJSONObject(i).getString("name");
                            brokers.add(new FogOSBroker(name));
                        }
                    }
                }
            } catch (MalformedURLException | ProtocolException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    Double getPingStats(String result){
        Double rtt = 1000.0;

        if(result.contains("unknown host") || result.contains("100% packet loss")){

        } else if(result.contains("% packet loss")){
            int start = result.indexOf("rtt min/avg/max/mdev");
            int end = result.indexOf("ms", start);
            String stats = result.substring(start + 23, end);
            rtt = Double.parseDouble(stats.split("/")[1]);
        } else{

        }

        return rtt;
    }

    // Initialize subscriptions with the selected broker
    void initSubscribe(FlexID deviceID) {
        Logger.getLogger(TAG).log(Level.INFO, "Start: initSubscribe()");

        deviceID = new FlexID("versatile");
        connect(deviceID);

        subscribe(MessageType.JOIN_ACK.getTopicWithDeviceID(deviceID));
        subscribe(MessageType.LEAVE_ACK.getTopicWithDeviceID(deviceID));
        subscribe(MessageType.STATUS_ACK.getTopicWithDeviceID(deviceID));
        subscribe(MessageType.REGISTER_ACK.getTopicWithDeviceID(deviceID));
        subscribe(MessageType.UPDATE_ACK.getTopicWithDeviceID(deviceID));
        subscribe(MessageType.MAP_UPDATE_ACK.getTopicWithDeviceID(deviceID));
        publish(MessageType.JOIN.getTopicWithDeviceID(deviceID), generateMessage(MessageType.JOIN));

        Logger.getLogger(TAG).log(Level.INFO, "Finish: initSubscribe()");
    }

    public Message generateMessage(MessageType messageType) {
        Message msg = null;
        switch (messageType) {
            case JOIN:
                msg = new JoinMessage(deviceID);
                try {
                    JSONArray uniqueCodes = new JSONArray("[{\"ifaceType\":\"wifi\",\"hwAddress\":\"00-1a-e9-8d-08-73\",\"ipv4\":\"143.248.30.13\",\"wifiSSID\":\"Welcome_KAIST\"},{ \"ifaceType\":\"lte\",\"hwAddress\":\"00:1a:e9:8d:08:74\",\"ipv4\":\"10.0.3.15\"}]");
                    JSONArray relay = new JSONArray("[\"fh2gj1g\", \"d3hsv5a35\"]");
                    JSONArray neighbors = new JSONArray("[{\"neighborIface\":\"wifi\", \"neighborIpv4\":\"10.0.0.42\", \"neighborFlexID\":\"asdf\"}, {\"neighborIface\":\"blue tooth\", \"neighborHwAddress\":\"00:11:22:33:aa:bb\", \"neighborFlexID\":\"asdf12\"}]");
                    String pubkey= "a32adf";
                    msg.addAttrValuePair("uniqueCodes", uniqueCodes.toString());
                    msg.addAttrValuePair("relay", relay.toString());
                    msg.addAttrValuePair("neighbors", neighbors.toString());
                    msg.addAttrValuePair("pubKey", pubkey);
                } catch (JSONException e) {
                e.printStackTrace();
                }
                break;
            case JOIN_ACK:
                msg = new JoinAckMessage(deviceID);
                break;
            case STATUS:

                break;
            case STATUS_ACK:
                break;
            case LEAVE:
                break;
            case LEAVE_ACK:
                break;
            case REGISTER:
                break;
            case REGISTER_ACK:
                break;
            case UPDATE:
                break;
            case UPDATE_ACK:
                break;
            case MAP_UPDATE:
                break;
            case MAP_UPDATE_ACK:
                break;
            case QUERY:
                msg = new QueryMessage(deviceID);
                break;
            case REPLY:
                msg = new ReplyMessage(deviceID);
                break;
            case REQUEST:
                msg = new RequestMessage(deviceID);
                break;
            case RESPONSE:
                msg = new ResponseMessage(deviceID);
                break;
        }

        return msg;
    }

    public void connect(FlexID deviceID){
        try {
            mqttClient = new MqttClient("tcp://" + broker.getName() + ":" + broker.getPort(), deviceID.getStringIdentity(), new MemoryPersistence());
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setCleanSession(true);
            mqttConnectOptions.setKeepAliveInterval(15);
            mqttConnectOptions.setConnectionTimeout(30);
            mqttClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {
                    Logger.getLogger(TAG).log(Level.INFO, "Mqtt: connectionLost");
                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    Logger.getLogger(TAG).log(Level.INFO, "Mqtt: messageArrived");
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    Logger.getLogger(TAG).log(Level.INFO, "Mqtt: deliveryComplete");
                }
            });

            mqttClient.connect(mqttConnectOptions);
        } catch (MqttSecurityException ex) {
            ex.printStackTrace();
        } catch (MqttException ex) {
            ex.printStackTrace();
        }
    }

    public void disconnect(){
        try {
            mqttClient.disconnect();
            Logger.getLogger(TAG).log(Level.INFO, "Mqtt: disconnect()");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(String topic){
        try {
            mqttClient.subscribe(topic, new IMqttMessageListener() {
                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    Logger.getLogger(TAG).log(Level.INFO, "Mqtt: messageArrived()");
                    Logger.getLogger(TAG).log(Level.INFO, "Mqtt: " + s + mqttMessage);
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String topic, Message msg){
        try {
            mqttClient.publish(topic, new MqttMessage(getStringFromHashTable(msg.getAttrValueTable()).getBytes()));
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public String getStringFromHashTable(Hashtable hashtable) {
        JSONObject jsonObject = new JSONObject(hashtable);
        return jsonObject.toString();
    }

    public Message sendMessage(Message msg) {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: sendMessage(): MessageType: " + msg.getMessageType().toString());

        /* Test Returns */
        if (msg.getMessageType() == MessageType.QUERY && msg.getValueByAttr("keywords").equals("test")) {
            java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Make a test list started.");
            ReplyMessage replyMessage;
            FlexID id;
            replyMessage = (ReplyMessage) generateMessage(MessageType.REPLY);
            id = new FlexID("test");
            java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "id 1: " + id + " / ID 1: " + new String(id.getIdentity()));
            replyMessage.addReplyEntry("환호하는 손흥민", "손흥민이 한독전에서 골을 넣고 환호하고 있다.", id);
            id = new FlexID("test");
            java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "id 2: " + id + " / ID 2: " + new String(id.getIdentity()));
            replyMessage.addReplyEntry("기뻐하는 김영권 영상", "온국민을 환호하게 만든 김영권의 첫골을 다시보자.", id);
            id = new FlexID("test");
            java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "id 3: " + id + " / ID 3: " + new String(id.getIdentity()));
            replyMessage.addReplyEntry("좌절에 빠진 독일 팬들", "예기치 못한 패배에 독일 팬들은 모두 울상을 짓고 있다.", id);
            java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Make a test list finished.");
            java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: sendMessage()");
            return replyMessage;
        } else if (msg.getMessageType() == MessageType.REQUEST) {
            RequestMessage requestMessage;
            requestMessage = (RequestMessage) msg;
            java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Make a test response message started.");
            java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Peer ID: " + new String(requestMessage.getPeerID().getIdentity()));
            ResponseMessage responseMessage;
            Locator locator = new Locator(InterfaceType.WIFI, "192.168.0.128", 3333);
            responseMessage = (ResponseMessage) generateMessage(MessageType.RESPONSE);
            responseMessage.setPeerID(new FlexID(msg.getValueByAttr("id")));
            responseMessage.getPeerID().setLocator(locator);

            java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Make a test response message finished.");
            return responseMessage;
        }
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: sendMessage()");
        return msg.send(broker, deviceID);
    }
}
