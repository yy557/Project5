package com.example.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * This is to demo the Intent class, which you can use to start another Activity.
 * @author Lily Chang
 */
public class StoreOrderActivity extends AppCompatActivity {
    private static final double TAX_RATE = 0.0625;

    private static StoreOrders storeOrders = new StoreOrders();
    private static ArrayList<String> orderNumbers = new ArrayList<String>();

    Button cancelOrder;
    TextView pizzaListView, orderTotal;
    Spinner ordNum;

    //load the layout file
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order);



        cancelOrder = findViewById(R.id.cancelOrder);
        pizzaListView = findViewById(R.id.pizzaListView);
        orderTotal = findViewById(R.id.orderTotal);
        ordNum = findViewById(R.id.ordNum);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, orderNumbers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ordNum.setAdapter(adapter);

        ordNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                displayOrderInListView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }
        });
    }

    /**
     * Adds an Order to the static StoreOrder object
     * @param order the Order being added
     */
    public static void addToOrder(Order order){
        storeOrders.add(order);
        orderNumbers.add(Integer.toString(order.getOrderNumber()));
    }

    /**
     * Removes an order from the store order
     */
    public void removeOrder(View v) {
        String selectedOrder = ordNum.getSelectedItem().toString();
        if(selectedOrder.equals("None"))
            return;
        int orderNumber = Integer.parseInt(selectedOrder);
        Order obj = null;
        for(Order ord : storeOrders.getOrders()) {
            if(ord.getOrderNumber() == orderNumber)
                obj = ord;
        }
        storeOrders.remove(obj);
        pizzaListView.setText("");
        orderTotal.setText("");
        orderNumbers.remove(ordNum.getSelectedItem().toString());
    }

    /**
     * Displays the proper information for the selected order on the Store Order page
     */
    private void displayOrderInListView() {
        pizzaListView.setText("");
        orderTotal.setText("");
        String selectedOrder = ordNum.getSelectedItem().toString();
        if(selectedOrder.equals("None"))
            return;
        int orderNumber = Integer.parseInt(selectedOrder);
        Order obj = null;
        for(Order ord : storeOrders.getOrders()) {
            if(ord.getOrderNumber() == orderNumber)
                obj = ord;
        }
        ArrayList<Pizza> pizzas = obj.getPizzas();
        double total = 0;
        for(int i = 0; i < pizzas.size(); i++) {
            Pizza pizza = pizzas.get(i);
            total += pizza.price();
            String string = getPizzaString(pizza);
            pizzaListView.append(string + "\n\n");
        }
        orderTotal.setText(String.valueOf(((double) ((int) ((total+ total * TAX_RATE)*100)))/100.0));
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