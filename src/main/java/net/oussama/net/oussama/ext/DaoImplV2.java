package net.oussama.net.oussama.ext;

import net.oussama.dao.IDao;

public class DaoImplV2 implements IDao {
    @Override
    public double getData() {
        System.out.println("version Capteur ...............");
        double t=12;
        return t;
    }
}
