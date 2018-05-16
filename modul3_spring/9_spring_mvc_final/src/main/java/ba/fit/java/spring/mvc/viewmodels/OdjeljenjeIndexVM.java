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


    }
}

