package com.claylin.grpc.proto.service;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.16.1)",
    comments = "Source: user.proto")
public final class UserviceGrpc {

  private UserviceGrpc() {}

  public static final String SERVICE_NAME = "Uservice";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.claylin.grpc.proto.entity.User,
      com.claylin.grpc.proto.entity.RetMsg> getAddMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "add",
      requestType = com.claylin.grpc.proto.entity.User.class,
      responseType = com.claylin.grpc.proto.entity.RetMsg.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.claylin.grpc.proto.entity.User,
      com.claylin.grpc.proto.entity.RetMsg> getAddMethod() {
    io.grpc.MethodDescriptor<com.claylin.grpc.proto.entity.User, com.claylin.grpc.proto.entity.RetMsg> getAddMethod;
    if ((getAddMethod = UserviceGrpc.getAddMethod) == null) {
      synchronized (UserviceGrpc.class) {
        if ((getAddMethod = UserviceGrpc.getAddMethod) == null) {
          UserviceGrpc.getAddMethod = getAddMethod = 
              io.grpc.MethodDescriptor.<com.claylin.grpc.proto.entity.User, com.claylin.grpc.proto.entity.RetMsg>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Uservice", "add"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.claylin.grpc.proto.entity.User.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.claylin.grpc.proto.entity.RetMsg.getDefaultInstance()))
                  .setSchemaDescriptor(new UserviceMethodDescriptorSupplier("add"))
                  .build();
          }
        }
     }
     return getAddMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.claylin.grpc.proto.entity.GetByUidReq,
      com.claylin.grpc.proto.entity.RetMsg> getGetByUidMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getByUid",
      requestType = com.claylin.grpc.proto.entity.GetByUidReq.class,
      responseType = com.claylin.grpc.proto.entity.RetMsg.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.claylin.grpc.proto.entity.GetByUidReq,
      com.claylin.grpc.proto.entity.RetMsg> getGetByUidMethod() {
    io.grpc.MethodDescriptor<com.claylin.grpc.proto.entity.GetByUidReq, com.claylin.grpc.proto.entity.RetMsg> getGetByUidMethod;
    if ((getGetByUidMethod = UserviceGrpc.getGetByUidMethod) == null) {
      synchronized (UserviceGrpc.class) {
        if ((getGetByUidMethod = UserviceGrpc.getGetByUidMethod) == null) {
          UserviceGrpc.getGetByUidMethod = getGetByUidMethod = 
              io.grpc.MethodDescriptor.<com.claylin.grpc.proto.entity.GetByUidReq, com.claylin.grpc.proto.entity.RetMsg>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Uservice", "getByUid"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.claylin.grpc.proto.entity.GetByUidReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.claylin.grpc.proto.entity.RetMsg.getDefaultInstance()))
                  .setSchemaDescriptor(new UserviceMethodDescriptorSupplier("getByUid"))
                  .build();
          }
        }
     }
     return getGetByUidMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserviceStub newStub(io.grpc.Channel channel) {
    return new UserviceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserviceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new UserviceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserviceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new UserviceFutureStub(channel);
  }

  /**
   */
  public static abstract class UserviceImplBase implements io.grpc.BindableService {

    /**
     */
    public void add(com.claylin.grpc.proto.entity.User request,
        io.grpc.stub.StreamObserver<com.claylin.grpc.proto.entity.RetMsg> responseObserver) {
      asyncUnimplementedUnaryCall(getAddMethod(), responseObserver);
    }

    /**
     */
    public void getByUid(com.claylin.grpc.proto.entity.GetByUidReq request,
        io.grpc.stub.StreamObserver<com.claylin.grpc.proto.entity.RetMsg> responseObserver) {
      asyncUnimplementedUnaryCall(getGetByUidMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.claylin.grpc.proto.entity.User,
                com.claylin.grpc.proto.entity.RetMsg>(
                  this, METHODID_ADD)))
          .addMethod(
            getGetByUidMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.claylin.grpc.proto.entity.GetByUidReq,
                com.claylin.grpc.proto.entity.RetMsg>(
                  this, METHODID_GET_BY_UID)))
          .build();
    }
  }

  /**
   */
  public static final class UserviceStub extends io.grpc.stub.AbstractStub<UserviceStub> {
    private UserviceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserviceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserviceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserviceStub(channel, callOptions);
    }

    /**
     */
    public void add(com.claylin.grpc.proto.entity.User request,
        io.grpc.stub.StreamObserver<com.claylin.grpc.proto.entity.RetMsg> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getByUid(com.claylin.grpc.proto.entity.GetByUidReq request,
        io.grpc.stub.StreamObserver<com.claylin.grpc.proto.entity.RetMsg> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetByUidMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class UserviceBlockingStub extends io.grpc.stub.AbstractStub<UserviceBlockingStub> {
    private UserviceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserviceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserviceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserviceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.claylin.grpc.proto.entity.RetMsg add(com.claylin.grpc.proto.entity.User request) {
      return blockingUnaryCall(
          getChannel(), getAddMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.claylin.grpc.proto.entity.RetMsg getByUid(com.claylin.grpc.proto.entity.GetByUidReq request) {
      return blockingUnaryCall(
          getChannel(), getGetByUidMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UserviceFutureStub extends io.grpc.stub.AbstractStub<UserviceFutureStub> {
    private UserviceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserviceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserviceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserviceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.claylin.grpc.proto.entity.RetMsg> add(
        com.claylin.grpc.proto.entity.User request) {
      return futureUnaryCall(
          getChannel().newCall(getAddMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.claylin.grpc.proto.entity.RetMsg> getByUid(
        com.claylin.grpc.proto.entity.GetByUidReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetByUidMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD = 0;
  private static final int METHODID_GET_BY_UID = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserviceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UserviceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD:
          serviceImpl.add((com.claylin.grpc.proto.entity.User) request,
              (io.grpc.stub.StreamObserver<com.claylin.grpc.proto.entity.RetMsg>) responseObserver);
          break;
        case METHODID_GET_BY_UID:
          serviceImpl.getByUid((com.claylin.grpc.proto.entity.GetByUidReq) request,
              (io.grpc.stub.StreamObserver<com.claylin.grpc.proto.entity.RetMsg>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class UserviceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UserviceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.claylin.grpc.proto.service.UserProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Uservice");
    }
  }

  private static final class UserviceFileDescriptorSupplier
      extends UserviceBaseDescriptorSupplier {
    UserviceFileDescriptorSupplier() {}
  }

  private static final class UserviceMethodDescriptorSupplier
      extends UserviceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UserviceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UserviceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserviceFileDescriptorSupplier())
              .addMethod(getAddMethod())
              .addMethod(getGetByUidMethod())
              .build();
        }
      }
    }
    return result;
  }
}
