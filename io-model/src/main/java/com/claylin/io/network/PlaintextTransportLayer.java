package com.claylin.io.network;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.security.Principal;

/*
 Transport layer for Plaintext communication
 */

public class PlaintextTransportLayer implements TransportLayer {

    private final SelectionKey key;

    private final SocketChannel socketChannel;

    public PlaintextTransportLayer(SelectionKey key, SocketChannel socketChannel) {
        this.key = key;
        this.socketChannel = socketChannel;
    }

    @Override
    public boolean ready() {
        return true;
    }

    @Override
    public boolean fishConnect() throws IOException {
        boolean connected = socketChannel.finishConnect();

        if (connected) {
            key.interestOps((key.interestOps() & ~SelectionKey.OP_CONNECT | SelectionKey.OP_READ));
        }

        return connected;
    }

    @Override
    public void disconnect() {
        key.cancel();
    }

    @Override
    public boolean isConnected() {
        return socketChannel.isConnected();
    }

    @Override
    public SocketChannel socketChannel() {
        return socketChannel;
    }

    /**
     * Performs SSL handshake hence is a no-op for the non-secure implementation
     */
    @Override
    public void handshake() {

    }

    /**
     * always returns false as there will be not any pending writes since we directly write to
     * socketChannel
     *
     * @return
     */
    @Override
    public boolean hasPendingWrites() {
        return false;
    }

    @Override
    public Principal peerPrincipal() {
        return null;
    }

    @Override
    public void addInterestOps(int ops) {
        key.interestOps(key.interestOps() | ops);
    }

    @Override
    public void removeInterestOps(int ops) {
        key.interestOps(key.interestOps() & ~ops);
    }

    @Override
    public boolean isMute() {
        return key.isValid() && (key.interestOps() & SelectionKey.OP_READ) == 0;
    }

    @Override
    public long transferFrom(FileChannel fileChannel, long position, long count) throws IOException {
        return fileChannel.transferTo(position, count, socketChannel);
    }

    @Override
    public long write(ByteBuffer[] srcs, int offset, int length) throws IOException {
        return socketChannel.write(srcs, offset, length);
    }

    @Override
    public long write(ByteBuffer[] srcs) throws IOException {
        return socketChannel.write(srcs);
    }

    @Override
    public long read(ByteBuffer[] dsts, int offset, int length) throws IOException {
        return socketChannel.read(dsts, offset, length);
    }

    @Override
    public long read(ByteBuffer[] dsts) throws IOException {
        return socketChannel.read(dsts);
    }

    @Override
    public int read(ByteBuffer dst) throws IOException {
        return socketChannel.read(dst);
    }

    @Override
    public int write(ByteBuffer src) throws IOException {
        return socketChannel.write(src);
    }

    @Override
    public boolean isOpen() {
        return socketChannel.isOpen();
    }

    @Override
    public void close() throws IOException {
        try {
            socketChannel.socket().close();
            socketChannel.close();
        } finally {
            key.attach(null);
            key.cancel();
        }
    }
}
