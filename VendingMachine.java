
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Lucas
 */
public class VendingMachine {
    private Dispenser snackMachine;
    private Coinbox moneyBox;
    
    public VendingMachine(){
        boolean stopLooping=false;
        snackMachine = new Dispenser();
        moneyBox = new Coinbox(5, 5, 5);
        while(!stopLooping){
            stopLooping = bossWork();
        }
    }
    public void run(){
    
    }
    private void showUserChoice(){
        
    }
    private void serviceCustomer(char choice){
        
    }
    private boolean bossWork(){
        Scanner kb = new Scanner(System.in);
        int choice;
        
        System.out.println("Boss Menu\n"
                + "1: Set Up Dispenser\n"
                + "2: Check Price\n"
                + "3: Change Price\n"
                + "4: Check Stock of Product\n"
                + "5: Restock Product\n"
                + "6: Delete Product\n"
                + "7: Finished");
        System.out.print("Select 1-7: ");
        try{
            choice = kb.nextInt();
        }
        catch(InputMismatchException  e){
            choice = 8;
        }
        System.out.println("");
        if(choice==1){
            snackMachine.setUpDispenser();
            return false;
        }
        else if(choice==2){
            System.out.println("\nSelected: Check Price");
            System.out.println(snackMachine.toString());
            System.out.print("Enter the product you would like to "
                + "price check (1-5): ");
            choice = kb.nextInt();
            snackMachine.getPrice(Integer.toString(choice).charAt(0));
            System.out.println("");
            return false;
        }
        else if(choice==3){
            return false;
        }
        else if(choice==4){
            return false;
        }
        else if(choice==5){
            return false;
        }
        else if(choice==6){
            return false;
        }
        else if(choice==7){
            return true;
        }
        else
            return false;
    }
}
