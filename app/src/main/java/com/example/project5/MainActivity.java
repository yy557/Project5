package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
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






    public void order(View view){
        PizzaFactory pizzaFactory = null;
        Pizza pizza = null;


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

        return (String[]) list.toArray();
    }
}