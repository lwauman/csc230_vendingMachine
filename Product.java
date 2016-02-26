
/**
 *
 * @author Lucas
 */
public class Product{
    private double prodPrice;
    private String prodName;
    private int qty;
    
    public Product(){
        prodName = "";
        prodPrice = 0;
        qty = 0;
    }
    
    public Product(String name, double price, int quantity){
        prodName = name;
        prodPrice = price;
        qty = quantity;
    }
    
    public void setName(String name){
        prodName = name;
    }
    
    public String getName(){
        return prodName;
    }
    
    public void setPrice(int price){
        prodPrice = price;
    }
    
    public double getPrice(){
        return prodPrice;
    }
    
    public void setQty(int quantity){
        qty = quantity;
    }
    
    public int getQty(){
        return qty;
    }
    
    @Override
    public String toString(){
        return "Name: "+getName()+ ", Price: " +getPrice()+ ", Quantity: " + getQty();
    }
}
