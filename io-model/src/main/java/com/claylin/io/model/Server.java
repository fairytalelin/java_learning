package com.claylin.io.model;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Set;

public class Server {
    public static void main(String[] args) throws Exception {

        Selector selector = Selector.open();

        SocketAddress address = new InetSocketAddress(41005);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(address);
        serverSocketChannel.configureBlocking(false);
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        selectionKey.channel();

        Set<SocketChannel> clientChannelSet = new HashSet<>();

        selector.selectedKeys();

        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey key : selectionKeys) {
                if (key.isAcceptable()) {
                    register(selector, (ServerSocketChannel) key.channel());
                }

                if (key.isReadable()) {

                }
            }
        }
    }

    private static void register(Selector selector, ServerSocketChannel serverSocket) throws Exception {
        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }

    private static void answerWithEcho(ByteBuffer buffer, SelectionKey key) throws Exception {
        SocketChannel client = (SocketChannel) key.channel();
        client.read(buffer);
        if (new String(buffer.array()).trim().equals("end")) {
            client.close();
            System.out.println("close");
        }

        buffer.flip();
        client.write(buffer);
        buffer.clear();
    }
}
