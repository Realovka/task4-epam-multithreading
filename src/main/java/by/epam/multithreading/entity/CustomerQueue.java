package by.epam.multithreading.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CustomerQueue {
    private static final Logger logger = LogManager.getLogger();
    private List<Customer> customers;

    private static CustomerQueue instance = new CustomerQueue();

    private CustomerQueue() {
        this.customers = new ArrayList<>();
    }

    public static CustomerQueue getInstance(){
        return instance;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }


    public void addCustomer(Customer customer){
        customers.add(customer);
        logger.log(Level.INFO, "New customer came to McDonalds " + customer.getId());
    }

    public void removeCustomer(Customer customer){
        customers.remove(customer);
        logger.log(Level.INFO, "Customer left McDonalds " + customer.getId());
    }

    public int getSize(){
        return customers.size();
    }

}
