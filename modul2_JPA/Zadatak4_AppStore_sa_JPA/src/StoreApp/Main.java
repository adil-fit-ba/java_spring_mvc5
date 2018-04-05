package StoreApp;

import Helper.Action1Param;
import Helper.Datum;
import Models.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        StoreApp sa=StoreApp.napravi(1,"bingo");
        sa.m_klijent_add();
        sa.klijenti.ForEach((Action1Param<Klijent>)t->t.ispis());
    }
}
