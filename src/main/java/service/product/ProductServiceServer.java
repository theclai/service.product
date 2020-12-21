package service.product;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.flywaydb.core.Flyway;

public class ProductServiceServer {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceServer.class);
    private int port;
    private Server server;

    public ProductServiceServer(int port) {

        this.port = port;

        server = ServerBuilder.forPort(this.port)
                .addService(new AlbServiceImpl())
                .intercept(new ExceptionHandler())
                .build();
    }

    public ProductServiceServer(ServerBuilder<?> serverBuilder, int port) {

        this.port = port;
        this.server = serverBuilder.addService(new AlbServiceImpl()).build();
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

        int port = 8080;

        if(System.getenv("HTTP_PORT") != null && System.getenv("HTTP_PORT").length() > 0){
            port = Integer.parseInt(System.getenv("HTTP_PORT"));
        }
        
        String flywayTask = System.getenv("FLYWAY_TASK");
        DatabaseParams databaseParams = new DatabaseParams();
        databaseParams.setDatabaseHost(System.getenv("DATABASE_HOST"));
        databaseParams.setDatabasePort(Integer.parseInt(System.getenv("DATABASE_PORT")));
        databaseParams.setDatabaseName(System.getenv("DATABASE_NAME"));
        databaseParams.setDatabaseType(System.getenv("DATABASE_TYPE"));
        databaseParams.setDatabaseUsername(System.getenv("DATABASE_USERNAME"));
        databaseParams.setDatabasePassword(System.getenv("DATABASE_PASSWORD"));

        final ProductServiceServer server = new ProductServiceServer(port);

        if (flywayTask != null && flywayTask.length() > 0 ) {

            runMigration(System.getenv("FLYWAY_TASK"), databaseParams);
            server.start();
            server.blockUntilShutdown();
            return;
        }

        server.start();
        server.blockUntilShutdown();
    }

    private static void runMigration(String flywayTask, DatabaseParams databaseParams) {

        if(flywayTask != ""){

            String operation = flywayTask.toLowerCase();

            try{

                Flyway flyway = Flyway.configure().dataSource(
                        databaseParams.getDatabaseHost(),
                        databaseParams.getDatabaseUsername(),
                        databaseParams.getDatabasePassword()).load();

                switch (operation) {
                    case "baseline":
                        //flyway baseline
                        flyway.baseline();
                        break;
                    case "clean":
                        //flyway clean
                        flyway.clean();
                        break;
                    case "info":
                        //flyway info
                        flyway.info();
                        break;
                    case "migrate":
                        //flyway migrate
                        flyway.migrate();
                        break;
                    case "repair":
                        //flyway repair
                        flyway.repair();
                        break;
                    case "validate":
                        //flyway validate
                        flyway.validate();
                        break;
                }
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }
    }
}