package com.claylin.grpc;

import com.claylin.grpc.proto.UserServer;
import com.google.common.util.concurrent.UncaughtExceptionHandlers;
import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import io.grpc.netty.shaded.io.netty.channel.EventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.nio.NioEventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.socket.nio.NioServerSocketChannel;
import io.grpc.netty.shaded.io.netty.util.concurrent.DefaultThreadFactory;
import io.grpc.services.ChannelzService;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class App {
    public static void main(String[] args) throws Exception {

        UserServer userServer = new UserServer();
        userServer.start();


        System.out.printf("=================== user server start ==============================");


        ThreadFactory tf = new DefaultThreadFactory("server-elg-for-master", true /*daemon */);
        EventLoopGroup boss = new NioEventLoopGroup(1, tf);
        EventLoopGroup worker = new NioEventLoopGroup(0, tf);

        ChannelzService channelzService = ChannelzService.newInstance(20);

        ForkJoinPool.ForkJoinWorkerThreadFactory forkJoinWorkerThreadFactory = new ForkJoinPool.ForkJoinWorkerThreadFactory() {
            final AtomicInteger num = new AtomicInteger();

            @Override
            public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
                ForkJoinWorkerThread thread =
                        ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(pool);
                thread.setDaemon(true);
                thread.setName("grpc-server-app-for-master" + "-" + num.getAndIncrement());
                return thread;
            }
        };

        Server masterServer = NettyServerBuilder.forPort(10026)
                .bossEventLoopGroup(boss)
                .workerEventLoopGroup(worker)
                .channelType(NioServerSocketChannel.class)
                .executor(new ForkJoinPool(Runtime.getRuntime().availableProcessors(),
                        forkJoinWorkerThreadFactory, UncaughtExceptionHandlers.systemExit(),
                        true /* async */))
                .addService(channelzService).build();
        masterServer.start();


        System.out.printf("=================== channelz server start ==============================");

        Thread.currentThread().join();
    }
}
