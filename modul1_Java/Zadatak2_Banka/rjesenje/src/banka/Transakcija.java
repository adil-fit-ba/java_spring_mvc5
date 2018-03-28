package banka;

import helper.Datum;

public class Transakcija
{
    public Datum _datumTransakcije ;
    public String _TO ;
    public float _iznos;
    public VrstaTransakcije _vrstaTransakcije ;
    /* ako je isplata vrijednost _TO postaviti na brojRacuna sa koga se transakcija vrsi*/
    public boolean _aktivna;
    /*u slucaju da se transakcija izbrise ona postaje neaktivna, ali je bitno da o njoj ostane zapis*/


    public final void Ispis()
    {
        if (_aktivna)
        {
            System.out.print("Vrsta transakcije: ");
            System.out.print(_vrstaTransakcije);
            System.out.print("\n");
            System.out.print("Iznos: ");
            System.out.print(_iznos);
            System.out.print("\n");
            System.out.print("To: ");
            System.out.print(_TO);
            System.out.print("\n");
        }
        else
        {
            System.out.print("Transakcija je obrisana");
            System.out.print("\n");
        }
    }

    public static Transakcija NapraviUplatu(float iznos, Datum datum)
    {
        Transakcija t = new Transakcija();
        t._vrstaTransakcije = VrstaTransakcije.UPLATA;
        t._TO = "-";
        t._iznos = iznos;
        t._aktivna = true;
//C++ TO JAVA CONVERTER TODO TASK: The following line was determined to be a copy assignment (rather than a reference assignment) - this should be verified and a 'copyFrom' method should be created:
//ORIGINAL LINE: t._datumTransakcije = datum;
        t._datumTransakcije = datum;
        return t;
    }

    public static Transakcija NapraviIsplatu(float iznos, String to, Datum datum)
    {
        Transakcija t = new Transakcija();
        t._vrstaTransakcije = VrstaTransakcije.ISPLATA;
        t._TO = to;
        t._iznos = iznos;
        t._aktivna = true;
//C++ TO JAVA CONVERTER TODO TASK: The following line was determined to be a copy assignment (rather than a reference assignment) - this should be verified and a 'copyFrom' method should be created:
//ORIGINAL LINE: t._datumTransakcije = datum;
        t._datumTransakcije = datum;
        return t;
    }
}

