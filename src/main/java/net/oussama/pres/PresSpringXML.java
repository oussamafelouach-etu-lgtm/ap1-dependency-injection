package net.oussama.pres;

import net.oussama.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PresSpringXML {
    static void main() {
        ApplicationContext springContext =new ClassPathXmlApplicationContext("config.xml");
        IMetier metier =(IMetier) springContext.getBean(IMetier.class);
        System.out.println("RES" +metier.calcul());
    }
}
