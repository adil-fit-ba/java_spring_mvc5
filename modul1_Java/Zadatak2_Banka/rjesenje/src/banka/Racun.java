package banka;//C++ TO JAVA CONVERTER WARNING: The following #include directive was ignored:
//#include "helper.Kolekcija.h"

import helper.Action1Param;
import helper.Datum;
import helper.Func1Param;
import helper.Kolekcija;

import java.util.concurrent.atomic.AtomicReference;

public class Racun
{
    public VrstaRacuna _vrstaRacuna;
    public String _brojRacuna ;
    public boolean _aktivan;
    public Kolekcija transakcije = new Kolekcija();

    public final void Ispis()
    {
        System.out.print("Vrsta računa: ");
        System.out.print(_vrstaRacuna);
        System.out.print("\n");
        System.out.print("Broj računa: ");
        System.out.print(_brojRacuna);
        System.out.print("\n");
        System.out.print("Broj transakcija: ");
        System.out.print(transakcije.brojac);
        System.out.print("\n");
    }

    public final void Dealociraj()
    {
    }

    public final void Deaktiviraj()
    {
        _aktivan = false;
    }

    public final float Saldo()
    {
        AtomicReference<Float> s = new AtomicReference<>(0F);


        transakcije.ForEach((Action1Param<Transakcija>) t -> {
            if (t._vrstaTransakcije == VrstaTransakcije.UPLATA)
            {
                s.set( s.get() +  t._iznos);
            }
            if (t._vrstaTransakcije == VrstaTransakcije.ISPLATA)
            {
                s.set(s.get() -t._iznos);
            }
        });

        return s.get();
    }

    public final void Uplata(float iznos, Datum datum)
    {
        Transakcija new_v = Transakcija.NapraviUplatu(iznos, datum);
        transakcije.Dodaj(new_v);
    }
    public final void Isplata(float iznos, String to, Datum datum)
    {
        Transakcija new_v = Transakcija.NapraviIsplatu(iznos, to, datum);
        transakcije.Dodaj(new_v);
    }

    public final Kolekcija GetTransakcijeByIznos(float iznos)
    {

        Kolekcija k = transakcije.Filter((Func1Param<Transakcija>) t -> t._iznos >= iznos);

        return k;
    }

    public final Kolekcija GetTransakcijeByDatum(Datum datum)
    {
        Kolekcija k = transakcije.Filter((Func1Param<Transakcija>) t -> t._datumTransakcije.equals(datum));

        return k;
    }

    public static Racun Napravi(VrstaRacuna vrsta_racuna, String broj_racuna)
    {
        Racun r = new Racun();

//C++ TO JAVA CONVERTER TODO TASK: The following line was determined to be a copy assignment (rather than a reference assignment) - this should be verified and a 'copyFrom' method should be created:
//ORIGINAL LINE: r->_vrstaRacuna = vrsta_racuna;
        r._vrstaRacuna = vrsta_racuna;
        r._brojRacuna = broj_racuna;
        return r;
    }
}
