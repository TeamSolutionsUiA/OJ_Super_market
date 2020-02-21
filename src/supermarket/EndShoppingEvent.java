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
 * queue. Registers a checkoutEvent if the chosen checkout is inactive (no customers)
 *
 * @author evenal, Jonathan & Ole Christian
 */
public class EndShoppingEvent extends Event {
    Customer customer;
    

    public EndShoppingEvent(Customer customer) {
        super(customer.endShoppingTime);
        this.customer = customer;
    }


    @Override
    public Event happen() {
	//hvis kunden har null produkter, ikke legg kunden inn i en kø. Avslutt event
	if (customer.numProducts == 0) {
	    customer.leaveTime = getTime() + 1;
	    return null;
	}
        
        // Velge kø:
        int minQueueLength = 999;
        Checkout chosenCheckout = null;
	
        for(Checkout item : customer.shop.checkouts) {
            Queue<Customer> queue = item.getQueue();
	    int queueSize = queue.size();
	    
	    //tell med kunden i kassen:
	    if (item.activeCustomer != null)
		queueSize++;
	    
	    //velg kasse med korteste kø
            if(queueSize < minQueueLength){
                minQueueLength = queueSize;
                chosenCheckout = item;
            }
	    
        }
        // Kunde legges inn i chekout kø.
        chosenCheckout.getQueue().add(customer);
        
	//hvis kassen er helt tom for andre kunder må en checkoutEvent lages for å "kickstarte" checkout event loopen
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
