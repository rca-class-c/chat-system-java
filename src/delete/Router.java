package delete;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import utils.CommonUtil;
import utils.ConsoleColor;
import java.io.*;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.util.Map;



public class Router {
    private  HttpServer server;

    public Router(HttpServer server) {
        this.server = server;
    }

    public HttpContext useRoute(HttpMethod API_METHOD, String path, Object object, Method method) {
        return this.getServer().createContext(path, (exchange -> {
            if (API_METHOD.toString().equals(exchange.getRequestMethod())) {
                Object response = null;
                CommonUtil.useColor(ConsoleColor.BoldColor.YELLOW_BOLD);

                StringBuffer requestBody = getRequestBody(exchange);
                Map<String, String> body = CommonUtil.mapRequestString(requestBody.toString());

                try {
                    response = method.invoke(object, body);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                
                System.out.println("REQUEST: " + API_METHOD +  " \"" + path + "\" " + 200);
                assert response != null;
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.toString().getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(response.toString().getBytes());
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

