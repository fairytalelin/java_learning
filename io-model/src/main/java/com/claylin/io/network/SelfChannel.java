package com.claylin.io.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.security.Principal;

public class SelfChannel {
    private final String id;

    private final TransportLayer transportLayer;

    private final Authenticator authenticator;

    private final int maxReceiveSize;

    private NetworkReceive receive; // read bytes from SocketChannel

    private Send send;

    public SelfChannel(String id, TransportLayer transportLayer, Authenticator authenticator, int maxReceiveSize) {
        this.id = id;
        this.transportLayer = transportLayer;
        this.authenticator = authenticator;
        this.maxReceiveSize = maxReceiveSize;
    }


    public void close() {

    }

    public Principal principal() throws IOException {
        return null;
    }

    public void prepare() throws IOException {
        if (!transportLayer.ready()) {
            transportLayer.handshake();
        }

        if (transportLayer.ready() && !authenticator.complete()) {
            authenticator.authenticate();
        }
    }

    public void disconnect() {
        transportLayer.disconnect();
    }

    /*public boolean finishConnect() {
        return transportLayer.fishConnect();
    }*/

    public boolean isConnected() {
        return transportLayer.isConnected();
    }

    public String id() {
        return id;
    }

    public void mute() {
        transportLayer.removeInterestOps(SelectionKey.OP_READ);
    }

    public void unmute() {
        transportLayer.addInterestOps(SelectionKey.OP_READ);
    }

    public boolean isMute() {
        return transportLayer.isMute();
    }

    public boolean ready() {
        return transportLayer.ready() && authenticator.complete();
    }

    public boolean hasSend() {
        return send != null;
    }

    public InetAddress socketAddress() {
        return transportLayer.socketChannel().socket().getInetAddress();
    }

    public String socketDescription() {
        Socket socket = transportLayer.socketChannel().socket();
        if (socket.getInetAddress() == null) {
            return socket.getLocalAddress().toString();
        }

        return socket.getInetAddress().toString();
    }

    public void setSend(Send send) throws Exception {
        if (this.send != null) {
            throw new Exception();
        }

        this.send = send;
        this.transportLayer.addInterestOps(SelectionKey.OP_WRITE);
    }

    public NetworkReceive read() throws IOException {
        NetworkReceive result = null;

        if (receive == null) {
            receive = new NetworkReceive(maxReceiveSize, id);
        }
        receive(receive);

        if (receive.complete()) {
            receive.payload().rewind();
            result = receive;
            receive = null;
        }

        return result;
    }

    public Send write() throws IOException {
        Send result = null;
        if (send != null && send(send)) {
            result = send;
            send = null;
        }
        return result;
    }

    private long receive(NetworkReceive receive) throws IOException {
        return receive.readFrom(transportLayer);
    }

    private boolean send(Send send) throws IOException {
        send.writeTo(transportLayer);
        if (send.completed()) {
            transportLayer.removeInterestOps(SelectionKey.OP_WRITE);
        }

        return send.completed();
    }
}
