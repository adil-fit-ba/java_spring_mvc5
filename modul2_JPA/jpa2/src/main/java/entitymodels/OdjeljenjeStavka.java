package entitymodels;

import javax.persistence.*;

@Entity
public class OdjeljenjeStavka
{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @ManyToOne(optional = false)
    private Ucenik ucenik;

    @ManyToOne(optional = false)
    private Odjeljenje odjeljenje;

    private int brojUDnevniku;

    public OdjeljenjeStavka(Ucenik ucenik, Odjeljenje odjeljenje, int brojUDnevniku) {
        this.ucenik = ucenik;
        this.odjeljenje = odjeljenje;
        this.brojUDnevniku = brojUDnevniku;
    }

    public OdjeljenjeStavka() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ucenik getUcenik() {
        return ucenik;
    }

    public void setUcenik(Ucenik ucenik) {
        this.ucenik = ucenik;
    }

    public Odjeljenje getOdjeljenje() {
        return odjeljenje;
    }

    public void setOdjeljenje(Odjeljenje odjeljenje) {
        this.odjeljenje = odjeljenje;
    }

    public int getBrojUDnevniku() {
        return brojUDnevniku;
    }

    public void setBrojUDnevniku(int brojUDnevniku) {
        this.brojUDnevniku = brojUDnevniku;
    }
}

