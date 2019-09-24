package customer;

import realEstateException.DuplicateSuburbException;

//this is customers tho wants to buy or rent a property
public abstract class BRCustomers extends Customers {
    private String[] suburbCodeList = new String[5];

    private  int numOfSuburb = 0;

    public BRCustomers(String customerId, String passWord,String name, String emailAddress) {
        super(customerId, passWord, name, emailAddress);
    }

    public void addSuburb(String suburbCode) throws DuplicateSuburbException {

        for(int i =0; i<numOfSuburb;i++) {
            if(suburbCodeList[i].equals(suburbCode)) {
                throw new DuplicateSuburbException();
            }
        }

        if(numOfSuburb+1 > suburbCodeList.length){
            String [] newList = new String[numOfSuburb*2];
            for(int i = 0; i< suburbCodeList.length; i++){
                newList[i] = suburbCodeList[i];
            }
            suburbCodeList = newList;
        }
        suburbCodeList[numOfSuburb] = suburbCode;
        numOfSuburb +=1;
    }

    public int getNumOfSuburb() {
        return numOfSuburb;
    }

    public String[] getSuburbCodeList(){
        return suburbCodeList;
    }
}
