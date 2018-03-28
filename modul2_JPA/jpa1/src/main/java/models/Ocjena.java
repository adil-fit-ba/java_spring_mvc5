package models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Ocjena {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Predmet predmet;

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    private Integer ocjenaBrojcano;
    private String ocjenaOpis;

    private Date datum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getOcjenaBrojcano() {
        return ocjenaBrojcano;
    }

    public void setOcjenaBrojcano(Integer ocjenaBrojcano) {
        this.ocjenaBrojcano = ocjenaBrojcano;
    }

    public String getOcjenaOpis() {
        return ocjenaOpis;
    }

    public void setOcjenaOpis(String ocjenaOpis) {
        this.ocjenaOpis = ocjenaOpis;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
}
