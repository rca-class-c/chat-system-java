package server;
import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import utils.CommonMethod;
import utils.ConsoleColor;

public class Server {

    public static void main(String[] args) {
        try {
            final int PORT = 8000;
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
            CommonMethod.useColor(ConsoleColor.BoldColor.GREEN_BOLD);
            System.out.println("Server running on port " + PORT);
            System.out.println();
            CommonMethod.resetColor();

            Router router = new Router(server);
            router.useRoute(HttpMethod.GET, "/");
            router.useRoute(HttpMethod.POST, "/api/register");
            router.useRoute(HttpMethod.POST, "/api/files");

            server.setExecutor(null); // creates a default executor
            server.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}