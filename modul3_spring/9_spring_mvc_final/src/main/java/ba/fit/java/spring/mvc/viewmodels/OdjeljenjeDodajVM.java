package ba.fit.java.spring.mvc.viewmodels;


import java.util.Map;

public class OdjeljenjeDodajVM
{
    public Map<Integer, String> getOdjeljenja() {
        return odjeljenja;
    }

    public void setOdjeljenja(Map<Integer, String> odjeljenja) {
        this.odjeljenja = odjeljenja;
    }

    public String getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(String nastavnik) {
        this.nastavnik = nastavnik;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public int getOdjeljenjeID() {
        return odjeljenjeID;
    }

    public void setOdjeljenjeID(int odjeljenjeID) {
        this.odjeljenjeID = odjeljenjeID;
    }

    public String getSkolaGodina() {
        return skolaGodina;
    }

    public void setSkolaGodina(String skolaGodina) {
        this.skolaGodina = skolaGodina;
    }

    public int getRazred() {
        return razred;
    }

    public void setRazred(int razred) {
        this.razred = razred;
    }

    public String nastavnik;

    public String oznaka;

    public int odjeljenjeID;

    public Map<Integer, String> odjeljenja;

    public String skolaGodina;

    public int razred;
}
