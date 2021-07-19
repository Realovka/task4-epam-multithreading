package by.epam.multithreading.entity;

import java.util.List;
import java.util.Objects;

public class McDonalds {
    private List<Cashbox> cashboxes;
    private static McDonalds instance = new McDonalds();

    private McDonalds() {
        cashboxes = List.of(new Cashbox(1), new Cashbox(2), new Cashbox(3));
    }

    public static McDonalds getInstance(){
        return instance;
    }

    public Cashbox getCashbox(int cashboxId) {
        return instance.cashboxes.get(cashboxId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        McDonalds mcDonalds = (McDonalds) o;
        return Objects.equals(cashboxes, mcDonalds.cashboxes);
    }

    @Override
    public int hashCode() {
        return 31 + (cashboxes != null ? cashboxes.hashCode() : 0);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\nMcDonalds : \n\tcashboxes : ").append(cashboxes);
        sb.append('\n');
        return sb.toString();
    }
}
