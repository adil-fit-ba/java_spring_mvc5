package ba.fit.java.spring.mvc.viewmodels;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Map;

public class StavkeDodajVM
{
    public Map<Integer, String> ucenici;

    public int odjeljenjeID;

    public int ucenikID;

    public int brojUdnevniku;

    public int odjeljenjeStavkaId;

    public Map<Integer, String> getUcenici() {
        return ucenici;
    }

    public void setUcenici(Map<Integer, String> ucenici) {
        this.ucenici = ucenici;
    }

    public int getOdjeljenjeID() {
        return odjeljenjeID;
    }

    public void setOdjeljenjeID(int odjeljenjeID) {
        this.odjeljenjeID = odjeljenjeID;
    }

    public int getUcenikID() {
        return ucenikID;
    }

    public void setUcenikID(int ucenikID) {
        this.ucenikID = ucenikID;
    }

    public int getBrojUdnevniku() {
        return brojUdnevniku;
    }

    public void setBrojUdnevniku(int brojUdnevniku) {
        this.brojUdnevniku = brojUdnevniku;
    }

    public int getOdjeljenjeStavkaId() {
        return odjeljenjeStavkaId;
    }

    public void setOdjeljenjeStavkaId(int odjeljenjeStavkaId) {
        this.odjeljenjeStavkaId = odjeljenjeStavkaId;
    }
}

