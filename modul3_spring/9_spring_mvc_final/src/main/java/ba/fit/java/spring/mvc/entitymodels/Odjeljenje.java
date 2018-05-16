package ba.fit.java.spring.mvc.entitymodels;

import ba.fit.java.spring.mvc.entitymodels.Nastavnik;

import javax.persistence.*;

@Entity
public class Odjeljenje
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String skolskaGodina;

    private int razred;

    private String oznaka;

    public Odjeljenje(String skolskaGodina, int razred, String oznaka, Nastavnik razrednik) {
        this.skolskaGodina = skolskaGodina;
        this.razred = razred;
        this.oznaka = oznaka;
        this.razrednik = razrednik;
    }

    private boolean isPrebacenuViseOdjeljenje;

    @ManyToOne(optional = false)
    private Nastavnik razrednik;

    public Odjeljenje() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkolskaGodina() {
        return skolskaGodina;
    }

    public void setSkolskaGodina(String skolskaGodina) {
        this.skolskaGodina = skolskaGodina;
    }

    public int getRazred() {
        return razred;
    }

    public void setRazred(int razred) {
        this.razred = razred;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public boolean isPrebacenuViseOdjeljenje() {
        return isPrebacenuViseOdjeljenje;
    }

    public void setPrebacenuViseOdjeljenje(boolean prebacenuViseOdjeljenje) {
        isPrebacenuViseOdjeljenje = prebacenuViseOdjeljenje;
    }

    public Nastavnik getRazrednik() {
        return razrednik;
    }

    public void setRazrednik(Nastavnik razrednik) {
        this.razrednik = razrednik;
    }
}

