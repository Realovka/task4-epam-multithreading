package by.epam.multithreading.entity;

import by.epam.multithreading.exception.MultithreadingException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class PickUpTable {
    private static final Logger logger = LogManager.getLogger();
    private static PickUpTable instance = new PickUpTable();

    private PickUpTable() {

    }

    public static PickUpTable getInstance(){
        return instance;
    }

    public void giveOrder(Customer customer) throws MultithreadingException{
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "The current thread is sleeping");
            throw new MultithreadingException("The current thread is sleeping");
        }
        customer.setStatus(Status.DONE);
        logger.log(Level.INFO, "Status set done for customer " + customer.getId());
        CustomerQueue.getInstance().removeCustomer(customer);
    }

}
