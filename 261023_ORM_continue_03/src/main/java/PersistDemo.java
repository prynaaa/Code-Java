import core.EntityManager;
import pojo.Pizza;

import java.io.IOException;

public class PersistDemo {
    static void main() throws IOException {
        Pizza pizza = Pizza.builder()
                .name("4 Jahreszeiten")
                .price(12.50f)
                .build();

        EntityManager em = EntityManager.getInstance();
        em.persist(pizza);
        // em.showTable(Pizza.class);
    }
}
