/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import eventsim.Event;
import eventsim.EventSim;
import java.util.Queue;


/**
 * A customer finishes shopping and heads for the checkout with the shortest
 * queue
 *
 * @author evenal
 */
public class EndShoppingEvent extends Event {
    Customer customer;
    

    public EndShoppingEvent(Customer customer) {
        super(customer.endShoppingTime);
        this.customer = customer;
    }


    @Override
    public Event happen() {
	if (customer.numProducts == 0) {
	    customer.leaveTime = getTime() + 1;
	    return null;
	}
        //customer.leaveTime = customer.checkoutTime + customer.checkoutDuration;
        //return null;
        // Velge kø:
        Checkout[] checkouts = customer.shop.getCheckouts();
        int minQueueLength = 999;
        Checkout chosenCheckout = null;
        
        for(Checkout item : checkouts) {
            Queue<Customer> queue = item.getQueue();
	    int queueSize = queue.size();
	    if (item.activeCustomer != null)
		queueSize++;
	    
            if(queueSize < minQueueLength){
		
                minQueueLength = queueSize;
                chosenCheckout = item;
            }
	    
        }
        // Kunde legges inn i chekout kø.
        chosenCheckout.getQueue().add(customer);
        
        if(minQueueLength == 0 && chosenCheckout.activeCustomer == null){
            return new CheckoutEvent(chosenCheckout, getTime() + 1);
        }
        else return null;
         
    }


    @Override
    public String toString() {
        return "EndShoppingEvent: " + customer;
    }
    
}
