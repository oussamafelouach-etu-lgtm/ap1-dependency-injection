package net.oussama.pres;

import net.oussama.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PresSpringAnnotation {
    static void main() {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext("net.oussama");
        IMetier metier =applicationContext.getBean(IMetier.class);
        System.out.println("RES="+metier.calcul());
    }
}
