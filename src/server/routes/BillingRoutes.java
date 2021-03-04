package server.routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import server.ChatServer;
import server.requestHandlers.PackageRequestHandler;
import server.requestHandlers.PaymentRequestHandler;

import java.io.PrintWriter;

public class BillingRoutes {
    private String data;
    private PrintWriter writer;
    private ObjectMapper objectMapper;
    private ChatServer server;
    private String request;

    public BillingRoutes(String data, PrintWriter writer, ObjectMapper objectMapper, ChatServer server, String request) {
        this.data = data;
        this.writer = writer;
        this.objectMapper = objectMapper;
        this.server = server;
        this.request = request;
    }

    public BillingRoutes(String data, PrintWriter writer, ObjectMapper objectMapper,String request) {
        this.data = data;
        this.writer = writer;
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public void Main() throws Exception {
        /*
        Packages routes
         */

        if(request.equals("billings/packages")){
            new PackageRequestHandler().HandleViewPackages(writer,objectMapper);
        }
        else if(request.equals("billings/packages/details")){
            new PackageRequestHandler().HandleViewPackageInfo(data, writer, objectMapper);
        }
        else if(request.equals("billings/packages/save")){
            new PackageRequestHandler().HandleSavePackage(data,writer,objectMapper);
        }
        else if(request.equals("billings/packages/search")){
            new PackageRequestHandler().HandleSearchPackage(data,writer,objectMapper);
        }
        else if(request.equals("billings/packages/edit")){
            new PackageRequestHandler().HandleUpdatePackage(data,writer,objectMapper);
        }
        else if(request.equals("billings/packages/delete")){
            new PackageRequestHandler().HandleDeletePackage(data,writer,objectMapper);
        }

        /*
        Subscription routes
         */

        /*
        Payment routes
         */
        else if(request.equals("billings/payments/save")){
            new PaymentRequestHandler().HandleSavePayment(data, writer, objectMapper);
        }
        else if(request.equals("billings/payments/details")){
            new PaymentRequestHandler().HandleGetPaymentDetails(data, writer, objectMapper);
        }
    }
}
