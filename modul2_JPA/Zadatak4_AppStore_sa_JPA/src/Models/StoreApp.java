package Models;

import Helper.Action1Param;
import Helper.Datum;
import Helper.Kolekcija;
import Helper.KonzolniAlati;

public class StoreApp {
    int id;
    String naziv;
    public Kolekcija<Proizvod> proizvodi;
    public Kolekcija<Faktura> fakture;
    public Kolekcija<Klijent> klijenti;

    StoreApp(){
        proizvodi=new Kolekcija<>();
        fakture=new Kolekcija<>();
        klijenti=new Kolekcija<>();
    }

    public static StoreApp napravi(int id, String naziv){

        StoreApp y=new StoreApp();
        y.id=id;
        y.naziv=naziv;
        return y;
    }

    //Unos novog klijenta
    public void m_klijent_add(){
        System.out.println("Unos novog klijenta");
        System.out.print("Ime: ");
        String ime= KonzolniAlati.ucitajString();
        System.out.print("Prezime: ");
        String prezime=KonzolniAlati.ucitajString();
        klijenti.Dodaj(Klijent.napravi(ime,prezime));
    }
    //Unos novog proizvoda
    public void m_proizvod_unos(){
        System.out.println("Unos novog proizvoda");
        System.out.print("Naziv: ");
        String naziv=KonzolniAlati.ucitajString();
        System.out.print("Jedinica mjere: ");
        String jmj=KonzolniAlati.ucitajString();
        System.out.print("Cijena: ");
        float cijena=KonzolniAlati.ucitajFloat();
        proizvodi.Dodaj(Proizvod.napravi(naziv,jmj,cijena));
    }
    //Nova prodaja
    void m_prodaja_add(){
        System.out.println("PRODAJA - FAKTURA");
        System.out.print("Unesite datum(d,m,g) :");
        int d=KonzolniAlati.ucitajInteger();
        int m=KonzolniAlati.ucitajInteger();
        int g=KonzolniAlati.ucitajInteger();
        Datum d1=Datum.Napravi(d,m,g);

        System.out.println("Odaberite klijenta: ");
        Klijent k = klijenti.odaberi();

        Faktura f=Faktura.napravi(d1,k);
        fakture.Dodaj(f);

        Proizvod p;
        do{
            p=proizvodi.odaberi();
            if(p!=null){
                System.out.println("Unesite kolicinu");
                int kolicina= KonzolniAlati.ucitajInteger();
                f.stavke.Dodaj(StavkaFakture.napravi(p,kolicina));
            }
        }while(p!=null);
    }
    // Novi store
    public static void m_store_new(){
        System.out.println("Unesite JIB firme");
        int id=KonzolniAlati.ucitajInteger();
        System.out.println("Unesite naziv firme");
        String naziv=KonzolniAlati.ucitajString();
        napravi(id,naziv);
    }
    public void m_prikaz_faktura() {
        fakture.ForEach((Action1Param<Faktura>)t->{
            t.ispis();
        });
    }
    void m_test_app(){
        StoreApp.napravi(1,"Bingo doo");
        klijenti.Dodaj(Klijent.napravi("Denis","Music"));
        klijenti.Dodaj(Klijent.napravi("Adil","Joldic"));

        proizvodi.Dodaj(Proizvod.napravi("Mlijeko","L",1.50f));
        proizvodi.Dodaj(Proizvod.napravi("Jabuke","KG",1.30f));
        proizvodi.Dodaj(Proizvod.napravi("Kafa 500g","kom",6.90f));

        Faktura f1=Faktura.napravi(Datum.Napravi(25,5,2017),klijenti.podaci[0]);
        Faktura f2=Faktura.napravi(Datum.Napravi(26,5,2017),klijenti.podaci[2]);
        fakture.Dodaj(f1);
        fakture.Dodaj(f2);

        f1.stavke.Dodaj(StavkaFakture.napravi(proizvodi.podaci[0],2));
        f1.stavke.Dodaj(StavkaFakture.napravi(proizvodi.podaci[1],2.5f));
        f2.stavke.Dodaj(StavkaFakture.napravi(proizvodi.podaci[0],3));
        f2.stavke.Dodaj(StavkaFakture.napravi(proizvodi.podaci[1],1.5f));
        f2.stavke.Dodaj(StavkaFakture.napravi(proizvodi.podaci[2],2));

        System.out.println("Suma za fakturu 1 "+fakture.podaci[0].getIznosSuma());
        System.out.println("Suma za fakturu 2 "+fakture.podaci[1].getIznosSuma());

    }
    public void m_izbornik(){
        int unos;
        do{
            System.out.println("2. NEW STORE");
            System.out.println("4. DODAJ: KLIJENT");
            System.out.println("5. DODAJ: PROIZVOD");
            System.out.println("6. DODAJ: PRODAJA(FAKTURA)");
            System.out.println("7. Prikaz faktura");
            System.out.println("8. Dodaj testne podatke i testiraj");
            System.out.println("0. EXIT");
            unos=KonzolniAlati.ucitajInteger();

            if(unos==2) m_store_new();
            if(unos==4) m_klijent_add();
            if(unos==5) m_proizvod_unos();
            if(unos==6) m_prodaja_add();
            if(unos==7) m_prikaz_faktura();
            if(unos==8) m_test_app();
        } while(unos!=0);
    }
}
