/**
 * @Author: Cyusa Keny , Irakoze Loraine
 */
package server.models;

import java.sql.Date;
import java.sql.Timestamp;

public class Subscription {
    private int SubscriptionId;
    private int UserID;
    private int  PackageId;
    private Timestamp SubscribedAt;
    private Date ExpirationDate;
    public Subscription(){

    }
Subscription(int subscriptionId,int userID,int packageId,Timestamp subscribedAt,Date ExpirationDate){
    this.SubscriptionId=subscriptionId;
    this.UserID=userID;
    this.PackageId=packageId;
    this.SubscribedAt=subscribedAt;
    this.ExpirationDate=ExpirationDate;
}
Subscription(int userID,int packageId,Timestamp subscribedAt,Date ExpirationDate){
    this.UserID=userID;
    this.PackageId=packageId;
    this.SubscribedAt=subscribedAt;
    this.ExpirationDate=ExpirationDate;
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

    public void setSubscribedAt(Timestamp subscribedAt) {
        SubscribedAt = subscribedAt;
    }

    public Timestamp getSubscribedAt() {
        return SubscribedAt;
    }

    public void setExpirationDate(Date expirationDate) {
        ExpirationDate = expirationDate;
    }

    public Date getExpirationDate() {
        return ExpirationDate;
    }
}
