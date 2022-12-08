package com.example.project5;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * This is to demo the Intent class, which you can use to start another Activity.
 * @author Lily Chang
 */
public class MainActivity extends AppCompatActivity {

    private static String selectedPizza = "Chicago BBQ Chicken";
    private ArrayList<Item> items = new ArrayList<>();
    private int [] itemImages = {R.drawable.chicagobbqchicken, R.drawable.chicagobuildyourown, R.drawable.chicagodeluxe,
            R.drawable.chicagomeatzza, R.drawable.nybbqchicken, R.drawable.nydeluxe, R.drawable.nybuildyourown,
            R.drawable.nymeatzza};
    RadioGroup pizzaSize;
    //load the layout file
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rcview = findViewById(R.id.rcView_menu);
        setupMenuItems(); //add the list of items to the ArrayList
        ItemsAdapter adapter = new ItemsAdapter(this, items); //create the adapter
        rcview.setAdapter(adapter); //bind the list of items to the RecyclerView
        //use the LinearLayout for the RecyclerView
        rcview.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupMenuItems() {
        /*
         * Item names are defined in a String array under res/string.xml.
         * Your item names might come from other places, such as an external file, or the database
         * from the backend.
         */
        String [] itemNames = getResources().getStringArray(R.array.itemNames);
        /* Add the items to the ArrayList.
         * Note that I use hardcoded prices for demo purpose. This should be replace by other
         * data sources.
         */
        for (int i = 0; i < itemNames.length; i++) {
            items.add(new Item(itemNames[i], itemImages[i]));
        }
    }


    public static void setSelectedPizza(String s){
        selectedPizza = s;
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
        pizzaSize = findViewById(R.id.radioGroupPizzaSize);
        pizza.setSize(Size.valueOf(((String)((RadioButton) pizzaSize.findViewById(pizzaSize.getCheckedRadioButtonId())).getText()).toUpperCase()));
        //CurrentOrderActivity.addToOrder(pizza);
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
        ArrayList<String> list = new ArrayList<>();

        return list.toArray(new String[0]);
    }
}