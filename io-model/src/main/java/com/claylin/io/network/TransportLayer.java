package com.claylin.io.network;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.channels.SocketChannel;
import java.security.Principal;

public interface TransportLayer extends ScatteringByteChannel, GatheringByteChannel {

    boolean ready();

    boolean fishConnect() throws IOException;

    void disconnect();

    boolean isConnected();

    SocketChannel socketChannel();

    void handshake();

    boolean hasPendingWrites();

    Principal peerPrincipal();

    void addInterestOps(int ops);

    void removeInterestOps(int ops);

    boolean isMute();

    long transferFrom(FileChannel fileChannel, long position, long count) throws IOException;
}
