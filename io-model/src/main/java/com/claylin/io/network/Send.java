package com.claylin.io.network;

import java.io.IOException;
import java.nio.channels.GatheringByteChannel;

public interface Send {

    String destination();

    boolean completed();

    long writeTo(GatheringByteChannel channel) throws IOException;

    long size();
}
