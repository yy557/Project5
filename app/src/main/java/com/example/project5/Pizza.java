package com.example.project5;

import java.util.ArrayList;

/**
 * The abstract class pizza
 * @author Jake Michalowicz, Young Ye
 */
public abstract class Pizza implements Customizable {
    private ArrayList<Topping> toppings = new ArrayList<>();
    private Crust crust;
    private Size size;
    public abstract double price();

    public void setSize(Size size){
        this.size = size;
    }
    public Size getSize(){
        return size;
    }
    public Crust getCrust(){
        return crust;
    }
    public ArrayList<Topping> getToppings(){
        return toppings;
    }
    public void setCrust(Crust crust){
        this.crust = crust;
    }
}
