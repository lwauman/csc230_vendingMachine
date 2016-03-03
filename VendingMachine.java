
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Lucas
 */
public class VendingMachine {
    private Dispenser snackMachine;
    private Coinbox moneyBox;
    
    //constructor instantiates dispencer and coinbox. Put 5 of each coin in coinbox
    //loop boss mode until it returns true
    public VendingMachine(){
        boolean stopLooping=false;
        snackMachine = new Dispenser();
        moneyBox = new Coinbox(5, 5, 5);
        while(!stopLooping){
            stopLooping = bossWork();
        }
        System.out.println("Shutting Down");
    }
    //starts customer mode
    public void run(){
        showUserChoice();
    }
    //displays snackMachine menu, gives directions, accepts user input, called
    //serviceCustomer with input as an argument
    private void showUserChoice(){
        Scanner kb = new Scanner(System.in);
        System.out.println(snackMachine.toString());
        moneyBox.displayCoins();
        System.out.print("Enter money first and then select a product(1-5): ");
        char userInput = kb.nextLine().toUpperCase().charAt(0);
        serviceCustomer(userInput);
        
    }
    //checks what is passed to make sure it is valid and gives appropiate response
    //to valid inputs
    private void serviceCustomer(char choice){
        //this allows going back to bossMode()
        if(choice=='0'){
            System.out.println("");
        }
        //this block is used when Q, D, N is passed
        else if(moneyBox.option(choice)){
            System.out.println("");
            run();
        }
        //something other than Q, D, N was passed
        else{
            //Something other than Q, D, N, R
            if(choice != 'R'){
                //Checks if what was passed was 1-5
                if(snackMachine.option(choice)){
                    int prodPrice = (int)(snackMachine.getPrice(choice)*100);
                    //if they are trying to buy that cost more than they have
                    //entered
                    if(moneyBox.getAmount()<prodPrice){
                            System.out.println("Insufficient funds.");
                            System.out.println("");
                            run();
                        }
                    //they have enough money for what they selected
                    else{
                        //the item is in stock and dispensed
                        if(snackMachine.dispense(choice) == 1){
                            //calculating change
                            int change = moneyBox.getAmount()-prodPrice;
                            if(change>0){
                                //outputs change
                                moneyBox.giveChange(change);
                                System.out.println("Enjoy. Please take your change.");
                            }
                            //change is 0
                            else{
                                //this is needed to set amount to 0. giveChange()
                                //can handle 0 being passed to it
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
                //This is used when Q, D, N, R, and 1-5 wasn't entered
                else{
                    System.out.println("Invalid Entry\n");
                    run();
                }
                    
            }
            //R was entered meaning they want all their money back
            else{
                //give all money entered back
                moneyBox.giveChange(moneyBox.getAmount());
                System.out.println("Change dispensed.");
                System.out.println("");
                run();
            }
        }
    }
    //allows maintence of machine
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
            System.out.println("");
            return false;
        }
        System.out.println("");
        //allows configuring objects
        if(choice==1){
            try{
               snackMachine.setUpDispenser();
                System.out.println("Setup Complete\n"); 
            }
            //an example of when this catch is used is if in responce to set price
            //Coke is entered
            catch(InputMismatchException e){
                System.out.println("Invalid Entry. All products have been saved"
                        + " upto this point.\n");
            }
            return false;
        }
        //change price of a product
        else if(choice==2){
            System.out.println("Selected: Change Price");
            try{
                snackMachine.changePrice();
            }catch(InputMismatchException | ArrayIndexOutOfBoundsException e){
                System.out.println("Invalid Entry\n");
            }
            //trying to change price of a null product
            catch(NullPointerException e){
                System.out.println("Product doesn't exist\n");
            }
            return false;
        }
        //restock product
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
        //delete product
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
        //start machine in customer mode
        else if(choice==5){
            run();
            return false;
        }
        //shut down
        else if(choice==6){
            return true;
        }
        //something other than 1-6 was entered
        else{
            System.out.println("Invalid Input");
            return false;
        }
    }
}
