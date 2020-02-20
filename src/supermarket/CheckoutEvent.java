/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import eventsim.Event;
import eventsim.EventSim;
import supermarket.Checkout;
import supermarket.Customer;


/**
 * Pops the first Customer from Queue in Checkout, and calculates duration of checkout for customer.
 * Creates new checkout event if there are still Customers in the queue, after the
 * current customer has been processed. 
 * 
 *
 * @author evenal
 */
public class CheckoutEvent extends Event {
    Checkout checkout;


    public CheckoutEvent(Checkout checkout, int eventTime) {
        super(eventTime);
        this.checkout = checkout;
    }


    @Override
    public Event happen() {
	if (checkout.queue.isEmpty())
	    return null;
	
        // Sjekke kølengden og oppdatere høyeste kølengde.:
        int queueLength = checkout.queue.size();
        if(queueLength > checkout.highestQueueLength){
            checkout.highestQueueLength = queueLength;
        }
        checkout.totalQueueLength += queueLength;
        
        checkout.customerCount++;
        
        Customer customer = checkout.queue.poll();
	checkout.activeCustomer = customer;
        customer.queueWaitDuration = getTime() - customer.endShoppingTime;
        customer.checkoutDuration = customer.numProducts * Checkout.PROD_DURATION + Checkout.PAY_DURATION;
        customer.checkoutTime = getTime() + customer.checkoutDuration;
        customer.leaveTime = customer.checkoutTime + 10;
        
        checkout.totalQueueDuration += customer.queueWaitDuration;
        checkout.totalCheckoutDuration += customer.checkoutDuration;
        
	
	EventSim.getInstance().addEvent(new CheckoutCompletedEvent(customer,checkout));
        return new CheckoutEvent(checkout, customer.checkoutTime + 1);
        
    }


    @Override
    public String toString() {
      return "bø";  
    }

}
