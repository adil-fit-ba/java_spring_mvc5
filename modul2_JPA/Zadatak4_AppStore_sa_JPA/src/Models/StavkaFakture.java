package Models;

public class StavkaFakture {
    int id;
    float kolicina;
    Proizvod proizvod;
    static int stavkaBrojac=0;

    public static StavkaFakture napravi(Proizvod proizvod, float kolicina){
        StavkaFakture s = new StavkaFakture();
        s.id=stavkaBrojac++;
        s.kolicina=kolicina;
        s.proizvod=proizvod;
        return s;
    }

    public float getIznos(){
        return kolicina*proizvod.cijena;
    }

    public void ispis(){
        System.out.println(proizvod.naziv+", "+proizvod.cijena+" x "+kolicina
            +" = "+getIznos());
    }
}
