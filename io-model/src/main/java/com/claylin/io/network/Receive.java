package com.claylin.io.network;

import java.io.IOException;
import java.nio.channels.ScatteringByteChannel;

public interface Receive {
    String source();

    boolean complete();

    long readFrom(ScatteringByteChannel channel) throws IOException;
}
