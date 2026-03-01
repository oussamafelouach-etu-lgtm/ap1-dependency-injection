package net.oussama.metier;

import net.oussama.dao.IDao;

public class MetierImpl implements IMetier {
    private IDao dao;//couplage faible
    public MetierImpl(IDao dao) {
        this.dao=dao;
    }
    public MetierImpl() {
    }

    @Override
    public double calcul() {
        double t =dao.getData();
        double res=t * 12 *Math.PI/2*Math.cos(t);
        return res;
    }
    public void setDao(IDao dao){
        this.dao=dao;
    }

}
