package com.claylin.grpc.proto.service.impl;

import com.claylin.grpc.proto.entity.GetByUidReq;
import com.claylin.grpc.proto.entity.RetMsg;
import com.claylin.grpc.proto.entity.User;
import com.claylin.grpc.proto.service.UserviceGrpc;
import io.grpc.stub.StreamObserver;

public class UserServiceImpl extends UserviceGrpc.UserviceImplBase {
    @Override
    public void add(User request, StreamObserver<RetMsg> responseObserver) {
        responseObserver.onNext(RetMsg.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void getByUid(GetByUidReq request, StreamObserver<RetMsg> responseObserver) {
        responseObserver.onNext(RetMsg.getDefaultInstance());
        responseObserver.onCompleted();
    }
}
