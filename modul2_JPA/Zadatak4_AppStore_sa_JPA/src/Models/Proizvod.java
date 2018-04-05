package Models;

public class Proizvod {
    int id;
    String naziv;
    String jmj;
    float cijena;
    static int proizvodBrojac=0;

    public static Proizvod napravi(String naziv, String jmj, float cijena){
        Proizvod p = new Proizvod();
        p.id=proizvodBrojac++;
        p.naziv=naziv;
        p.jmj=jmj;
        p.cijena=cijena;
        return p;
    }

    public void ispis(){
        System.out.println("Naziv:"+naziv+", Jmj:"+jmj+", Cijena:"+cijena);
    }
}
