package ba.fit.java.spring.mvc.entitymodels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Predmet
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String naziv;

    private int razred;

    public Predmet(String naziv, int razred) {
        this.naziv = naziv;
        this.razred = razred;
    }

    public Predmet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getRazred() {
        return razred;
    }

    public void setRazred(int razred) {
        this.razred = razred;
    }
}

