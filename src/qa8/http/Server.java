package qa8.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import qa6.QA6;
import qa7.InMemoryTaskManager;
import qa7.Task;
import qa7.TaskManager;
import qa8.gson.DurationAdapter;
import qa8.gson.LocalDateTimeAdapter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Server {
    public static final int PORT = 8080;
    private final HttpServer server;
    private final Gson gson;
    private final TaskManager taskManager;

    public Server(TaskManager taskManager) throws IOException {
        this.taskManager = taskManager;
        gson = new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(Duration.class, new DurationAdapter())
                .create();
        server = HttpServer.create(new InetSocketAddress("localhost", PORT), 0);
        server.createContext("/app", this::handler);
    }

    public void start() {
        System.out.println("Server started on port " + PORT);
        System.out.println("http://localhost:" + PORT + "/app");
        server.start();
    }

    public void stop() {
        server.stop(0);
        System.out.println("Server stopped on port " + PORT);
    }

    private void handler(HttpExchange h) throws IOException {
        final String path = h.getRequestURI().getPath().substring(5);
        switch (path) {
            case "task" -> {
                switch (h.getRequestMethod()) {
                    // read
                    case "GET" -> {
                        final String query = h.getRequestURI().getQuery();
                        if (query == null) {
                            // GET localhost:8080/app/task
                            try {
                                final List<Task> tasks = taskManager.getTasks();
                                final String response = gson.toJson(tasks, new TypeToken<List<Task>>(){}.getType());
                                sendText(h, response);
                            } catch (QA6.ManagerSaveException e) {
                                h.sendResponseHeaders(500, 0);
                            }
                            h.close();
                            return;
                        }

                        // GET localhost:8080/app/task/?id=
                        String idParam = query.substring(3);// ?id=
                        final int id = Integer.parseInt(idParam);
                        final Task task = taskManager.getTask(id);

                        final String response = gson.toJson(task);
                        sendText(h, response);
                    }
                    // create & update
                    case "PUT" -> {
                        // PUT localhost:8080/app/task
                        String body = readText(h);
                        if (body.isBlank()) {
                            // body отсутствует
                            h.sendResponseHeaders(400, 0);
                            return;
                        }
                        final Task task = gson.fromJson(body, Task.class);
                        if (task.getId() == null) {
                            int newId = taskManager.addNewTask(task);
                            final String response = gson.toJson(newId);
                            sendText(h, response);
                        } else {
                            taskManager.updateTask(task);
                            h.sendResponseHeaders(200, 0);
                        }
                    }
                    case "DELETE" -> {
                        h.sendResponseHeaders(501, 0);
                        //not yet implemented
                    }
                    default -> {
                        h.sendResponseHeaders(404, 0);
                    }
                }
            }
            case "epic" -> {
                /**
                 * Как можно писать эндпойнты:
                 *  - GET /tasks/subtasks/epic?id=&name=&comment=
                 *  - GET /tasks/subtasks/epic/{id}
                 *  - GET /tasks/subtasks/epic/?id=  <-- так тоже можно,
                 *  но лично мне такая запись не нравится - я нахожу её менее читаемой, чем предыдущие две
                 */
            }
            default -> {
                System.out.println("Неизвестный зарос: " + h.getRequestURI());
                h.sendResponseHeaders(404, 0);
            }
        }
        h.close();
    }

    public static void main(String[] args) throws IOException {
        final Server server = new Server(new InMemoryTaskManager());
        server.start();
//		server.stop();
    }

    private String readText(HttpExchange h) throws IOException {
        return new String(h.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
    }

    private void sendText(HttpExchange h, String text) throws IOException {
        byte[] resp = text.getBytes(StandardCharsets.UTF_8);
        h.getResponseHeaders().add("Content-Type", "application/json;charset=utf-8");
        h.sendResponseHeaders(200, resp.length);
        h.getResponseBody().write(resp);
    }

}
