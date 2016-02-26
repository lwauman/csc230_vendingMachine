/**
 *
 * @author Lucas
 */
public class Coinbox {
    private int numQ, numD, numN, amount;
    
    
    public Coinbox(int Q, int D, int N){
        numQ = Q;
        numD = D;
        numN = N;
        amount = 0;
    }
    
    public void giveChange(int change){
        int reduceQ, reduceD, reduceN, updatedChange=change;
        if(change>0){
            //Quaters
            if(change>=numQ*25){
                reduceQ = numQ;
                updatedChange -= numQ*25;
            }
            else{
                reduceQ = change / 25;
                updatedChange = change - reduceQ*25;
            }
            //Dimes
            if(updatedChange>=numD*10){
                reduceD = numD;
                updatedChange -= numD*10;
            }
            else{
                reduceD = updatedChange / 10;
                updatedChange = updatedChange - reduceD*10;
            }
            //Nickles
            if(updatedChange>=numN*5){
                reduceN = numN;
                updatedChange -= numN*5;
            }
            else{
                reduceN = updatedChange / 5;
                updatedChange = updatedChange - reduceN*5;
            }
            numQ -= reduceQ;
            numD -= reduceD;
            numN -= reduceN;
            amount = 0;
        }
    }
    
    public void displayCoins(){
        System.out.println("Current balance:"+" "+amount);
        System.out.println("Please insert Q, D, N, or R");
    }
    
    public int getAmount(){
        return amount;
    }
    
    public boolean option(char choice){
        if(choice == 'Q'|| choice == 'D'|| choice == 'N'){
            takeCoin(choice);
            displayAmount();
            return true;
        }
        else if(choice == 'R'){
            giveChange(amount);
            return false;
        }
        else
            return false;
    }


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

    private void displayAmount(){
        System.out.println("Current balance:"+" "+amount);
    } 











}