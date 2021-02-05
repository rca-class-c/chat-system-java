package server;

import java.util.Map;
import com.sun.net.httpserver.HttpServer;
import server.config.Server;
import server.config.Router;
import server.models.HttpMethod;
import server.services.FileService;

public class Main {
    public static void main(String[] args) {
        try {
            HttpServer server = new Server().createServer();
            server.start();

            Router router = new Router(server);
            router.useRoute(HttpMethod.POST, "/api/files", new FileService(), FileService.class.getMethod("saveFile", Map.class));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

