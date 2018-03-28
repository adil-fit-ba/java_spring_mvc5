package banka;

import helper.Datum;
import helper.Kolekcija;

public class Klijent
{
    public String _JMBG = new String(new char[14]);
    public String _imePrezime;
    public int maxR = 10;
    public Racun[] _racuni = new Racun[maxR];
    public int _trenutnoRacuna = 0; //brojac
    public boolean _aktivan;

    public static Klijent Napravi(String jmbg, String ime_prezime)
    {
        Klijent k = new Klijent();
        int x = ime_prezime.length() + 1;
        k._imePrezime = new String(new char[x - 1]);
        k._imePrezime = ime_prezime;
        k._JMBG = jmbg;
        return k;
    }
    public final void Ispis()
    {
        System.out.print("KLIJENT  jmbg: ");
        System.out.print(_JMBG);
        System.out.print(",  Ime i prezime: ");
        System.out.print(_imePrezime);
        System.out.print(",  Broj racuna: ");
        System.out.print(this._trenutnoRacuna);
        System.out.print("\n");
    }

    public final void Dealociraj()
    {
        _imePrezime = null;
        _racuni = null;
    }

    public final void DodajRacun(VrstaRacuna vrstaRacuna, String brojRacuna)
    {
        _racuni[_trenutnoRacuna++] = Racun.Napravi(vrstaRacuna, brojRacuna);
    }

    public final void DodajUplatu(int indexRacuna, float iznos, Datum datum)
    {
        _racuni[indexRacuna].Uplata(iznos, datum);
    }

    public final void DodajIsplatu(int indexRacuna, float iznos, String to, Datum datum)
    {
        _racuni[indexRacuna].Isplata(iznos, to, datum);
    }

    public final Kolekcija GetTransakcijeByIznos(float iznos)
    {
        Kolekcija zbirnaKolekcija = new Kolekcija();
        for (int i = 0; i < _trenutnoRacuna; i++)
        {
            zbirnaKolekcija.Dodaj(_racuni[i].GetTransakcijeByIznos(iznos));
        }
        return zbirnaKolekcija;
    }

    public final Kolekcija GetTransakcijeByDatum(Datum datum)
    {
        Kolekcija zbirnaKolekcija = new Kolekcija();
        for (int i = 0; i < _trenutnoRacuna; i++)
        {
            zbirnaKolekcija.Dodaj(_racuni[i].GetTransakcijeByDatum(datum));
        }
        return zbirnaKolekcija;
    }

    public final void Deaktiviraj()
    {
        _aktivan = false;
    }

	/*
	Unos / Ispis / Dealociraj / DodajRacun / Deaktiviraj /
	DodajUplatu
	DodajIsplatu
	GetTransakcijeByIznos - vraca Kolekciju transakcija koje su imale iznos veci od trazenog
	GetTransakcijeByDatum - vraca Kolekciju transakcija koje su obavljene za trazeni datum
	*/
}

