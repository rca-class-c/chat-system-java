package server.config;

import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import utils.CommonUtil;
import utils.ConsoleColor;


/**
 * Server
 * @author Divin Irakiza
 */
public class Server {

//    public static void main(String[] args) {
//        try {
//            final int PORT = 8000;
//            HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
//            CommonUtil.useColor(ConsoleColor.BoldColor.GREEN_BOLD);
//            System.out.println("Server running on port " + PORT);
//            System.out.println();
//            CommonUtil.resetColor();
//
//            Router router = new Router(server);
//
////            router.useRoute(HttpMethod.POST, "/api/files", new FileController(), FileController.class.getMethod("saveFile", Map.class));
//
//            server.setExecutor(null); // creates a default executor
//            server.start();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }

//    public static void main(String[]args) {
//       try {
//            final int PORT = 8000;
//            CommonUtil.resetColor();
//
//            Router router = new Router(server);
//
////            router.useRoute(HttpMethod.POST, "/api/files", new FileController(), FileController.class.getMethod("saveFile", Map.class));
//
//            server.setExecutor(null); // creates a default executor
//            server.start();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//

    public HttpServer createServer() {
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(Config.getServerHostname(), Config.getPORT()), 0);
            CommonUtil.useColor(ConsoleColor.BoldColor.GREEN_BOLD);
            System.out.println("Server running on port " + Config.getPORT());
            System.out.println();
            CommonUtil.resetColor();

//            Router router = new Router(server);
//
////            router.useRoute(HttpMethod.POST, "/api/files", new FileController(), FileController.class.getMethod("saveFile", Map.class));
//
            server.setExecutor(null); // creates a default executor
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return server;
    }
}