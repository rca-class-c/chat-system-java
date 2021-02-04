package server;

import java.net.InetSocketAddress;
import java.util.Map;

import com.sun.net.httpserver.HttpServer;
//import controllers.FileController;
import utils.CommonUtil;
import utils.ConsoleColor;

public class Server {

    public static void main(String[] args) {
        try {
            final int PORT = 8000;
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
            CommonUtil.useColor(ConsoleColor.BoldColor.GREEN_BOLD);
            System.out.println("Server running on port " + PORT);
            System.out.println();
            CommonUtil.resetColor();

            Router router = new Router(server);

//            router.useRoute(HttpMethod.POST, "/api/files", new FileController(), FileController.class.getMethod("saveFile", Map.class));

            server.setExecutor(null); // creates a default executor
            server.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

