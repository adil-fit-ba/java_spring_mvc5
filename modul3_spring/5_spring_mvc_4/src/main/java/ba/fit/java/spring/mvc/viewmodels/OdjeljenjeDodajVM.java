package ba.fit.java.spring.mvc.viewmodels;

import java.util.Map;

public class OdjeljenjeDodajVM {

    public String oznaka;

    public String skolaGodina;

    public int razred;

    public int getNastavnikId() {
        return nastavnikId;
    }

    public int nastavnikId;

    public int getOdjeljenjeId() {
        return odjeljenjeId;
    }

    public int odjeljenjeId;


    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public void setSkolaGodina(String skolaGodina) {
        this.skolaGodina = skolaGodina;
    }

    public void setRazred(int razred) {
        this.razred = razred;
    }

    public void setNastavnikId(int nastavnikId) {
        this.nastavnikId = nastavnikId;
    }

    public void setOdjeljenjeId(int odjeljenjeId) {
        this.odjeljenjeId = odjeljenjeId;
    }

    public void setOdjeljenja(Map<Integer, String> odjeljenja) {
        this.odjeljenja = odjeljenja;
    }

    public void setNastavnici(Map<Integer, String> nastavnici) {
        this.nastavnici = nastavnici;
    }

    public String getSkolaGodina() {
        return skolaGodina;
    }

    public int getRazred() {
        return razred;
    }

    public Map<Integer, String> odjeljenja;

    public Map<Integer, String> getNastavnici() {
        return nastavnici;
    }

    public Map<Integer, String> nastavnici;

    public Map<Integer, String> getOdjeljenja() {
        return odjeljenja;
    }
}
