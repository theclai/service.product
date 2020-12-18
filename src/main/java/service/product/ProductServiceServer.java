package service.product;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ProductServiceServer {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceServer.class);
    private int port;
    private Server server;

    public ProductServiceServer(int port) {

        this.port = port;

        server = ServerBuilder.forPort(this.port)
                .intercept(new ExceptionHandler())
                .build();
    }

    public ProductServiceServer(ServerBuilder<?> serverBuilder, int port) {

        this.port = port;
    }

    public void start() throws IOException, InterruptedException {

        server.start();
        logger.info("Server started, listening on " + this.port);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                logger.info("*** shutting down gRPC server since JVM is shutting down");
                try {
                    logger.debug("Server stop");
                    ProductServiceServer.this.stop();
                } catch (InterruptedException e) {
                    logger.error(e.getMessage());
                }
                logger.debug("*** server shut down");
                System.err.println("*** server shut down");
            }
        });
    }

    public void stop() throws InterruptedException {
        if (server != null) {
            logger.info("Server shutdown");
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            logger.info("Server await termination");
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        logger.info("Log from {}", ProductServiceServer.class.getSimpleName());
        System.out.println("Read Specific Environment Variable");

        int port = Integer.parseInt(System.getenv("HTTP_PORT"));

        final ProductServiceServer server = new ProductServiceServer(port);
        server.start();
        server.blockUntilShutdown();
    }
}