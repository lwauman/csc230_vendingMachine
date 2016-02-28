
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
            System.out.println("Invalid Input");
            choice = 8;
        }
        System.out.println("");
        
        if(choice==1){
            snackMachine.tempSetup();
            System.out.println("Setup Complete");
            return false;
        }
        else if(choice==2){
            System.out.println("Selected: Check Price");
            System.out.println(snackMachine.toString());
            System.out.print("Enter the product you would like to "
                + "price check (1-5): ");
            try{
                choice = kb.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Invalid Entry\n");
                return false;
            }
            if(choice>0 && choice<6){
                double toFormat = snackMachine.getPrice(Integer.toString(choice).charAt(0));
                if(toFormat>0){
                    System.out.println(String.format("The price of product %d is $%.2f.\n", choice, toFormat));
                }
                else
                    System.out.println("Product out of stock\n");
            }
            else
                System.out.println("Invalid Entry\n");
            return false;
        }
        else if(choice==3){
            System.out.println("Selected: Change Price");
            System.out.println(snackMachine.toString());
            try{
                snackMachine.changePrice();
            }catch(InputMismatchException | ArrayIndexOutOfBoundsException e){
                System.out.println("Invalid Entry\n");
            }
            return false;
        }
        else if(choice==4){
            System.out.println("Selected: Check Stock of Product");
            System.out.println(snackMachine.toString());
            System.out.print("Enter the number of the product "
                    + "that you wish to check stock of (1-5): ");
            try{
                choice = kb.nextInt();
                char choiceToChar = Integer.toString(choice).charAt(0);
                if(snackMachine.inStock(choiceToChar)){
                    System.out.println("Product "+choice+" is in stock.\n");
                }
                else
                    System.out.println("Product "+choice+" is out of stock\n");
            }catch(InputMismatchException e){
                System.out.println("Invalid Entry\n");
            }
            return false;
        }
        else if(choice==5){
            try{
                System.out.println("Selected: Restock Product");
                snackMachine.restockProduct();
            }
            catch(InputMismatchException | ArrayIndexOutOfBoundsException e){
                System.out.println("Invalid Input\n");
            }
            catch(NullPointerException e){
                System.out.println("Item doesn't exist\n");
            }
            return false;
        }
        else if(choice==6){
            System.out.println("Selected: Delete Product");
            try{
                snackMachine.deleteProduct();
            }
            catch(InputMismatchException | ArrayIndexOutOfBoundsException e){
                System.out.println("Invalid input\n");
            }
            return false;
        }
        else if(choice==7){
            return true;
        }
        else{
            System.out.println("Invalid Input");
            return false;
        }
    }
}
