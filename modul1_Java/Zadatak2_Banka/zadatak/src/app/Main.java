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





    public static void main(String[] args) {

        Banka B1 = new Banka();

        int izborMenu;
        do
        {
            izborMenu = PrikaziMenu();

        } while (izborMenu != 9);


    }
}
