package com.claylin.io.network;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ScatteringByteChannel;

public class NetworkReceive implements Receive {

    public final static String UNKNOWN_SOURCE = "";
    public static final int UNLIMITED = -1;

    private final String source;
    private final ByteBuffer size;
    private final int maxSize;
    private ByteBuffer buffer;

    public NetworkReceive(String source, ByteBuffer buffer) {
        this.source = source;
        this.buffer = buffer;
        this.size = null;
        this.maxSize = UNLIMITED;
    }

    public NetworkReceive(String source) {
        this.source = source;
        this.size = ByteBuffer.allocate(4);
        this.buffer = null;
        this.maxSize = UNLIMITED;
    }

    public NetworkReceive(int maxSize, String source) {
        this.source = source;
        this.maxSize = maxSize;
        this.buffer = null;
        this.size = ByteBuffer.allocate(4);
    }

    public NetworkReceive() {
        this(UNKNOWN_SOURCE);
    }

    @Override
    public String source() {
        return source;
    }

    @Override
    public boolean complete() {
        return !size.hasRemaining() && !buffer.hasRemaining();
    }

    @Override
    public long readFrom(ScatteringByteChannel channel) throws IOException {
        int read = 0;
        if (size.hasRemaining()) { // 一直读取大小
            int byteRead = channel.read(size);
            if (byteRead < 0) {
                throw new EOFException();
            }
            read += byteRead;

            if (!size.hasRemaining()) {
                size.rewind();
                int receiveSize = size.getInt();
                if (receiveSize < 0) {
                    throw new IOException();
                }
                if (maxSize != UNLIMITED & receiveSize > maxSize) {
                    throw new IOException();
                }

                this.buffer = ByteBuffer.allocate(receiveSize);
            }
        }

        if (buffer != null) {
            int byteRead = channel.read(buffer);
            if (byteRead < 0) {
                throw new EOFException();
            }
            read += byteRead;
        }
        return read;
    }

    public ByteBuffer payload() {
        return this.buffer;
    }
}
