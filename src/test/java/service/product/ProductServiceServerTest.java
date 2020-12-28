/**
 * Tapp
 * Copyright (c) 20042020 All Rights Reserved.
 */
package service.product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import io.grpc.ManagedChannel;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.*;
import tapp.product.ProductServiceGrpc;

/**
 * @author faisalrahman
 * @version $Id: ImageServiceServerTest.java, v 0.1 20201109 19.02 faisalrahman Exp $$
 */
//@RunWith(JUnit4.class)
public class ProductServiceServerTest {

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

    public void getCategory_ValidParameters_returnCategory(){


    }
}