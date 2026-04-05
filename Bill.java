import java.util.*;
interface Billable {
    double calculateTotal();
}
class utilityBill implements Billable{

    private final String user_name;
    private final int units;
    private static final double taxAmt=0.05;
    utilityBill(String user_name,int units){
        this.user_name=user_name;
        this.units=units;
    }
    private double baseamount(){
        double total=0.0;
        if(units<=100){
            total=units*1.0;
        }
        else if(units<=300){
            total=(100*1.0)+(units-100)*2.0;
        }
        else{
            total=(100*1.0)+(200*2.0)+(units-300)*5.0;
        }
        return total;
    }
    @Override
    public double calculateTotal(){
        // considering 5% as tax
        return baseamount()*(1+taxAmt);
    }
    public void receipt(){
        double base=baseamount();
        double tax=base*taxAmt;
        double total=calculateTotal();
        System.out.println("SmartPay Digital Receipt");
        System.out.println("Customer name : "+user_name);
        System.out.println("Units : "+units);
        System.out.printf("Base amount : %.2f\n",base);
        System.out.printf("Tax 5%% : %.2f\n",tax);
        System.out.printf("Total Amount : %.2f\n",total);
    }
}
public class Bill{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println();
            System.out.println("Welcome to Smart Pay");
            System.out.println("------------------------------------------------");
            System.out.print("Enter user name or Exit to terminate : ");
            String user_name=sc.nextLine();
            if(user_name.equalsIgnoreCase("Exit")){
                System.out.println("Thank you for using Smart Pay Have a good day");
                break;
            }
            if(user_name.length()==0){
                System.out.println("User name cannot be empty");
                continue;
            }
            System.out.print("Enter previous units: ");
            int prev=sc.nextInt();
            sc.nextLine();
            try{
                if(prev<0){
                    throw new NumberFormatException();
                }
            }
            catch(NumberFormatException e){
                System.out.print("Previous units cannot be negative");
                continue;
            }
            System.out.print("Enter current units: ");
            int curr=sc.nextInt();
            sc.nextLine();
            try{
                if(curr<0){
                    throw new NumberFormatException();
                }
            }
            catch(NumberFormatException e){
                System.out.println("units cannot be negative");
                continue;
            }
            if(prev>curr){
                System.out.println("Previous units cannot be greater than current units");
                continue;
            }
            int units=curr-prev;
            utilityBill CurrBill=new utilityBill(user_name, units);
            System.out.println("--------------------------------------------");
            CurrBill.receipt();
        }
    }

}