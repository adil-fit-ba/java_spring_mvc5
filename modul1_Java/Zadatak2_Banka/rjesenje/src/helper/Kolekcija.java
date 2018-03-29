package helper;

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

        k.ForEach(new Action1Param<T>() {
            @Override
            public void invoke(T x) {
                Dodaj(x);
            }
        });

        k.ForEach( x -> Dodaj((T) x));

        k.ForEach((Action1Param<T>) this::Dodaj);


        for (int i = 0; i < k.brojac; i++) {
            T t = (T) k.podaci[i];
            Dodaj(t);
        }

    }

    //Kolekcija Filter(function<bool(Tip&)> f)
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
    //void ForEach(function<void(Tip&)> f)
    public final void ForEach(Action1Param<T> f)
    {
        //za svaki element iz kolekcije k izvrsi zadatak f
        for (int i = 0; i < brojac; i++)
        {
            T x = podaci[i];
            f.invoke(x);
        }
    }
}



