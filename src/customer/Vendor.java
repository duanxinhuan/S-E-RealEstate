package customer;

import property.Property;


public class Vendor extends VLCustomers {
    private String statusOfOffer[];
    private long time;
    private long advance;
    private int ConsiderationTime;
    private double minPrice;



    public Vendor(String customerId, String passWord, String name, String emailAddress, String[] statusOfOffer) {
        super ( customerId, passWord, name, emailAddress );
        this.statusOfOffer = new String[]{"accepted","rejected"};
    }


    public void setConsiderationTime(int considerationTime) {
        ConsiderationTime = considerationTime;
    }

    public void setStatusOfOffer(String[] statusOfOffer) {
        this.statusOfOffer = statusOfOffer;
    }

    public void DateTime(int considerationTime ) {
        advance = ((considerationTime * 24L)*60L)*60L*1000L;
        time = System.currentTimeMillis()+advance;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }


//    int numOfSell = 0;
//    public void addProperty(Property p) {
//        numOfSell+=1;
//    }

}







