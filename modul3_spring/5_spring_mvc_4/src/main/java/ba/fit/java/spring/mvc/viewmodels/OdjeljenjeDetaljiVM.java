package ba.fit.java.spring.mvc.viewmodels;

public class OdjeljenjeDetaljiVM
    {
        public String razrednik;
        public String oznaka;
        public int razred;
        public String skolskaGodina;
        public int brojPredmta;
        public int odjeljenjeID;
        public int brojUcenika;

        public String getRazrednik() {
            return razrednik;
        }

        public void setRazrednik(String razrednik) {
            this.razrednik = razrednik;
        }

        public String getOznaka() {
            return oznaka;
        }

        public void setOznaka(String oznaka) {
            this.oznaka = oznaka;
        }

        public int getRazred() {
            return razred;
        }

        public void setRazred(int razred) {
            this.razred = razred;
        }

        public String getSkolskaGodina() {
            return skolskaGodina;
        }

        public void setSkolskaGodina(String skolskaGodina) {
            this.skolskaGodina = skolskaGodina;
        }

        public int getBrojPredmta() {
            return brojPredmta;
        }

        public void setBrojPredmta(int brojPredmta) {
            this.brojPredmta = brojPredmta;
        }

        public int getOdjeljenjeID() {
            return odjeljenjeID;
        }

        public void setOdjeljenjeID(int odjeljenjeID) {
            this.odjeljenjeID = odjeljenjeID;
        }

        public int getBrojUcenika() {
            return brojUcenika;
        }

        public void setBrojUcenika(int brojUcenika) {
            this.brojUcenika = brojUcenika;
        }
    }
