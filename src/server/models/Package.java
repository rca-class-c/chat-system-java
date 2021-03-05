package server.models;

/**
 *
 * @authors:
 * - Loraine Irakoze
 * - Cyusa Keny
 */

public class Package {
    public int packageId;
    public String packageName;
    public int period;
    public float price;

    public Package(int packageId, String packageName, int period, float price){
        this.packageId = packageId;
        this.packageName = packageName;
        this.period = period;
        this.price = price;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    public void setPeriod(int period) {
        this.period = period;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    public int getPackageId() {
        return packageId;
    }
    public String getPackageName() {
        return packageName;
    }
    public float getPrice() {
        return price;
    }
    public int getPeriod() {
        return period;
    }

}