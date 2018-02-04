package ba.fit.java.spring.mvc.entitymodels;

import javax.persistence.*;

@Entity
public class DodjeljenPredmet
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(optional = false)
    private OdjeljenjeStavka odjeljenjeStavka ;

    @ManyToOne(optional = false)
    private Predmet predmet ;

    private int zakljucnoPolugodiste ;

    private int zakljucnoKrajGodine ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OdjeljenjeStavka getOdjeljenjeStavka() {
        return odjeljenjeStavka;
    }

    public void setOdjeljenjeStavka(OdjeljenjeStavka odjeljenjeStavka) {
        this.odjeljenjeStavka = odjeljenjeStavka;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public int getZakljucnoPolugodiste() {
        return zakljucnoPolugodiste;
    }

    public void setZakljucnoPolugodiste(int zakljucnoPolugodiste) {
        this.zakljucnoPolugodiste = zakljucnoPolugodiste;
    }

    public int getZakljucnoKrajGodine() {
        return zakljucnoKrajGodine;
    }

    public void setZakljucnoKrajGodine(int zakljucnoKrajGodine) {
        this.zakljucnoKrajGodine = zakljucnoKrajGodine;
    }
}
