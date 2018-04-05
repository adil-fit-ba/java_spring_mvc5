package Models;

public class Klijent {
    String ime;
    String prezime;
    int id;
    static int klijentBrojac=0;

    public static Klijent napravi(String ime, String prezime){
        Klijent k = new Klijent();
        k.id=klijentBrojac++;
        k.ime=ime;
        k.prezime=prezime;
        return k;
    }

    public void ispis(){
        System.out.println("Id:"+id+", Ime:"+ime+", Prezime:"+prezime);
    }
}
