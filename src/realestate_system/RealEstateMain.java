package realestate_system;

import java.util.Scanner;

public class RealEstateMain {

    public static void runApp() {
        Scanner sc = new Scanner(System.in);
        String option;
        RealEstate realEstate = new RealEstate();
        RealEmployee realEmployee = new RealEmployee();
        System.out.println("Please choose customer or employee or end the program." +"\n input  customer  or employee or exit!");
        do{
            try {
                option = sc.next();
                if(option.equals("customer"))
                    realEstate.startRealEstate();
                else if(option.equals("employee"))
                    realEmployee.startRealEmployee();
                else if(option.equals("exit"))
                    System.exit(0);
                else
                    System.out.println("wrong input, please input again");
            } catch (NullPointerException e) {
                System.out.println(e);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }while(true);
    }
}
