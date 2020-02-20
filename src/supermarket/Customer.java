/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import eventsim.EventSim;


/**
 *
 * @author evenal
 */
public class Customer {
    // customer will pick a random number of products between these two values
    public static final int MAX_PRODUCTS = 100;
    public static final int MIN_PRODUCTS = 0;

    // customer will spend ranom amount of time between these values before
    // going to check out
    public static final int MAX_SHOP_TIME = 60*60;
    public static final int MIN_SHOP_TIME = 10;

    SuperMarket shop;
    String name;

    int beginShoppingTime;
    int shoppingDuration;
    int numProducts;
    int endShoppingTime;
    int queueWaitDuration;
    int checkoutTime;
    int checkoutDuration;
    int leaveTime;


    public Customer(SuperMarket shop, int i) {
        this.shop = shop;
        name = "Cust" + i;
        
        numProducts = EventSim.nextInt(MIN_PRODUCTS, MAX_PRODUCTS);
        shoppingDuration = EventSim.nextInt(MIN_SHOP_TIME, MAX_SHOP_TIME);
	
	
	beginShoppingTime = EventSim.nextInt(0,SuperMarket.SHOP_OPEN_DURATION - shoppingDuration);
	
        endShoppingTime = beginShoppingTime + shoppingDuration;
    }
}
