package Helper;

import Models.Klijent;

import java.util.concurrent.atomic.AtomicReference;

public class Kolekcija<T>
{
    public T[] podaci;
    public int brojac = 0;

    public Kolekcija(int max_size) {
        this.max_size = max_size;
        podaci = (T[]) new Object[max_size];
    }

    public Kolekcija() {
        this(40);
    }

    public int max_size;
    public final void Dodaj(T newV)
    {
        podaci[brojac++] = newV;
    }
    public final void Dodaj(Kolekcija k)
    {
        k.ForEach((Action1Param<T>) x -> Dodaj(x));
    }

    public final Kolekcija Filter(Func1Param<T> f)
    {
        Kolekcija newK = new Kolekcija();

        //iz kolekcije k u kolekciju newK kopirati elemente koji zadovoljavaju uslov f
        for (int i = 0; i < brojac; i++)
        {
            T x = podaci[i];
            if (f.invoke(x) == true)
            {
                newK.Dodaj(x);
            }
        }

        return newK;
    }
    public final void ForEach(Action1Param<T> f)
    {
        //za svaki element iz kolekcije k izvrsi zadatak f
        for (int i = 0; i < brojac; i++)
        {
            T x = podaci[i];
            f.invoke(x);
        }
    }


    public T odaberi() {
        int unos;
        do {
            AtomicReference<Integer> rb=new AtomicReference<>(1);
            ForEach((Action1Param<T>) t -> {
                System.out.print(rb.get());

                //ispis fali

                rb.set(rb.get()+1);
            });
            System.out.println("0. Cancel");
            System.out.println("Unesite RB");
            unos=KonzolniAlati.ucitajInteger();
            if(unos==0)
                return null;
            if(unos>=1 && unos<=brojac)
                return podaci[unos-1];
            System.out.println("Pogresan unos!");
        }while(true);
    }
}




