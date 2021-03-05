package server.models;


/**
 *
 * @authors:
 * - Loraine Irakoze
 * - Cyusa Keny
 */

public class Payment {
    private int payId;
    private int subId;
    private float Discount = 0;
    private float TotalAmount;
public  Payment(){

}
    public Payment(int payId, int subId, float discount, float totalAmount){
        this.payId = payId;
        this.subId = subId;
        this.Discount = discount;
        this.TotalAmount = totalAmount;
    }

    public void setPayId(int payId) {
        this.payId = payId;
    }
    public void setSubId(int subId) {
        this.subId = subId;
    }
    public void setDiscount(float discount) {
        Discount = discount;
    }
    public void setTotalAmount(float totalAmount) {
        TotalAmount = totalAmount;
    }

    public int getPayId() {
        return payId;
    }
    public int getSubId() {
        return subId;
    }
    public float getDiscount() {
        return Discount;
    }
    public float getTotalAmount() {
        return TotalAmount;
    }
}