
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
        return "Product 1: "+ items[0]+" \nProduct 2: "+items[1]
                +" \nProduct 3: "+items[2]+" \nProduct 4: "+items[3]
                +" \nProduct 5: "+items[4];
    }
    public boolean option(char choice){
        int converted = Character.getNumericValue(choice);
        return converted>=1 && converted<=5;
    }
    public void changePrice(){
        Scanner kb = new Scanner(System.in);
        int prodNum;
        double newPrice;
        
        System.out.println(toString());
        System.out.println("");
        System.out.print("Enter the product of which you would "
                + "like to modify the price (1-5): " );
        prodNum = kb.nextInt();
        System.out.println("Product selected: "+items[prodNum-1].getName());
        System.out.print("Enter the new price for the selected item: $");
        newPrice = kb.nextDouble();
        items[prodNum-1].setPrice(newPrice);
        System.out.println("Price Updated");
    }
    public void restockProduct(){
        Scanner kb = new Scanner(System.in);
        int prodNum, toAdd, totalAfterRestock;
        //display products
        System.out.println(toString());
        System.out.println("");
        //gather info 
        System.out.print("Enter the product you would like to "
                + "add inventory to (1-5): ");
        prodNum = kb.nextInt();
        System.out.println("Product selected: "+items[prodNum-1].getName());
        System.out.print("Enter the number of the selected product "
                + "you wish to add to the current "
                + "inventory: ");
        toAdd = kb.nextInt();
        //change product
        totalAfterRestock = items[prodNum-1].getQty()+toAdd;
        items[prodNum-1].setQty(totalAfterRestock);
        System.out.println("Quantity Updated");
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
    public double getPrice(char choice){
        if(!option(choice)){
            return -1;
        }
        else if(items[Character.getNumericValue(choice)-1] == null){
            return -1;
        }
        else 
            return items[Character.getNumericValue(choice)-1].getPrice();
    }
    public boolean inStock(char choice){
        if(!option(choice)){
            return false;
        }
        else if(items[Character.getNumericValue(choice)-1] == null){
            return false;
        }
        else 
            return items[Character.getNumericValue(choice)-1].getQty()>0;
    }
    public int dispense(char choice){
        if(!inStock(choice)){
            return 0;
        }
        else{
            items[Character.getNumericValue(choice)-1].setQty(
                items[Character.getNumericValue(choice)-1].getQty()-1
            );
            return 1;
        }
    }
    public void deleteProduct(){
        Scanner kb = new Scanner(System.in);
        int prodNum;
        
        System.out.println(toString());
        System.out.println("");
        
        System.out.print("Enter the product you would like to "
                + "delete (1-5): ");
        prodNum = kb.nextInt();
        items[prodNum-1] = null;
        numItems--;
        System.out.println("Item deleted");
    }
 
    
    
}
