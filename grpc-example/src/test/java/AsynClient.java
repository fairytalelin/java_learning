import com.claylin.grpc.proto.entity.GetByUidReq;
import com.claylin.grpc.proto.entity.RetMsg;
import com.claylin.grpc.proto.service.UserviceGrpc;
import com.google.common.collect.Lists;
import com.google.common.math.LongMath;
import io.grpc.ManagedChannel;
import io.grpc.Status;
import io.grpc.netty.shaded.io.grpc.netty.NegotiationType;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.shaded.io.netty.channel.nio.NioEventLoopGroup;
import io.grpc.netty.shaded.io.netty.util.concurrent.DefaultThreadFactory;
import io.grpc.stub.StreamObserver;

import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by lwc on 2018/10/12.
 */
public class AsynClient {

    private static final String serverIP = "localhost";
    //private static final String serverIP = "localhost";
    private static final int serverPort = 10027;

    // The histogram can record values between 1 microsecond and 1 min.
    public static final long HISTOGRAM_MAX_VALUE = 60000000L;

    // Value quantization will be no more than 1%. See the README of HdrHistogram for more details.
    public static final int HISTOGRAM_PRECISION = 2;

    public static final long duration = 60;

    public static void main(String[] args) throws Exception {

        /*final String ip = args[0];
        final int channelCount = Integer.parseInt(args[1]);
        final int maxConcurrentStreams = Integer.parseInt(args[2]);*/

        final String ip = serverIP;
        final int channelCount = 6;
        final int maxConcurrentStreams = 10;

        final CountDownLatch countDownLatch = new CountDownLatch(channelCount);

        final AtomicLong counter = new AtomicLong();
        final long counterDelta = 1;
        ManagedChannel[] channels;
        NettyChannelBuilder channelBuilder = NettyChannelBuilder.forAddress(ip, serverPort);
        channelBuilder.directExecutor()
                .negotiationType(NegotiationType.PLAINTEXT).keepAliveTime(1, TimeUnit.SECONDS).keepAliveTimeout(10, TimeUnit.SECONDS);

        channels = new ManagedChannel[channelCount];

        ThreadFactory clientThreadFactory = new DefaultThreadFactory("CTF pool", true /* daemon */);

        for (int i = 0; i < channelCount; i++) {
            // Use a dedicated event-loop for each channel
            channels[i] = channelBuilder
                    .eventLoopGroup(new NioEventLoopGroup(1, clientThreadFactory))
                    .build();
        }

        final GetByUidReq getFreeVoiceRoomReq = GetByUidReq.newBuilder().setUid(1111L).build();

        List<Future<RetMsg>> futures = Lists.newArrayListWithExpectedSize(maxConcurrentStreams);

        long startTime = System.nanoTime();
        final long endTime = startTime + TimeUnit.SECONDS.toNanos(duration);

        for (final ManagedChannel channel : channels) {
            for (int i = 0; i < maxConcurrentStreams; i++) {
                final UserviceGrpc.UserviceStub stub = UserviceGrpc.newStub(channel);

                // final HistogramFuture future = new HistogramFuture();

                stub.getByUid(getFreeVoiceRoomReq, new StreamObserver<RetMsg>() {

                    public void onNext(RetMsg value) {
                        counter.addAndGet(counterDelta);
                    }

                    public void onError(Throwable t) {
                        Status status = Status.fromThrowable(t);
                        System.err.println("Encountered an error in unaryCall. Status is " + status);
                        t.printStackTrace();

                        countDownLatch.countDown();
                    }

                    public void onCompleted() {
                        long now = System.nanoTime();
                        // Record the latencies in microseconds
                        if (endTime > now) {
                            stub.getByUid(getFreeVoiceRoomReq, this);
                        } else {
                            //future.done();
                            countDownLatch.countDown();
                        }
                    }
                });
                //futures.add(future);
            }
        }

        countDownLatch.await();

        // Wait for completion
        /*for (Future<RetMsg> future : futures) {
            future.get();
        }*/
        long tps = LongMath.divide(counter.get(), duration, RoundingMode.HALF_EVEN);
        System.out.println(tps);
    }

    private static class HistogramFuture implements Future<RetMsg> {
        private RetMsg retMsg;
        private boolean canceled;
        private boolean done;

        HistogramFuture() {
        }

        public synchronized boolean cancel(boolean mayInterruptIfRunning) {
            if (!done && !canceled) {
                canceled = true;
                notifyAll();
                return true;
            }
            return false;
        }

        public RetMsg getRetMsg() {
            return retMsg;
        }

        public HistogramFuture setRetMsg(RetMsg retMsg) {
            this.retMsg = retMsg;
            return this;
        }

        public synchronized boolean isCancelled() {
            return canceled;
        }

        public synchronized boolean isDone() {
            return done || canceled;
        }

        public synchronized RetMsg get() throws InterruptedException, ExecutionException {
            while (!isDone() && !isCancelled()) {
                wait();
            }

            if (isCancelled()) {
                throw new CancellationException();
            }
            return retMsg;
        }

        public RetMsg get(long timeout, TimeUnit unit) throws InterruptedException,
                ExecutionException,
                TimeoutException {
            throw new UnsupportedOperationException();
        }

        private synchronized void done() {
            done = true;
            notifyAll();
        }
    }
}
