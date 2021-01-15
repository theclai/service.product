/**
 * Tapp
 * Copyright (c) 20042020 All Rights Reserved.
 */
package service.product;

import AWS.ALBGrpc;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.xml.crypto.Data;

import static org.junit.Assert.assertEquals;

/**
 * @author faisalrahman
 * @version $Id: AlbServiceServerTest.java, v 0.1 20201218 19.46 faisalrahman Exp $$
 */
@RunWith(JUnit4.class)
public class AlbServiceServerTest {

    /**
     * This rule manages automatic graceful shutdown for the registered servers and channels at the
     * end of test.
     */
    @Rule
    public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();
    private ProductServiceServer productServiceServer;
    private ManagedChannel inProcessChannel;

    @Before
    public void Setup() throws Exception {

        // Generate a unique in-process server name.
        String serverName = InProcessServerBuilder.generateName();
        DatabaseParams databaseParams = new DatabaseParams();

        productServiceServer = new ProductServiceServer(
                InProcessServerBuilder.forName(serverName).directExecutor(), 50052);

        productServiceServer.start();
        inProcessChannel = grpcCleanup.register(
                InProcessChannelBuilder.forName(serverName).directExecutor().build());
    }

    @After
    public void tearDown() throws Exception {
        productServiceServer.stop();
    }

    @Test
    public void albServiceImpl_healthcheck(){

        ALBGrpc.ALBBlockingStub blockingStub = ALBGrpc.newBlockingStub(inProcessChannel);

        Empty request = Empty.newBuilder().build();
        Empty response = blockingStub.healthcheck(request);
        assertEquals(request, response);
    }
}