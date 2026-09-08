package com.estacionamais.server.service;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnection {
    private final DatabaseClient clienteDatabase;

    public DatabaseConnection(DatabaseClient clienteDatabase) {
        this.clienteDatabase = clienteDatabase;
    }
}
