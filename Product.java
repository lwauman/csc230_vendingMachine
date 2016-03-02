/*
 * Author: Lucas Auman
 * Program 1 - MyString
 * CSC230-02 Spring 2016
 */

public class Product{
    private double prodPrice;
    private String prodName;
    private int qty;
    
    //constructor Initialize name to blank, quantity and price to 0
    public Product(){
        prodName = "";
        prodPrice = 0;
        qty = 0;
    }
    //initializes fields with appropiate values
    public Product(String name, double price, int quantity){
        prodName = name;
        prodPrice = price;
        qty = quantity;
    }
    //sets name to the string that is passed into the method
    public void setName(String name){
        prodName = name;
    }
    //returns name
    public String getName(){
        return prodName;
    }
    //sets proPrice to the double that is passed into the method
    public void setPrice(double price){
        prodPrice = price;
    }
    //returnds prodPrice
    public double getPrice(){
        return prodPrice;
    }
    //Sets qty to the int that is passed into the methods
    public void setQty(int quantity){
        qty = quantity;
    }
    //returns qty
    public int getQty(){
        return qty;
    }
    //returns a string representation of the product
    @Override
    public String toString(){
        String inMoneyFormat = String.format("$%.2f", getPrice());
        return "Name: "+getName()+ ", Price: "+inMoneyFormat+ ", Quantity: " 
                + getQty();
    }
}
