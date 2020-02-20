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
	
	supern.printResults();
    }

    public static final int NUM_CHECKOUTS = 3;
    public static final int NUM_CUSTOMERS = 700;
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
    
    public void printResults() {
	int checkoutCustomerCount = 0;
	int highestQueueLength = 0;
	int totalWaitDuration = 0;
	for (Checkout c : checkouts) {
	    System.out.println(c);
	    checkoutCustomerCount += c.customerCount;
	    highestQueueLength = Math.max(c.highestQueueLength, highestQueueLength);
	    
	    totalWaitDuration += c.totalCheckoutDuration + c.totalQueueDuration;
	}
	
	System.out.println("-----------------------------------------");
	System.out.println("Aggregated stats on the supermarket as a whole:"
		+ "\n Customers with 0 products: " + (NUM_CUSTOMERS - checkoutCustomerCount)
		+ "\n Highest queue length: " + highestQueueLength
		+ "\n Customer average wait duration (time spent queueing & checkout combined): " + ((float)totalWaitDuration/checkoutCustomerCount)
	);
    }

    public Checkout[] getCheckouts() {
        return checkouts;
    }
    
}
 