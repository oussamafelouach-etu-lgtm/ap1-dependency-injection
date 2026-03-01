package net.oussama.pres;

import net.oussama.metier.MetierImpl;
import net.oussama.ext.DaoImplV2;

public class Pres1 {
    static void main() {
        DaoImplV2 d =new DaoImplV2();
        MetierImpl metier = new MetierImpl(d);
        //metier.setDao(d);//
        System.out.println("RES = "+metier.calcul());

    }
}
