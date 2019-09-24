package property;
import customer.Landlord;
import employees.Employee;
import employees.SalesConsultant;

import java.util.HashMap;
public class ForSale {
    private String SaleId;
    private String Status = "W";
    private double minPrice;
    private double CommissionRate;
    private String assignedEmployee;
    private double FinalPrice;
    private boolean Negotiate;
    //HashMap<Integer,String> offerList = new HashMap<>();

    public ForSale(double minPrice, double commissionRate) {
        this.minPrice = minPrice;
        this.CommissionRate = commissionRate;
    }

    public ForSale(String saleId, String status, double minPrice, double commissionRate, String assignedEmployee) {
        SaleId = saleId;
        Status = status;
        this.minPrice = minPrice;
        CommissionRate = commissionRate;
        this.assignedEmployee = assignedEmployee;
    }



    public void setSaleId(String saleId) {
        this.SaleId = saleId;
    }

    public String getAssignedEmployee() {
        return assignedEmployee;
    }

    public void assign(Employee emp){
        this.Status = "A";
        this.assignedEmployee = emp.getEmployeeId();
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

//    public HashMap<Integer, String> getOfferList() {
//        return this.offerList;
//    }

    public boolean isNegotiate() {
        return this.Negotiate;
    }
}


