package homework;

import java.util.ArrayList;
import java.util.List;
public class test8 {
    public static void main(String args[]){
        Order order = new Order(0);
        order.addFood(new Drink("Coke",4));
        order.addFood(new Drink("Coffee",10));
        order.addFood(new Drink("Juice",6));
        order.addFood(new Drink("Tea",5));
        order.addFood(new Dishes("tofu",15));
        order.addFood(new Dishes("stir-fried vegetable",20));
        order.addFood(new Dishes("fried chicken",30));
        order.addFood(new Dishes("streamed fished",35));
        order.showBill();

        order.addFood(new Dishes("Chow mein",35));
        order.setPeopleAmount(4);
        order.showBill();

        for(int i = 0;i < 10;i++){
            order.takeFood();
        }
    }
}

abstract class Food{
    protected String name;
    protected int price;
    public Food(){
        name = "";
        price = 0;
    }
    public Food(String _name,int _price){
        name = _name;
        price = _price;
    }
    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    public void setPrice(int _price){
        price = _price;
    }
    public abstract void take();
}

class Drink extends Food{
    public Drink(String _name,int _price){
        name = _name;
        price = _price;
    }
    public void take(){
        System.out.println("The Drink " + getName() + " is taken");
    }
}

class Dishes extends Food{
    public Dishes(String _name,int _price){
        name = _name;
        price = _price;
    }
    public void take(){
        System.out.println("The Dishes " + getName() + " is taken");
    }
}

class Order{
    protected List<Food> foodList = new ArrayList<>();
    protected int peopleAmount;
    public Order(int _peopleAmount){
        peopleAmount = _peopleAmount;
    }
    public void addFood(Food newFood){
        foodList.add(newFood);
        System.out.println("The food " + '"' + newFood.getName() + '"' + " is added to the order");
    }
    public void setPeopleAmount(int _peopleAmount){
        peopleAmount = _peopleAmount;
    }
    public void showBill(){
        try{
            int sum = 0;
            System.out.println("Bill of the Order:");
            for(int i = 0;i < foodList.size();i++){
                System.out.println("name: " + foodList.get(i).getName() + ", price: " + foodList.get(i).getPrice() + " yuan");
                sum = sum + foodList.get(i).getPrice(); 
            }
            if(peopleAmount == 0){
                throw new ArithmeticException();
            }
            else{
                System.out.println("Each Person should pay:" + sum/peopleAmount + " yuan");
                System.out.println();
            }
        }
        catch(ArithmeticException e){
            System.out.println("Error : Should be at least one person pay for the bill");
            System.out.println();
        }
    }
    public void takeFood(){
        try{
            if(foodList.size() != 0){
                foodList.get(0).take();
                foodList.remove(0);
            }
            else{
                throw new IndexOutOfBoundsException();
            }
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Error : All the food have already taken");
        }
    }
}