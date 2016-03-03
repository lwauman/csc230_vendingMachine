/**
 *
 * @author Lucas
 */
public class Coinbox {
    private int numQ, numD, numN, amount;
    
    //constructor intalizes variables
    public Coinbox(int Q, int D, int N){
        numQ = Q;
        numD = D;
        numN = N;
        amount = 0;
    }
    
    //string representation of coinbox
    public String toString(){
        return numQ + " "+ numD+" "+numN;
    }
    //gives change equal to what is passed as long as the value passed is greater
    //than 0 
    public void giveChange(int change){
        int reduceQ, reduceD, reduceN, updatedChange=change;
        if(change>0){
            double changeAsDouble = (double)change/100;
            System.out.printf("Change: $%.2f\n", changeAsDouble);
            //Quarters
            if(change>=numQ*25){
                //if this block is used it means that all the quarters need to 
                //be used in order to return change
                reduceQ = numQ;
                updatedChange -= numQ*25;
            }
            else{
                reduceQ = change / 25; //this determines the number of quarters needed
                updatedChange = change - reduceQ*25; 
            }
            //Dimes
            if(updatedChange>=numD*10){
                //if this block is used it means that all the dimes need to 
                //be used in order to return change
                reduceD = numD;
                updatedChange -= numD*10;
            }
            else{
                reduceD = updatedChange / 10; //number of dimes needed
                updatedChange = updatedChange - reduceD*10;
            }
            //Nickles
            if(updatedChange>=numN*5){
                //if this block is used it means that all the nickles need to 
                //be used in order to return change
                reduceN = numN;
                updatedChange -= numN*5;
            }
            else{
                reduceN = updatedChange / 5; //number of nickles needed
                updatedChange = updatedChange - reduceN*5;
            }
            numQ -= reduceQ;
            numD -= reduceD;
            numN -= reduceN;
            amount = 0;
        }
        else if(change == 0){
            amount = 0;
        }
    }
    //Disply the amount variable in a money format and gives instructions
    public void displayCoins(){
        double amountInMoneyFormat = (double)amount/100;
        System.out.printf("Current balance: $%.2f\n", amountInMoneyFormat);
        System.out.println("Please insert Q, D, N, or R");
    }
    //return amount
    public int getAmount(){
        return amount;
    }
    //Checks what is passed. If money takes the coin and true, if return gives change and false
    //otherwise false
    public boolean option(char choice){
        if(choice == 'Q'|| choice == 'D'|| choice == 'N'){
            takeCoin(choice);
            //displayAmount(); This was called for in the UML but it results in the 
            //current balance being printed twice since ShowUserChoice() in Vending Machine
            //also display current balance
            return true;
        }
        else if(choice == 'R'){
            giveChange(amount);
            return false;
        }
        else{
            return false;
        }
    }

    //checks what is passed and manages variables
    private void takeCoin(char coin){
        if(coin == 'Q'){
            numQ++;
            amount+=25;
        }
        else if(coin == 'D'){
            numD++;
            amount+=10;
        }
        else{
            numN++;
            amount+=5;
        } 
    }
    //displays amount in money format
    private void displayAmount(){
        double amountInMoneyFormat = (double)amount/100;
        System.out.printf("Current balance: $%.2f\n", amountInMoneyFormat);

    } 











}