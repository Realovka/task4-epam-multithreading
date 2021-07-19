package by.epam.multithreading;

import by.epam.multithreading.entity.Customer;
import by.epam.multithreading.entity.CustomerQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args)  {
        CustomerQueue queue = CustomerQueue.getInstance();
        queue.addCustomer(new Customer());
        queue.addCustomer(new Customer());
        queue.addCustomer(new Customer());
        queue.addCustomer(new Customer());
        queue.addCustomer(new Customer());
        ExecutorService executorService = Executors.newFixedThreadPool(queue.getSize());
        queue.getCustomers().forEach(executorService::execute);
        executorService.shutdown();
    }
}
