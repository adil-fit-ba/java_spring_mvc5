package ba.fit.java.spring.mvc.viewmodels;

import java.util.List;

public class OdjeljenjeIndexVM
{
    public List<Row> rows;

    public static class Row
    {
        public Double prosjekOcjena;

        public String najboljiUcenik;

        public String skolskaGodina;

        public int razred;

        public String oznaka;

        public String razrednik;

        public boolean isPrebacenuViseOdjeljenje;

        public int odjeljenjeId;

        public Row() {
        }

        public Row(Double prosjekOcjena, String najboljiUcenik, String skolskaGodina, int razred, String oznaka, String razrednik, boolean isPrebacenuViseOdjeljenje, int odjeljenjeId) {
            this.prosjekOcjena = prosjekOcjena;
            this.najboljiUcenik = najboljiUcenik;
            this.skolskaGodina = skolskaGodina;
            this.razred = razred;
            this.oznaka = oznaka;
            this.razrednik = razrednik;
            this.isPrebacenuViseOdjeljenje = isPrebacenuViseOdjeljenje;
            this.odjeljenjeId = odjeljenjeId;
        }
    }
}

