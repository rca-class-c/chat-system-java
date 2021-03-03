package server.middlewares;

import utils.ValidSubscription;

public class SubscriptionMiddleware {

    private final ValidSubscription validSubscription = new ValidSubscription();

    public boolean checkValid(Subscription subscribe){
        return validSubscription.isValid(subscribe.getExpirationDate.toString());
    }
}
