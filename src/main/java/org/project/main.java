package org.project;

import com.sun.net.httpserver.HttpServer;
import org.project.controller.TaskController;

import java.io.IOException;
import java.net.InetSocketAddress;

public class main {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/tasks", new TaskController());
        server.setExecutor(null);
        server.start();
        System.out.println("Servidor rodando em http://localhost:8080/tasks");
    }
}
