package property;
import customer.Landlord;
import employees.Employee;
import employees.SalesConsultant;

import java.util.ArrayList;
import java.util.HashMap;
public class ForSale {
    private String forSaleId;
    private double minPrice;
    private String status;
    private double finalPrice;
    private double commissionRate;
    private String assignedEmployee;
    private ArrayList<Offer> offerList = new ArrayList<>();
    private SalesConsultant sc;


    ForSale(double minPrice, double newCommissionRate) {
        this.minPrice = minPrice;
        this.commissionRate = newCommissionRate;
    }

    public ForSale(String saleId, String status, double minPrice, double newCommissionRate, String assignedEmployee,
                   Boolean negotiate) {
        this.forSaleId = saleId;
        this.status = status;
        this.minPrice = minPrice;
        this.commissionRate = newCommissionRate;
        this.assignedEmployee = assignedEmployee;
        this.negotiate = negotiate;
    }

    public void setSaleId(String saleId) {
        this.forSaleId = saleId;
    }

    public String getAssignedEmployee() {
        return assignedEmployee;
    }

    public void assign(Employee emp) {
        this.status = "A";
        this.assignedEmployee = emp.getEmployeeId();
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
        return this.status;
    }

    public ArrayList<Offer> getOfferList() {
        return this.offerList;
    }

    // add offer object into offer list
    public void addOffer(Offer offer) {
        for(int i=0; i<offerList.size(); i++) {
            this.offerList.add(offer);
        }
    }

    public boolean getNegotiate() {
        return this.negotiate;
    }



}


