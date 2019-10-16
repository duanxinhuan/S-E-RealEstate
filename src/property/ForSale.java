package property;
import customer.Landlord;
import employees.Employee;
import employees.SalesConsultant;

import java.util.HashMap;
public class ForSale {
    private String forSaleId;
    private String Status = "W";
    private double minPrice;
    private double commissionRate;
//    private double finalPrice;  final price can be got from SalesConsultant class
    private String assignedEmployee;
    private String status;
    private boolean negotiate = true;
    private HashMap<Integer,Offer> offerList = new HashMap<>();
    private SalesConsultant sc;


    private ForSale(double minPrice, double newCommissionRate) {
        this.minPrice = minPrice;
        this.commissionRate = newCommissionRate;
    }

    public ForSale(String saleId, String status, double minPrice, double newCommissionRate, String assignedEmployee,
                   Boolean negotiate) {
        forSaleId = saleId;
        Status = status;
        this.minPrice = minPrice;
        commissionRate = newCommissionRate;
        this.assignedEmployee = assignedEmployee;
        this.negotiate = negotiate;
    }

    public void setSaleId(String saleId) {
        this.forSaleId = saleId;
    }

    public String getAssignedEmployee() {
        return assignedEmployee;
    }

    public void assign(Employee emp){
        this.Status = "A";
        this.assignedEmployee = emp.getEmployeeId();
    }

    public void setCommissionRate() {
       if (getNegotiate()){
           commissionRate = 0.02;
       }
       else{
           commissionRate = 0.05;
       }
    }

    public String getSaleId() {
        return this.forSaleId;
    }

    public double getMinPrice() {
        return this.minPrice;
    }

    public double getCommissionRate() {
        return this.commissionRate;
    }

    public String getStatus() {
        return this.Status;
    }

    public HashMap<Integer, Offer> getOfferList() {
        return this.offerList;
    }

    // add offer object into offer list
    public void addOffer(Offer offer) {
        for(int i=0; i<offerList.size(); i++) {
            this.offerList.put(i,offer);
        }
    }

    public boolean getNegotiate() {
        return this.negotiate;
    }



}


