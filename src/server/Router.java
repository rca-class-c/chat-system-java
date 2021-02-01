package server;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import utils.CommonMethod;
import utils.ConsoleColor;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;


public class Router {
    private  HttpServer server;

    public Router(HttpServer server) {
        this.server = server;
    }

    public HttpContext useRoute(HttpMethod API_METHOD, String path) {
        return this.getServer().createContext(path, (exchange -> {
            if (API_METHOD.toString().equals(exchange.getRequestMethod())) {
                String respText = path +  "" + API_METHOD;
                CommonMethod.useColor(ConsoleColor.BoldColor.YELLOW_BOLD);

                StringBuffer me = getRequestBody(exchange);
                System.out.println(me);

                System.out.println("REQUEST: " + API_METHOD +  " \"" + path + "\" " + 200);
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, respText.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(respText.getBytes());
                output.flush();
            } else {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, -1);// 405 Method Not Allowed
            }
            exchange.close();

        }));
    }


    public HttpServer getServer() {
        return server;
    }



    private StringBuffer getRequestBody(HttpExchange exchange) throws IOException {

        Map<String, String> body = new HashMap<>();

        InputStream output1 = exchange.getRequestBody();

        BufferedReader in = new BufferedReader(new InputStreamReader(
                output1));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        System.out.println(response);
        in.close();

        return response;
    }

}

