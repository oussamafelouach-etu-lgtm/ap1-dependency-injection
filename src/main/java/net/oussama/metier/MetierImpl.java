package net.oussama.metier;

import net.oussama.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("metier")
public class MetierImpl implements IMetier {

    @Qualifier("d")

    private IDao dao;//couplage faible
    public MetierImpl(@Qualifier("d") IDao dao) {
        this.dao=dao;
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
