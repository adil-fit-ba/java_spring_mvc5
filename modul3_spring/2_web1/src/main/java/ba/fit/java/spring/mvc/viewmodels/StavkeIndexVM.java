package ba.fit.java.spring.mvc.viewmodels;

import java.util.List;

public class StavkeIndexVM
{
    public int OdjeljenjeId;
    public List<Row> rows;

    public int getOdjeljenjeId() {
        return OdjeljenjeId;
    }

    public void setOdjeljenjeId(int odjeljenjeId) {
        OdjeljenjeId = odjeljenjeId;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public static class Row
    {
        public int getBrojUDnevniku() {
            return brojUDnevniku;
        }

        public void setBrojUDnevniku(int brojUDnevniku) {
            this.brojUDnevniku = brojUDnevniku;
        }

        public int getBrojZakljucihOcjena() {
            return brojZakljucihOcjena;
        }

        public void setBrojZakljucihOcjena(int brojZakljucihOcjena) {
            this.brojZakljucihOcjena = brojZakljucihOcjena;
        }

        public String getUcenik() {
            return ucenik;
        }

        public void setUcenik(String ucenik) {
            this.ucenik = ucenik;
        }

        public int getOdjeljenjeStavkeId() {
            return odjeljenjeStavkeId;
        }

        public void setOdjeljenjeStavkeId(int odjeljenjeStavkeId) {
            this.odjeljenjeStavkeId = odjeljenjeStavkeId;
        }

        public int brojUDnevniku;

        public int brojZakljucihOcjena;

        public String ucenik;

        public int odjeljenjeStavkeId;
    }
}
