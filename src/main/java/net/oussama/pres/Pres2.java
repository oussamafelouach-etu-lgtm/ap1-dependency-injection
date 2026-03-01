package net.oussama.pres;

import net.oussama.dao.IDao;
import net.oussama.metier.IMetier;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Scanner;


public class Pres2 {
    static void main() throws Exception{
        Scanner scanner =new Scanner(new File("config.txt"));

        String daoClassName = scanner.nextLine();
        Class cDao =Class.forName(daoClassName);
        cDao.newInstance();
        IDao d=(IDao) cDao.newInstance();


        String metierClassName = scanner.nextLine();
        Class cMetier =Class.forName(metierClassName);
        IMetier metier= (IMetier) cMetier.getConstructor(IDao.class).newInstance(d);
        //IMetier metier= (IMetier) cMetier.getConstructor().newInstance();
        //Method setDao =cMetier.getDeclaredMethod("setDao", IDao.class);
        //setDao.invoke(metier,d);


        System.out.println("Res= "+metier.calcul());

    }
}
