
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
        System.out.println("Shutting Down");
    }
    public void run(){
        showUserChoice();
    }
    private void showUserChoice(){
        Scanner kb = new Scanner(System.in);
        System.out.println(snackMachine.toString());
        moneyBox.displayCoins();
        System.out.print("Enter money first and then select a product(1-5): ");
        char userInput = kb.nextLine().toUpperCase().charAt(0);
        serviceCustomer(userInput);
        
    }
    private void serviceCustomer(char choice){
        if(choice=='0'){
            System.out.println("");
        }
        else if(moneyBox.option(choice)){
            System.out.println("");
            run();
        }
        else{
            if(choice != 'R'){
                if(snackMachine.option(choice)){
                    int prodPrice = (int)(snackMachine.getPrice(choice)*100);
                    if(moneyBox.getAmount()<prodPrice){
                            System.out.println("Insufficient funds.");
                            System.out.println("");
                            run();
                        }
                    else{
                        if(snackMachine.dispense(choice) == 1){
                            int change = moneyBox.getAmount()-prodPrice;
                            if(change>0){
                                moneyBox.giveChange(change);
                                System.out.println("Enjoy. Please take your change.");
                            }
                            else{
                                moneyBox.giveChange(change);
                                System.out.println("Enjoy");
                            }
                            System.out.println("");
                            run();
                        }
                        else{
                            System.out.println("Out of Stock");
                            System.out.println("");
                            run();
                        }
                    }
                }
                else{
                    System.out.println("Invalid Entry\n");
                    run();
                }
                    
            }
            else{
                moneyBox.giveChange(moneyBox.getAmount());
                System.out.println("Change dispenced.");
                System.out.println("");
                run();
            }
        }
    }

    private boolean bossWork(){
        Scanner kb = new Scanner(System.in);
        int choice;
        
        System.out.println("Boss Menu\n"
                + "1: Set Up Dispenser\n"
                + "2: Change Price\n"
                + "3: Restock Product\n"
                + "4: Delete Product\n"
                + "5: Start Machine\n"
                + "6: Shutdown");
        System.out.print("Select 1-6: ");
        try{
            choice = kb.nextInt();
        }
        catch(InputMismatchException  e){
            System.out.println("Invalid Input");
            choice = 6;
        }
        System.out.println("");
        
        if(choice==1){
            //snackMachine.tempSetup();
            try{
               snackMachine.setUpDispenser();
                System.out.println("Setup Complete\n"); 
            }catch(InputMismatchException e){
                System.out.println("Invalid Entry. All products have been saved"
                        + " upto this point.\n");
            }
            return false;
        }
        else if(choice==2){
            System.out.println("Selected: Change Price");
            try{
                snackMachine.changePrice();
            }catch(InputMismatchException | ArrayIndexOutOfBoundsException e){
                System.out.println("Invalid Entry\n");
            }catch(NullPointerException e){
                System.out.println("Product doesn't exist\n");
            }
            return false;
        }
        else if(choice==3){
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
        else if(choice==4){
            System.out.println("Selected: Delete Product");
            try{
                snackMachine.deleteProduct();
            }
            catch(InputMismatchException | ArrayIndexOutOfBoundsException e){
                System.out.println("Invalid input\n");
            }
            return false;
        }
        else if(choice==5){
            run();
            return false;
        }
        else if(choice==6){
            return true;
        }
        else{
            System.out.println("Invalid Input");
            return false;
        }
    }
}
