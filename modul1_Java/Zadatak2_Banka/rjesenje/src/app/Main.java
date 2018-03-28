package app;

import banka.*;
import helper.Action1Param;
import helper.Datum;
import helper.Kolekcija;
import helper.KonzolniAlati;



public class Main {

    private static String crt = "\n---------------------------\n";

    private static int PrikaziMenu()
    {

        int izbor;
        System.out.print(crt);
        System.out.print("\t::MENU::");
        System.out.print(crt);
        System.out.print("1. Dodaj korisnika");
        System.out.print("\n");
        System.out.print("2. Dodaj racun");
        System.out.print("\n");
        System.out.print("3. Dodaj Uplatu");
        System.out.print("\n");
        System.out.print("4. Dodaj Isplatu");
        System.out.print("\n");
        System.out.print("5. Stanje na računu");
        System.out.print("\n");
        System.out.print("6. Pretraga po min. iznosu transakcije");
        System.out.print("\n");
        System.out.print("7. Pretraga po datumu transakcije");
        System.out.print("\n");
        System.out.print("8. Deaktiviranje korisnika");
        System.out.print(crt);
        System.out.print("9. Kraj");
        System.out.print(crt);
        do
        {
            System.out.print("Vas izbor -> ");
            izbor = KonzolniAlati.ucitajInteger();
        } while (izbor < 1 || izbor>9);
        //  system("cls");
        return izbor;
    }

    private static Klijent IzborKlijenta(Banka B)
    {
        for (int i = 0; i < B.trenutnoKlijenata; i++)
        {
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.print(B.klijenti[i]._imePrezime);
            System.out.print("\n");
        }
        int odabir;
        System.out.print("Unesite id korisnika: ");

        while (true)
        {
            odabir = KonzolniAlati.ucitajInteger();
            if (odabir > 0 && odabir <= B.trenutnoKlijenata)
            {
                break;
            }
            System.out.print("Los unos!!!");
            System.out.print("\n");
        }
        odabir--;
        Klijent odabrani = B.klijenti[odabir];
        if (odabrani != null)
        {
            System.out.print("Odabran klijent ---> ");
            odabrani.Ispis();
        }
        return odabrani;
    }

    private static Racun IzborRacuna(Klijent K)
    {
        for (int i = 0; i < K._trenutnoRacuna; i++)
        {
            System.out.print(i + 1);
            System.out.print(": ");
            K._racuni[i].Ispis();
        }
        int x;
        System.out.print("Unesite RB: ");
        x = KonzolniAlati.ucitajInteger();

        return K._racuni[x - 1];
    }




    public static void main(String[] args) {

        Banka B1 = new Banka();

        int izborMenu;
        do
        {
            izborMenu = PrikaziMenu();
            if (izborMenu == 1)
            {
                //Dodaj korisnika
                System.out.print("Unesite jmbg");
                System.out.print("\n");
                String jmbg = new String(new char[14]);
                jmbg = KonzolniAlati.ucitajString();

                System.out.print("Unesite ime i prezime");
                System.out.print("\n");
                String ime_prezime = KonzolniAlati.ucitajString();

                B1.DodajKlijenta(jmbg, ime_prezime);
            }
            if (izborMenu == 2)
            {
                //Dodaj racun
                Klijent k = IzborKlijenta(B1);

                System.out.print("Unesite broj racuna");
                System.out.print("\n");
                String racun = KonzolniAlati.ucitajString();

                k.DodajRacun(VrstaRacuna.DEVIZNI, racun);
            }
            if (izborMenu == 3)
            {
                //Uplata
                Klijent k = IzborKlijenta(B1);
                Racun r = IzborRacuna(k);
                System.out.print("Unesite iznos uplate ");
                System.out.print("\n");
                float iznos;
                iznos = KonzolniAlati.ucitajFloat();
                System.out.print("Unesite datum uplate");
                System.out.print("\n");
                Datum date = new Datum();
                date._dan = KonzolniAlati.ucitajInteger();
                date._mjesec = KonzolniAlati.ucitajInteger();
                date._godina = KonzolniAlati.ucitajInteger();
                r.Uplata(iznos, date);
            }
            if (izborMenu == 4)
            {
                //Isplata
                Klijent k = IzborKlijenta(B1);
                Racun r = IzborRacuna(k);
                System.out.print("Unesite iznos isplate ");
                System.out.print("\n");
                float iznos;
                iznos = KonzolniAlati.ucitajFloat();
                System.out.print("Unesite datum isplate");
                System.out.print("\n");
                Datum date = new Datum();
                date._dan = KonzolniAlati.ucitajInteger();
                date._mjesec = KonzolniAlati.ucitajInteger();
                date._godina = KonzolniAlati.ucitajInteger();;
                r.Isplata(iznos, "??", date);
            }
            if (izborMenu == 5)
            {
                //Stanje
                Klijent k = IzborKlijenta(B1);
                Racun r = IzborRacuna(k);
                System.out.print("Stanje na racunu je ");
                System.out.print(r.Saldo());
            }
            if (izborMenu == 6)
            {
                //Pretraga po min. iznosu
                Klijent k = IzborKlijenta(B1);
                System.out.print("Pretraga: Unesite min. iznos transakcije");
                System.out.print("\n");
                float f;
                f = KonzolniAlati.ucitajFloat();
                Kolekcija transakcije = k.GetTransakcijeByIznos(f);
                transakcije.ForEach(new Action1Param<Transakcija>() {
                    @Override
                    public void invoke(Transakcija t) {
                        t.Ispis();
                    }
                });

                transakcije.ForEach((Action1Param<Transakcija>) t -> t.Ispis());

                transakcije.ForEach((Action1Param<Transakcija>) Transakcija::Ispis);
            }
            if (izborMenu == 7)
            {
                //Pretraga po datumu
                Klijent k = IzborKlijenta(B1);
                System.out.print("Pretraga: Unesite datum transakcije");
                System.out.print("\n");
                System.out.print("Unesite datum isplate");
                System.out.print("\n");
                Datum date = new Datum();
                date._dan = KonzolniAlati.ucitajInteger();
                date._mjesec = KonzolniAlati.ucitajInteger();
                date._godina = KonzolniAlati.ucitajInteger();;

                Kolekcija transakcije = k.GetTransakcijeByDatum(date);
                transakcije.ForEach((Action1Param<Transakcija>) t -> t.Ispis());
            }

            if (izborMenu == 8)
            {
                System.out.print("Deaktiviranje klijenta: Izaberite klijenta");
                System.out.print("\n");
                Klijent k = IzborKlijenta(B1);
                k.Deaktiviraj();
            }
        } while (izborMenu != 9);

        B1.Dealociraj();






    }
}
