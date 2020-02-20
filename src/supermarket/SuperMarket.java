/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import eventsim.Event;
import eventsim.EventSim;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author evenal
 */
public class SuperMarket {

    public static void main(String[] arts) {
        SuperMarket supern = new SuperMarket();
        supern.startSim();
    }

    public static final int NUM_CHECKOUTS = 3;
    public static final int NUM_CUSTOMERS = 1000;
    public static final int SHOP_OPEN_DURATION = 60*60*12; //butikken er åpen i 12 timer målt i sekunder

    Checkout[] checkouts;
    Customer[] customers;
    Event[] init;


    public SuperMarket() {
        checkouts = new Checkout[NUM_CHECKOUTS];
        for (int i = 0; i < NUM_CHECKOUTS; i++)
            checkouts[i] = new Checkout(this, i);
        customers = new Customer[NUM_CUSTOMERS];
        init = new Event[NUM_CUSTOMERS];
        for (int i = 0; i < NUM_CUSTOMERS; i++) {
            Customer c = new Customer(this, i);
            init[i] = new BeginShoppingEvent(c);
            customers[i] = c;
        }
    }


    public void startSim() {
        EventSim sim = EventSim.getInstance();
        sim.setup(init);
        sim.run();
        
        int test = 0;
    } 

    public Checkout[] getCheckouts() {
        return checkouts;
    }
    
}
 