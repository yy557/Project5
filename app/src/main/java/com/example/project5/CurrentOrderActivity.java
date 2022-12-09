package com.example.project5;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * This is to demo the Intent class, which you can use to start another Activity.
 * @author Lily Chang
 */
public class CurrentOrderActivity extends AppCompatActivity {

    private static final double TAX_RATE = 0.0625;
    private static Order order = new Order();
    private static ArrayList<String> pizzasListArray = new ArrayList<>();
    private ListView pizzasList;
    private ArrayAdapter<String> adapter;
    private TextView textViewOrderNumber, textViewSalesTax, textViewSubtotal, textViewOrderTotal;
    //load the layout file
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, pizzasListArray);
        pizzasList = findViewById(R.id.pizzasList);
        pizzasList.setAdapter(adapter);
        displayOrdersInListView();

    }

    /**
     * Adds a pizza to an order
     * @param pizza the pizza object
     */
    public static void addToOrder(Pizza pizza){
        order.add(pizza);
    }

    /**
     * Adds the order to the store orders
     */
    public void placeOrder(View view){
        StoreOrderActivity.addToOrder(order);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Information");
        alert.setMessage("Placed Order");
        AlertDialog dialog = alert.create();
        dialog.show();
        order = new Order();
        displayOrdersInListView();

    }

    /**
     * Removes all pizza from the order
     */
   public void clearOrder(View view){
        ArrayList<Pizza> pizzas = order.getPizzas();
        while(pizzas.size() > 0){
            pizzas.remove(0);
        }
        displayOrdersInListView();
        Toast.makeText(getApplicationContext(), "Order Cleared", Toast.LENGTH_LONG).show();
    }

    /**
     * Removes the selected pizza from the order
     */
    public void removePizza(View view){
        pizzasList = findViewById(R.id.pizzasList);
        if(pizzasList.getSelectedItemPosition() != -1) {
            ArrayList<Pizza> pizzas = order.getPizzas();
            order.remove(pizzas.get(pizzasList.getSelectedItemPosition()));
            displayOrdersInListView();
        }
    }

    /**
     * Displays all the pizzas in the order using the listview
     */
    private void displayOrdersInListView(){
        adapter.clear();
        pizzasList = findViewById(R.id.pizzasList);
        pizzasListArray = new ArrayList<>();
        ArrayList<Pizza> pizzas = order.getPizzas();
        textViewOrderNumber = findViewById(R.id.textViewOrderNumber);
        textViewOrderNumber.setText(String.valueOf(order.getOrderNumber()));
        double subtotal = 0;
        for(int i = 0; i < pizzas.size(); i++){
            Pizza pizza = pizzas.get(i);
            subtotal += pizza.price();
            adapter.add(getPizzaString(pizza));
        }
        textViewSubtotal = findViewById(R.id.textViewSubtotal);
        textViewSubtotal.setText(String.valueOf(((double) ((int) (subtotal*100)))/100.0));
        textViewSalesTax = findViewById(R.id.textViewSalesTax);
        textViewSalesTax.setText(String.valueOf(((double) ((int) ((subtotal * TAX_RATE)*100)))/100.0));
        textViewOrderTotal = findViewById(R.id.textViewOrderTotal);
        textViewOrderTotal.setText(String.valueOf(((double) ((int) ((subtotal+ subtotal * TAX_RATE)*100)))/100.0));

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
}