package net.oussama.dao;

public class DaoImpl implements IDao {
    @Override
    public double getData() {
        System.out.println("version base de donnee");
        double t=34;
        return t;
    }
}
