/**
 * @Author: Cyusa Keny , Irakoze Loraine
 */
package server.models;

public class Subscription {
    private int SubscriptionId;
    private int UserID;
    private int  PackageId;
    private java.sql.Timestamp SubscribedAt;
    private java.sql.Date ExpirationDate;
    public Subscription(){

    }
public Subscription(int subscriptionId, int userID, int packageId, java.sql.Timestamp subscribedAt, java.sql.Date ExpirationDate){
    this.SubscriptionId=subscriptionId;
    this.UserID=userID;
    this.PackageId=packageId;
    this.SubscribedAt=subscribedAt;
    this.ExpirationDate=ExpirationDate;
}
Subscription(int userID, int packageId, java.sql.Timestamp subscribedAt, java.sql.Date ExpirationDate){
    this.UserID=userID;
    this.PackageId=packageId;
    this.SubscribedAt=subscribedAt;
    this.ExpirationDate=ExpirationDate;
}

    public Subscription(int subscriptionId, int userID, int packageId) {
        this.SubscriptionId=subscriptionId;
        this.UserID=userID;
        this.PackageId=packageId;
    }

    public void setSubscriptionId(int subscriptionId) {
        SubscriptionId = subscriptionId;
    }

    public int getSubscriptionId() {
        return SubscriptionId;
    }

    public void setPackageId(int packageId) {
        PackageId = packageId;
    }

    public int getPackageId() {
        return PackageId;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setSubscribedAt(java.sql.Timestamp subscribedAt) {
        SubscribedAt = subscribedAt;
    }

    public java.sql.Timestamp getSubscribedAt() {
        return SubscribedAt;
    }

    public void setExpirationDate(java.sql.Date expirationDate) {
        ExpirationDate = expirationDate;
    }

    public java.sql.Date getExpirationDate() {
        return ExpirationDate;
    }
}
