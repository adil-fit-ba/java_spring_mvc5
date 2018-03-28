package banka;

public class Banka
{
    public int maxK = 100;
    public int trenutnoKlijenata = 0;
    public Klijent[] klijenti = new Klijent[maxK];
    public final void DodajKlijenta(String jmbg, String ime_prezime)
    {
        klijenti[trenutnoKlijenata++] = Klijent.Napravi(jmbg, ime_prezime);
    }

    public final void Dealociraj()
    {
        for (int i = 0; i < trenutnoKlijenata; i++)
        {
            klijenti[i].Dealociraj();
        }
    }
}