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
    public static final int PAY_DURATION = 20;
    //total time for checkout = PAY_DURATION + PROD_DURATION*customer.numProd

    SuperMarket shop;
    String name;
    Queue<Customer> queue;
    Customer activeCustomer;
    
    int highestQueueLength;
    int customerCount;
    int totalQueueDuration;
    int totalCheckoutDuration;
    int totalQueueLength;
    int highestQueueDuration;

    public Checkout(SuperMarket shop, int i) {
        this.shop = shop;
        this.name = "Checkout" + i;
        this.queue = new LinkedList();
    }

    public Queue<Customer> getQueue() {
        return queue;
    }
    
    public float averageQueueLengh() {
	return (float) totalQueueLength / customerCount;
    }
    
    public float averageQueueDuration() {
	return (float) totalQueueDuration / customerCount;
    }
    
    public float averageCheckoutDuration() {
	return (float) totalCheckoutDuration / customerCount;
    }
    
    public float averageTotalDuration() {
	return (float) (totalQueueDuration + totalCheckoutDuration) / customerCount;
    }
    
    public static int getPROD_DURATION() {
        return PROD_DURATION;
    }

    public static int getPAY_DURATION() {
        return PAY_DURATION;
    }
    
    @Override
    public String toString() {
        return name + ":"
		+ "\n Customers: " + customerCount
		+ "\n Highest queue length: " + highestQueueLength
		+ "\n Highest queue duration: " + highestQueueDuration + "s"
		+ "\n Average queue length (as experienced from customer): " + averageQueueLengh()
		+ "\n Average total time spent in queue per customer: " + averageQueueDuration()+ "s";
    }
}
