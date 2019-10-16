package customer;

import employees.SalesConsultant;
import property.Offer;
import property.ForSale;

public class Buyer extends BRCustomers {

    private final double Down_Payment_Percent = 0.1;

    public Buyer(String customerId, String passWord, String name, String emailAddress) {
        super(customerId, passWord, name, emailAddress);
    }

    public Offer makeOffer(Offer offer) {
        return offer;
    }

    // buyer can withdraw offer before that the formal offer has been made or before cooling period
    public void withdrawOffer(Offer offer, ForSale fs) {
        fs.getOfferList().remove(offer);
    }

    public void makeDownPayment(SalesConsultant sc) {
        double downPayment = sc.getFinalPrice() * Down_Payment_Percent;
    }

}
