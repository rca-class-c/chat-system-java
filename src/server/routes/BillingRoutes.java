package server.routes;

import server.requestHandlers.PackageRequestHandler;
import server.requestHandlers.PaymentRequestHandler;

/**
 *
 * @authors:
 * - Loraine Irakoze
 * - Cyusa Keny
 */

public class BillingRoutes {
    private String data;
    private java.io.PrintWriter writer;
    private com.fasterxml.jackson.databind.ObjectMapper objectMapper;
    private server.ChatServer server;
    private String request;

    public BillingRoutes(String data, java.io.PrintWriter writer, com.fasterxml.jackson.databind.ObjectMapper objectMapper, server.ChatServer server, String request) {
        this.data = data;
        this.writer = writer;
        this.objectMapper = objectMapper;
        this.server = server;
        this.request = request;
    }

    public BillingRoutes(String data, java.io.PrintWriter writer, com.fasterxml.jackson.databind.ObjectMapper objectMapper, String request) {
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
        else if(request.equals("billings/packages/remove")){
            new PackageRequestHandler().HandleDeletePackage(data,writer,objectMapper);
        }

        /*
        Subscription routes
         */
        else if(request.equals("billings/subscriptions/save")){
            new server.requestHandlers.SubscriptionHandler().HandleNewSubscriptions(data, objectMapper, writer);
        }
        else if(request.equals("billings/subscriptions/valid")){
            new server.requestHandlers.SubscriptionHandler().ValidatingSubscriptionHandler(data, objectMapper, writer);
        }
        else if(request.equals("billings/subscriptions/show")){
            new server.requestHandlers.SubscriptionHandler().ShowSubscriptionHandler(data, objectMapper, writer);
        }
        else if(request.equals("billings/subscriptions/remove")){
            new server.requestHandlers.SubscriptionHandler().DeleteSubscription(data, objectMapper, writer);
        }
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
