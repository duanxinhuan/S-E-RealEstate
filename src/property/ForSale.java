package property;
import java.util.HashMap;
public class ForSale {
    private String SaleId;
    private double minPrice;
    private double CommissionRate;
    private double FinalPrice;
    private boolean Negotiate;
    private String Status = "A";
    HashMap<Integer,String> offerList = new HashMap<>();

    public ForSale(double minPrice, double commissionRate, double finalPrice, boolean negotiate) {
        this.minPrice = minPrice;
        this.CommissionRate = commissionRate;
        this.FinalPrice = finalPrice;
        this.Negotiate = false;
    }
    public void setSaleId(String saleId) {
        this.SaleId = saleId;
    }

    public void setCommissionRate() {
       if (isNegotiate()){
           CommissionRate = 0.05;
       }
       else{
           CommissionRate =0.02;
       }

    }

    public String getSaleId() {
        return this.SaleId;
    }

    public double getMinPrice() {
        return this.minPrice;
    }

    public double getCommissionRate() {
        return this.CommissionRate;
    }

    public double getFinalPrice() {
        return this.FinalPrice;
    }

    public String getStatus() {
        return this.Status;
    }

    public HashMap<Integer, String> getOfferList() {
        return this.offerList;
    }

    public boolean isNegotiate() {
        return this.Negotiate;
    }
}


