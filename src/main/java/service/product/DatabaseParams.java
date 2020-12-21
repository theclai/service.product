/**
 * Tapp
 * Copyright (c) 20042020 All Rights Reserved.
 */
package service.product;

/**
 * @author faisalrahman
 * @version $Id: DatabaseParams.java, v 0.1 20201218 17.05 faisalrahman Exp $$
 */
public class DatabaseParams {

    private String databaseHost;
    private int databasePort;
    private String databaseName;
    private String databasePassword;
    private String databaseType;
    private String databaseUsername;

    public String getDatabaseHost() {
        return databaseHost;
    }

    public void setDatabaseHost(String databaseHost) {
        this.databaseHost = databaseHost;
    }

    public int getDatabasePort() {
        return databasePort;
    }

    public void setDatabasePort(int port) {
        this.databasePort = port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public void setDatabaseUsername(String databaseUsername) {
        this.databaseUsername = databaseUsername;
    }
}