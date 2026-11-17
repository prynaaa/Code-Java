import core.EntityManager;
import pojo.Gulasch;
import pojo.Pizza;

import java.io.IOException;

public class PersistDemo {
    static void main() throws Exception {
        Pizza pizza = Pizza.builder()
                .name("4 Jahreszeiten")
                .price(12.50f)
                .build();

        Gulasch gulasch = Gulasch.builder()
                .name("Gulasch")
                .size("Groß")
                .price(14.25f)
                .build();

        Gulasch gulaschSuppe = Gulasch.builder()
                .name("Gulasch-Suppe")
                .size("Mittel")
                .price(11.75f)
                .build();

        EntityManager em = EntityManager.getInstance();
        em.persist(pizza);
        em.persist(gulaschSuppe);
        em.persist(gulasch);
    }
}
