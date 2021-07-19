package by.epam.multithreading.entity;

import by.epam.multithreading.exception.MultithreadingException;
import by.epam.multithreading.util.CustomerIdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Customer implements Runnable {
    private static final Logger logger = LogManager.getLogger();
    private long id;
    private List<Product> products;
    private Status status;

    public Customer() {
        this.id = CustomerIdGenerator.generateId();
        this.products = new ArrayList<Product>();
        this.status = Status.DEFAULT;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                Objects.equals(products, customer.products) &&
                status == customer.status;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result *= (int) (id ^ (id >>> 32));
        result *= 31 + (products != null ? products.hashCode() : 0);
        result *= 31 + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\nCustomer : \n\tid : ").append(id);
        sb.append("\n\tproducts : ").append(products);
        sb.append("\n\tstatus : ").append(status);
        sb.append('\n');
        return sb.toString();
    }

    public void run()  {
        products.add(new Product(1, "Cheeseburger"));
        products.add(new Product(2, "French fries"));
        products.add(new Product(3, "Coca-cola"));
        logger.log(Level.INFO, "Products added to basket " + products);
        McDonalds mcDonalds = McDonalds.getInstance();
        Random random = new Random();
        Cashbox cashbox =  mcDonalds.getCashbox(random.nextInt(3));
        try {
            cashbox.executeOrder(this);
        } catch (MultithreadingException e) {
            logger.log(Level.ERROR, "MultithreadingException " + e);
            e.printStackTrace();
        }
    }
}
