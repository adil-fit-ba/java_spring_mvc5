package ba.fit.java.spring.mvc.entitymodels;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class KorisnickiNalog
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    private String korisnickoIme ;

    private String lozinka ;

    public KorisnickiNalog() {
    }

    public KorisnickiNalog(String korisnickoIme, String lozinka) {

        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
}