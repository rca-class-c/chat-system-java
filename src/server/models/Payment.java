package server.models;

public class Payment {
    private int payId;
    private int methodId;
    private int subId;
    private float Discount;
    private float TotalAmount;

    public void setPayId(int payId) {
        this.payId = payId;
    }
    public void setMethodId(int methodId) {
        this.methodId = methodId;
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
    public int getMethodId() {
        return methodId;
    }
    public float getDiscount() {
        return Discount;
    }
    public float getTotalAmount() {
        return TotalAmount;
    }
}
