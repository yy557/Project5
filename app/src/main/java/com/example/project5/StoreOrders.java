package com.example.project5;




import java.util.ArrayList;

/**
 * The storeOrders class
 * @author Jake Michalowicz, Young Ye
 */
public class StoreOrders implements Customizable {

    private ArrayList<Order> orders = new ArrayList<>();

    /**
     * Adds an order to the list of store orders
     * @param obj the order object
     * @return true if successful, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Order){
            Order order = (Order) obj;
            orders.add(order);
            return true;
        }
        return false;
    }

    /**
     * Removes an order from the list of store orders
     * @param obj the order object
     * @return true if successful, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Order){
            Order order = (Order) obj;
            orders.remove(order);
            return true;
        }
        return false;
    }


    /**
     * Helper method that builds the string for displaying a pizza
     * @param pizza the pizza that must be displayed
     * @return the properly formatted string representation of a pizza
     */
    private String getPizzaString(Pizza pizza) {
        String string = "";
        if (pizza instanceof Deluxe) {
            string += "Deluxe ";
        } else if (pizza instanceof Meatzza) {
            string += "Meatzza ";
        } else if (pizza instanceof BBQChicken) {
            string += "BBQ Chicken ";
        } else if (pizza instanceof BuildYourOwn) {
            string += "Build Your Own ";
        }
        string += "(";
        if(pizza.getCrust() == Crust.PAN || pizza.getCrust() == Crust.DEEPDISH || pizza.getCrust() == Crust.STUFFED){
            string += "Chicago Style - ";
        }else if(pizza.getCrust() == Crust.BROOKLYN || pizza.getCrust() == Crust.HANDTOSSED || pizza.getCrust() == Crust.THIN){
            string += "New York Style - ";
        }
        string += pizza.getCrust() + "), ";
        ArrayList<Topping> toppings = pizza.getToppings();
        for(int j = 0; j < toppings.size(); j++){
            string += toppings.get(j) + ", ";
        }
        string += pizza.getSize() + ", $" + ((double) ((int) (pizza.price()*100)))/100.0;

        return string;
    }

    /**
     * Gets the orders list
     * @return the orders list
     */
    public ArrayList<Order> getOrders(){
        return orders;
    }
}
