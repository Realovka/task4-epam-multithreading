package by.epam.multithreading.entity;

import by.epam.multithreading.exception.MultithreadingException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Cashbox {
    private static final Logger logger = LogManager.getLogger();

    private long id;

    public Cashbox(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cashbox cashbox = (Cashbox) o;
        return id == cashbox.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\nCashbox : \n\tid : ").append(id);
        sb.append('\n');
        return sb.toString();
    }

    public void executeOrder(Customer customer) throws MultithreadingException {
        customer.setStatus(Status.ACCEPTED);
        logger.log(Level.INFO, "Status set accepted at the cashbox " + id + " for customer " + customer.getId());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "The current thread is sleeping");
            throw new MultithreadingException("The current thread is sleeping");
        }
       PickUpTable.getInstance().giveOrder(customer);
    }
}
