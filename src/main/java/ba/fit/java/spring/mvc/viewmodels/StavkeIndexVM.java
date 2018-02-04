package ba.fit.java.spring.mvc.viewmodels;

import java.util.List;

public class StavkeIndexVM
{
    public int OdjeljenjeId;
    public List<Row> rows;

    public class Row
    {
        public int brojUDnevniku;

        public int brojZakljucihOcjena;

        public String ucenik;

        public int odjeljenjeStavkeId;
    }
}
