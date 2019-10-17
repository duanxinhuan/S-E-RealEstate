package property;

import util.DateTime;

public class Offer implements Applicant {

    private String buyerName;
    private double offerPrice;
    private DateTime offerDate;
    private String offerId;
    private String offerStatus;

    public Offer(String buyerName, double offerPrice, String offerId,
                 DateTime offerDate, String offerStatus) {
        this.buyerName = buyerName;
        this.offerPrice = offerPrice;
        this.offerId = offerId;
        this.offerDate = offerDate;
        this.offerStatus = offerStatus;
    }

    public String getOfferStatus() {
        return offerStatus;
    }

    public String getName() {
        return buyerName;
    }

    public double getOfferPrice() {
        return offerPrice;
    }

    public String getOfferId() {
        return offerId;
    }

    public DateTime getOfferDate() {
        return offerDate;
    }
    // the status after buy makes an offer
    public String setWaitingResponseStatus() {
        this.offerStatus = "waiting for response";
        return offerStatus;
    }
    // the status of offer being accepted by vendor
    public String setAcceptStatus() {
        this.offerStatus = "accepted";
        return offerStatus;
    }
    // the status of offer being rejected by vendor
    public String setRejectedStatus() {
        this.offerStatus = "rejected";
        return offerStatus;
    }

    @Override
    public void accept() {

    }

    @Override
    public void reject() {

    }
}
