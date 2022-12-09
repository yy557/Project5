package com.example.project5;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is to demo the Intent class, which you can use to start another Activity.
 * @author Lily Chang
 */
public class OrderPizzaActivity extends AppCompatActivity{
    private ListView listViewAvailableToppings, listViewSelectedToppings;
    private ArrayAdapter<String> adapter2, adapter3;
    private static ArrayList<String> availableToppingsList = new ArrayList<>(Arrays.asList("Sausage", "Pepperoni", "Onion", "GreenPepper", "Onion", "Mushroom", "BBQChicken", "Provolone", "Cheddar", "Beef", "Ham", "Broccoli", "Potato", "Cucumber"));
    private static ArrayList<String> selectedToppingsList = new ArrayList<>();
    String selectedPizza;
    private ArrayList<Item> items = new ArrayList<>();
    private int [] itemImages = {R.drawable.chicagobuildyourown, R.drawable.chicagobbqchicken, R.drawable.chicagodeluxe,
            R.drawable.chicagomeatzza, R.drawable.nybbqchicken, R.drawable.nydeluxe, R.drawable.nybuildyourown,
            R.drawable.nymeatzza};

    /**
     * Creates the Order Pizza page
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pizza);
        selectedPizza = getIntent().getStringExtra("ITEM");
        initializeToppingsList();
        TextView textViewTitle = findViewById(R.id.textViewTitle);
        textViewTitle.setText(selectedPizza);
        adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, availableToppingsList);
        adapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, selectedToppingsList);
        listViewAvailableToppings = findViewById(R.id.listViewAvailableToppings);
        listViewAvailableToppings.setAdapter(adapter2);
        listViewSelectedToppings = findViewById(R.id.listViewSelectedToppings);
        listViewSelectedToppings.setAdapter(adapter3);
        listViewSelectedToppings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                availableToppingsList.add(listViewSelectedToppings.getItemAtPosition(i).toString());
                selectedToppingsList.remove(i);
                adapter2.notifyDataSetChanged();
                adapter3.notifyDataSetChanged();
                updatePrice();
            }
        });
        listViewAvailableToppings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(checkNumberOfToppingsSelected()) {
                    selectedToppingsList.add(listViewAvailableToppings.getItemAtPosition(i).toString());
                    availableToppingsList.remove(i);
                    adapter2.notifyDataSetChanged();
                    adapter3.notifyDataSetChanged();
                    updatePrice();
                }
            }
        });
        RadioGroup pizzaSize = findViewById(R.id.radioGroupPizzaSize);
        pizzaSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updatePrice();
            }
        });
        updatePrice();
    }

    private boolean checkNumberOfToppingsSelected(){
        if(selectedToppingsList.size() == 7) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Warning!");
            alert.setMessage("You may not add more than 7 toppings");
            AlertDialog dialog = alert.create();
            dialog.show();
            return false;
        }
        return true;
    }

    private void initializeToppingsList(){
        listViewAvailableToppings = findViewById(R.id.listViewAvailableToppings);
        listViewSelectedToppings = findViewById(R.id.listViewSelectedToppings);
        TextView crust = findViewById(R.id.crust);
        if(selectedPizza.endsWith("Build Your Own")){
            crust.setText("Pan");
            availableToppingsList = new ArrayList<>(Arrays.asList("Sausage", "Pepperoni", "Onion", "GreenPepper", "Onion", "Mushroom", "BBQChicken", "Provolone", "Cheddar", "Beef", "Ham", "Broccoli", "Potato", "Cucumber"));
            selectedToppingsList = new ArrayList<>();
        }else if(selectedPizza.endsWith("Deluxe")){
            crust.setText("Deep Dish");
            listViewAvailableToppings.setEnabled(false);
            listViewSelectedToppings.setEnabled(false);
            selectedToppingsList = new ArrayList<>(Arrays.asList("Sausage", "Pepperoni", "GreenPepper", "Onion", "Mushroom"));
        }else if(selectedPizza.endsWith("BBQ Chicken")){
            crust.setText("Pan");
            listViewAvailableToppings.setEnabled(false);
            listViewSelectedToppings.setEnabled(false);
            selectedToppingsList = new ArrayList<>(Arrays.asList("BBQChicken", "GreenPepper", "Provolone", "Cheddar"));
        }else if(selectedPizza.endsWith("Meatzza")){
            crust.setText("Stuffed");
            listViewAvailableToppings.setEnabled(false);
            listViewSelectedToppings.setEnabled(false);
            selectedToppingsList = new ArrayList<>(Arrays.asList("Sausage", "Pepperoni", "Beef", "Ham"));
        }
    }

    private void updatePrice(){
        PizzaFactory pizzaFactory = null;
        Pizza pizza = null;
        if(selectedPizza.startsWith("Chicago")){
            pizzaFactory = new ChicagoPizza();
        }else if(selectedPizza.startsWith("New York")){
            pizzaFactory = new NYPizza();
        }
        if(selectedPizza.endsWith("Build Your Own")){
            pizza = pizzaFactory.createBuildYourOwn();
        }else if(selectedPizza.endsWith("Deluxe")){
            pizza = pizzaFactory.createDeluxe();
        }else if(selectedPizza.endsWith("BBQ Chicken")){
            pizza = pizzaFactory.createBBQChicken();
        }else if(selectedPizza.endsWith("Meatzza")){
            pizza = pizzaFactory.createMeatzza();
        }
        RadioGroup pizzaSize = findViewById(R.id.radioGroupPizzaSize);
        pizza.setSize(Size.valueOf(((String)((RadioButton) pizzaSize.findViewById(pizzaSize.getCheckedRadioButtonId())).getText()).toUpperCase()));
        TextView price = findViewById(R.id.price);
        price.setText(String.valueOf((double) ((int) ((pizza.price())*100))/100.0));
    }





    public void order(View view){
        PizzaFactory pizzaFactory = null;
        Pizza pizza = null;
        if(selectedPizza.startsWith("Chicago")){
            pizzaFactory = new ChicagoPizza();
        }else if(selectedPizza.startsWith("New York")){
            pizzaFactory = new NYPizza();
        }
        if(selectedPizza.endsWith("Build Your Own")){
            pizza = pizzaFactory.createBuildYourOwn();
        }else if(selectedPizza.endsWith("Deluxe")){
            pizza = pizzaFactory.createDeluxe();
        }else if(selectedPizza.endsWith("BBQ Chicken")){
            pizza = pizzaFactory.createBBQChicken();
        }else if(selectedPizza.endsWith("Meatzza")){
            pizza = pizzaFactory.createMeatzza();
        }
        RadioGroup pizzaSize = findViewById(R.id.radioGroupPizzaSize);
        pizza.setSize(Size.valueOf(((String)((RadioButton) pizzaSize.findViewById(pizzaSize.getCheckedRadioButtonId())).getText()).toUpperCase()));
        CurrentOrderActivity.addToOrder(pizza);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Add to order");
        alert.setMessage("Pizza added to order");
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void showStoreOrderActivity(View view){
        Intent intent = new Intent(this, StoreOrderActivity.class);
        startActivity(intent);
    }

    public void showCurrentOrderActivity(View view){
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        startActivity(intent);
    }

    public static String[] getToppingsList(){
        return selectedToppingsList.toArray(new String[0]);
    }


}