package property;

public class Offer implements Applicant {

    private String name;
    private double offerPrice;
    private String status;
    private ForSale forSale;

    public String getName() {
        return name;
    }

    public double getOfferPrice() {
        return offerPrice;
    }

    public String getForSaleID() {
        return forSale.getSaleId();
    }

    public String getStatus() {
        return status;
    }

    @Override
    public void accept() {

    }

    @Override
    public void reject() {

    }
}
