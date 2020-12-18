/**
 * Tapp
 * Copyright (c) 20042020 All Rights Reserved.
 */
package service.product;

import AWS.ALBGrpc;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

/**
 * @author faisalrahman
 * @version $Id: AlbServiceImpl.java, v 0.1 20201218 17.01 faisalrahman Exp $$
 */
public class AlbServiceImpl extends ALBGrpc.ALBImplBase {

    public void healthcheck(Empty request, StreamObserver<Empty> responseObserver) {

        Empty reply = Empty.newBuilder().build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}