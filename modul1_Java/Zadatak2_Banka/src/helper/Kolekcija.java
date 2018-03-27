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
    }

    public final Kolekcija Filter(Func1Param<T, Boolean> f)
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
}




