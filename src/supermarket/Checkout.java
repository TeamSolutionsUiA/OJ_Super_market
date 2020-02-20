/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import java.util.LinkedList;
import java.util.Queue;


/**
 *
 * @author evenal
 */
public class Checkout {
    // amount of time per prouct (to scan barcode)
    public static final int PROD_DURATION = 2;
    // amount of time to pay
    public static final int PAY_DURATION = 10;
    //total time for checkout = PAY_DURATION + PROD_DURATION*customer.numProd

    SuperMarket shop;
    String name;
    Queue<Customer> queue;
    
    int highestQueueLength;
    int customerCount;
    int totalQueueDuration;
    int totalCheckoutDuration;
    int totalQueueLength;


    public Checkout(SuperMarket shop, int i) {
        this.shop = shop;
        this.name = "Checkout" + i;
        this.queue = new LinkedList();
    }

    public Queue<Customer> getQueue() {
        return queue;
    }

    public static int getPROD_DURATION() {
        return PROD_DURATION;
    }

    public static int getPAY_DURATION() {
        return PAY_DURATION;
    }
    
}
