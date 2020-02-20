/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import eventsim.Event;


/**
 * A customer enters the shop
 *
 * @author evenal
 */
public class CheckoutCompletedEvent extends Event {
    Customer customer;
    Checkout checkout;
    SuperMarket market;

    public CheckoutCompletedEvent(Customer customer, Checkout checkout) {
        super(customer.checkoutTime);
        this.customer = customer;
	this.checkout = checkout;
    }


    @Override
    public Event happen() {
	checkout.activeCustomer = null;
        return null;
    }
}
