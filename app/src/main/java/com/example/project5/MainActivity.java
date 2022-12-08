package com.example.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * This is to demo the Intent class, which you can use to start another Activity.
 * @author Lily Chang
 */
public class MainActivity extends AppCompatActivity {

    private RadioButton radioButtonChicago, radioButtonNewYork, radioButtonDeluxe, radioButtonBBQChicken, radioButtonMeatzza, radioButtonBuildYourOwn;
    static CheckBox checkBoxSausage;
    static CheckBox checkBoxPepperoni;
    static CheckBox checkBoxGreenPepper;
    static CheckBox checkBoxOnion;
    static CheckBox checkBoxMushroom;
    static CheckBox checkBoxBBQChicken;
    static CheckBox checkBoxProvolone;
    static CheckBox checkBoxCheddar;
    static CheckBox checkBoxBeef;
    static CheckBox checkBoxHam;
    static CheckBox checkBoxBrocolli;
    static CheckBox checkBoxPotato;
    static CheckBox checkBoxCucumber;
    RadioGroup pizzaSize;
    //load the layout file
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void order(View view){
        PizzaFactory pizzaFactory = null;
        Pizza pizza = null;
        if(radioButtonChicago.isChecked()){
            pizzaFactory = new ChicagoPizza();
        }else if(radioButtonNewYork.isChecked()){
            pizzaFactory = new NYPizza();
        }
        if(radioButtonBuildYourOwn.isChecked()){
            pizza = pizzaFactory.createBuildYourOwn();
        }else if(radioButtonDeluxe.isChecked()){
            pizza = pizzaFactory.createDeluxe();
        }else if(radioButtonBBQChicken.isChecked()){
            pizza = pizzaFactory.createBBQChicken();
        }else if(radioButtonMeatzza.isChecked()){
            pizza = pizzaFactory.createMeatzza();
        }

    }

    public static String[] getToppingsList(){
        ArrayList<String> list = new ArrayList<>();
        if(checkBoxSausage.isSelected()){
            list.add("Sausage");
        }
        if(checkBoxPepperoni.isSelected()){
            list.add("Pepperoni");
        }
        if(checkBoxGreenPepper.isSelected()){
            list.add("GreenPepper");
        }
        if (checkBoxOnion.isSelected()) {
            list.add("Onion");
        }
        if (checkBoxMushroom.isSelected()) {
            list.add("Mushroom");
        }
        if (checkBoxBBQChicken.isSelected()) {
            list.add("BBQChicken");
        }
        if (checkBoxProvolone.isSelected()) {
            list.add("Provolone");
        }
        if (checkBoxCheddar.isSelected()) {
            list.add("Cheddar");
        }
        if (checkBoxBeef.isSelected()) {
            list.add("Beef");
        }
        if (checkBoxHam.isSelected()) {
            list.add("Ham");
        }
        if (checkBoxBrocolli.isSelected()) {
            list.add("Broccoli");
        }
        if (checkBoxPotato.isSelected()) {
            list.add("Potato");
        }
        if (checkBoxCucumber.isSelected()) {
            list.add("Cucumber");
        }
        return (String[]) list.toArray();
    }
}