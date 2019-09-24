import realestate_system.*;
import java.util.*;


public class main {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String option;
        RealEstate realEstate = new RealEstate();
        RealEmployee realEmployee = new RealEmployee();
        System.out.println("Are u a employee or customer?" +"\n input  customer  or employee!");
        do{
            option = sc.next();
            if(option.equals("customer"))
                realEstate.startRealEstate();
            else if(option.equals("employee"))
                realEmployee.startRealEmployee();
            else
                System.out.println("wrong input, please input again");
        }while(true);

    }
}
