package Models;

import Helper.Action1Param;
import Helper.Datum;
import Helper.Kolekcija;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Faktura {
    int id;
    Datum datum;
    Klijent klijent;
    public Kolekcija<StavkaFakture> stavke;
    static int fakturaBrojac=0;

    Faktura(){
        stavke=new Kolekcija<>();
    }

    public static Faktura napravi(Datum datum, Klijent klijent){
        Faktura f = new Faktura();
        f.id=fakturaBrojac++;
        f.datum=datum;
        f.klijent=klijent;
        return f;
    }
    public void ispis(){
        System.out.println("Datum:"+datum.ispis()+", Id:"+id+", Suma:"+getIznosSuma());
    }

    public float getIznosSuma() {
        AtomicReference<Float>suma = new AtomicReference<>();
        suma.set(0f);
        stavke.ForEach((Action1Param<StavkaFakture>)t->{
            suma.set(suma.get()+t.getIznos());
        });
        return suma.get();
    }

}
