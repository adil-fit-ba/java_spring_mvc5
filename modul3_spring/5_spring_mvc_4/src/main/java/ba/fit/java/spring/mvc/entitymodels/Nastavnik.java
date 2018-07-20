package ba.fit.java.spring.mvc.entitymodels;

import org.hibernate.annotations.Cascade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

@Entity
public class Nastavnik
{
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id ;

    private String ime ;
    private String prezime ;

    @ManyToOne(optional = true)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private KorisnickiNalog korisnickiNalog ;

    public Nastavnik() {
    }

    public Nastavnik(String ime, String prezime) {

        this.ime = ime;
        this.prezime = prezime;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public KorisnickiNalog getKorisnickiNalog() {
        return korisnickiNalog;
    }

    public void setKorisnickiNalog(KorisnickiNalog korisnickiNalog) {
        this.korisnickiNalog = korisnickiNalog;
    }
}
