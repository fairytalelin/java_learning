package com.claylin.io.network;

import java.nio.ByteBuffer;

public class NetworkSend extends ByteBufferSend {
    public NetworkSend(String destination, ByteBuffer... buffers) {
        super(destination, buffers);
    }

    private static ByteBuffer[] sizeDelimit(ByteBuffer[] byteBuffers) {
        int size = 0;
        for (int i = 0; i < byteBuffers.length; i++) {
            size += byteBuffers[i].remaining();
        }
        ByteBuffer[] delimited = new ByteBuffer[byteBuffers.length + 1];
        delimited[0] = ByteBuffer.allocate(4);
        delimited[0].putInt(size);
        delimited[0].rewind();
        System.arraycopy(byteBuffers, 0, delimited, 1, byteBuffers.length);
        return delimited;
    }
}
