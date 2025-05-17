package org.project.controller;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.project.excpetion.NonEmpty;
import org.project.model.Task;
import org.project.service.TaskService;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

public class TaskController implements HttpHandler {
    private TaskService service = new TaskService();
    Gson gson = new Gson();


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

        if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(200, -1);
            exchange.close();
            return;
        }

        switch (exchange.getRequestMethod()) {
            case "GET":
                GetHandle(exchange);
                break;
            case "POST":
                postHandle(exchange);
                break;
            case "PUT":
                putHandle(exchange);
                break;
            case "DELETE":
                deleteHandle(exchange);
                break;
            default:
                exchange.sendResponseHeaders(405, -1); // Método não permitido
        }


    }


    public void GetHandle(HttpExchange exchange) throws IOException {
        System.out.println("Entrou no GetHandle");
        try {
            List<Task> taskList = service.getAllTasks();
            System.out.println("Pegou lista com " + (taskList != null ? taskList.size() : "null"));
            String response = gson.toJson(taskList);
            System.out.println("Response JSON gerado: " + response);

            exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
            byte[] bytes = response.getBytes("UTF-8");
            exchange.sendResponseHeaders(200, bytes.length);

            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                exchange.sendResponseHeaders(500, -1);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void postHandle(HttpExchange exchange) throws IOException {
        System.out.println("Entrou no postHandle");
        try (InputStream is = exchange.getRequestBody()) {
            String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            System.out.println("Recebeu body: " + body);

            Task task = gson.fromJson(body, Task.class);
            service.AdicionarTask(task); // seu método que insere no banco

            String response = gson.toJson(Map.of("message", "Tarefa criada com sucesso!"));
            exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
            byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(201, bytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(500, -1);
        }
    }
    public void putHandle(HttpExchange exchange) throws IOException {
        System.out.println("Entrou no putHandle");
        try {
            // Pega o caminho, ex: /tasks/2
            String path = exchange.getRequestURI().getPath();
            String[] pathParts = path.split("/");
            if (pathParts.length < 3) { // Verifica se tem /tasks/{id}
                exchange.sendResponseHeaders(400, -1);
                return;
            }

            int id = Integer.parseInt(pathParts[2]); // Pega o id do path

            try (InputStream is = exchange.getRequestBody()) {
                String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                System.out.println("Recebeu body: " + body);

                Task task = gson.fromJson(body, Task.class);

                // Aqui, como você quer só marcar como concluída,
                // você pode ignorar os outros campos e chamar o método que atualiza só o status
                service.AtualizarTask(id);  // método que criamos para marcar concluída

                String response = gson.toJson(Map.of("message", "Tarefa marcada como concluída com sucesso!"));
                exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
                byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
                exchange.sendResponseHeaders(200, bytes.length);

                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(bytes);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(500, -1);
        }
    }

    public void deleteHandle(HttpExchange exchange) throws IOException {
        System.out.println("Entrou no deleteHandle");
        try {
            // Pega o caminho, ex: /tasks/2
            String path = exchange.getRequestURI().getPath();
            // Divide por "/", espera que o último segmento seja o id
            String[] pathParts = path.split("/");
            if (pathParts.length < 3) { // /tasks/{id} tem pelo menos 3 partes: "", "tasks", "id"
                exchange.sendResponseHeaders(400, -1);
                return;
            }
            int id = Integer.parseInt(pathParts[2]); // pega o "2" de /tasks/2
            service.RemoverID(id);

            String response = gson.toJson(Map.of("message", "Tarefa deletada com sucesso!"));
            exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
            byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, bytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(500, -1);
        }
    }




}


