package ba.fit.java.spring.mvc.entitymodels;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AutorizacijskiToken
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    private String vrijednost ;

    @ManyToOne(optional = false)
    private KorisnickiNalog korisnickiNalog ;

    private Date vrijemeEvidentiranja ;

    private String ipAdresa ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVrijednost() {
        return vrijednost;
    }

    public void setVrijednost(String vrijednost) {
        this.vrijednost = vrijednost;
    }

    public KorisnickiNalog getKorisnickiNalog() {
        return korisnickiNalog;
    }

    public void setKorisnickiNalog(KorisnickiNalog korisnickiNalog) {
        this.korisnickiNalog = korisnickiNalog;
    }

    public Date getVrijemeEvidentiranja() {
        return vrijemeEvidentiranja;
    }

    public void setVrijemeEvidentiranja(Date vrijemeEvidentiranja) {
        this.vrijemeEvidentiranja = vrijemeEvidentiranja;
    }

    public String getIpAdresa() {
        return ipAdresa;
    }

    public void setIpAdresa(String ipAdresa) {
        this.ipAdresa = ipAdresa;
    }
}