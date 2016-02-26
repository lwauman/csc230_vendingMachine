
import java.util.Scanner;

/**
 *
 * @author Lucas
 */
public class Dispenser {
    private Product[] items;
    private int numItems;
    
    public Dispenser(){
        items = new Product[5];
        numItems = 0;
    }
    @Override
    public String toString(){
        return "Product 1: "+ items[0]+". \nProduct 2: "+items[1]
                +". \nProduct 3: "+items[2]+". \nProduct 4: "+items[3]
                +". \nProduct 5: "+items[4];
    }
    public boolean option(char choice){
        return choice>=1 && choice<=numItems;
    }
    public void changePrice(){
        System.out.println(toString());
    }
    public void restockProduct(){
        System.out.println(toString());
    }
    public void setUpDispenser(){
        if(numItems<6){
            Scanner kb = new Scanner(System.in);
            String productName;
            int productPrice, quantity;
            int i=0;
            while(i<5){
                System.out.print("Enter product name: ");
                productName = kb.nextLine();
                System.out.print("Enter the price of the product: ");
                productPrice = kb.nextInt();
                System.out.print("Enter the number of products to be stocked: ");
                quantity = kb.nextInt();
                kb.nextLine();
                items[i] = new Product();
                items[i].setName(productName);
                items[i].setPrice(productPrice);
                items[i].setQty(quantity);
                numItems++;
                i++;
            }
        }
        else
            System.out.println("The vending machine is currrently full");
    }
    
    //public void deleteProduct(){
    //    toString
    //}
    
}
