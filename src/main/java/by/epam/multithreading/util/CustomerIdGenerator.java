package by.epam.multithreading.util;

public class CustomerIdGenerator {
    private static long id;

    private CustomerIdGenerator() {
    }

    public static long generateId(){
        return ++id;
    }
}
