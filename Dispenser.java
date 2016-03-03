/*
 * Author: Lucas Auman
 * Program 1 - MyString
 * CSC230-02 Spring 2016
 */

import java.util.Scanner;

public class Dispenser {
    private Product[] items; //an array of products
    private int numItems;
    
    //contructs intializes items to hold 5 objects and sets numItems to 0
    public Dispenser(){
        items = new Product[5];
        numItems = 0;
    }
    
    //returns a string representation of all the products
    @Override
    public String toString(){
        return "Product 1: "+ items[0]+" \nProduct 2: "+items[1]
                +" \nProduct 3: "+items[2]+" \nProduct 4: "+items[3]
                +" \nProduct 5: "+items[4];
    }
    //returns true if the numericalvalue of choice is between 1-5 inclusive
    //returns false otherwise
    public boolean option(char choice){
        int converted = Character.getNumericValue(choice);
        return converted>=1 && converted<=5;
    }
    //allows boss to change the price of an object
    public void changePrice(){
        Scanner kb = new Scanner(System.in);
        int prodNum;
        double newPrice;
        
        System.out.println(toString());//shows all the items
        //gather info
        System.out.print("Enter the number of the product that you"
                + " wish to modify (1-5): " );
        prodNum = kb.nextInt();
        System.out.println("Product selected: "+items[prodNum-1].getName());//displays the item selected
        System.out.print("Enter the new price for the selected item: $");
        newPrice = kb.nextDouble();
        //check input and set price if valid
        if(newPrice>0){
            items[prodNum-1].setPrice(newPrice);//sets the new price
            System.out.println("Price Updated\n");
        }
        else
            System.out.println("Invalid Price Entered\n");
    }
    //allows boss to restock a product
    public void restockProduct(){
        Scanner kb = new Scanner(System.in);
        int prodNum, toAdd, totalAfterRestock;
        //display products
        System.out.println(toString());
        //gather info 
        System.out.print("Enter the number of the product that you wish "
                + "to modify (1-5): ");
        prodNum = kb.nextInt();
        System.out.println("Product selected: "+items[prodNum-1].getName());
        System.out.print("Enter the number of the selected product "
                + "you wish to add to the current "
                + "inventory: ");
        toAdd = kb.nextInt();
        //change product if valid entry
        if(toAdd>0){
            totalAfterRestock = items[prodNum-1].getQty()+toAdd;
            items[prodNum-1].setQty(totalAfterRestock);
            System.out.println("Quantity Updated\n");
        }
        else
            System.out.println("Invalid Entry\n");
    }
    
    //allows boss to stock machine and configure product info
    public void setUpDispenser(){
        //if not full
        if(numItems<5){
            Scanner kb = new Scanner(System.in);
            //kb.nextLine();//make sure that buffer is clear
            
            int index=-1;
            
            //this gets the index of the first element in the array that is null
            for(int i=0;i<5;i++)
                if(items[i]==null){
                    index=i;
                    break;
                }
            //while there are null elements of the array and while index is still inbounds
            while(index!=-1 && index<5 && items[index]==null){
                String productName="";
                double productPrice=0;
                int quantity=-1;
                //gather info
                while("".equals(productName)){
                    System.out.print("Enter product name: ");
                    productName = kb.nextLine();
                }
                while(productPrice<=0){
                    System.out.println("Enter prices as they are normally formatted.\n"
                            + "Example: One dollar and 25 cents = $1.25, "
                            + "75 cents = $0.75 or $.75, 25 cents = $0.25 or $.25");
                    System.out.print("Enter the price of the product: $");
                    productPrice = kb.nextDouble();
                }
                while(quantity<0){
                    System.out.print("Enter the number of products to be stocked: ");
                    quantity = kb.nextInt();
                }            
                kb.nextLine();//clears the endline for next product
                //set info
                items[index] = new Product();
                items[index].setName(productName);
                items[index].setPrice(productPrice);
                items[index].setQty(quantity);
                numItems++;
                index++;
            }
        }
        else
            System.out.println("The vending machine is currrently full");
    }
    //checks choice to make sure it is valid if so returns the price of the product
    //located in the array at index choice-1. -1 if choice is invalid or the product
    //is null
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
    //checks to make sure choice is valid. Returns false if invalid or if product 
    //at choice-1 is null. Returns true if the product at index choice-1 has a qty of more
    //than 0
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
    //passes choice to inStock(). If inStock returns false return 0. otherwise returns
    // 1 and reduces qty of product by 1
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
    //allows boss to delete a product
    public void deleteProduct(){
        Scanner kb = new Scanner(System.in);
        int prodNum;
        //display products
        System.out.println(toString());
        
        //gather info
        System.out.print("Enter the number of the product you would like to "
                + "delete (1-5): ");
        prodNum = kb.nextInt();
        //delete item
        if(prodNum>=1 && prodNum<=5){
            items[prodNum-1] = null;
            numItems--;
            System.out.println("Item deleted\n");
        }
        else{
            System.out.println("Invalid Entry\n");
        }
            
        
    }
    public void tempSetup(){
        items[0] = new Product("Pepsi", 1.00, 1);
        items[1] = new Product("Coke", 1.00, 1);
        items[2] = new Product("Mt. Dew", .75, 0);
        items[3] = new Product("Sprite", .75, 1);
        items[4] = new Product("Water", .50, 1);
    }
 
    
    
}
