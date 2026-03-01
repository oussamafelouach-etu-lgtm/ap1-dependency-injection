package net.oussama.pres;

import net.oussama.dao.IDao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Pres2 {
    static void main() throws Exception{
        Scanner scanner =new Scanner(new File("config.txt"));

        String daoClassName = scanner.nextLine();
        Class cDao =Class.forName(daoClassName);
        cDao.newInstance();
        IDao dao=(IDao) cDao.newInstance();
        System.out.println(dao.getData());

        String metierClassName = scanner.nextLine();
        Class cMetier =Class.forName(daoClassName);
        cMetier.newInstance();
        IDao metier=cDao.getConstructor(IDao.class).newInstance(d);
        System.out.println(dao.getData());
    }
}
