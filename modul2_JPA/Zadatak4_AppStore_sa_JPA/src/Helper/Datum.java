package Helper;

public class Datum
{
    public int _dan;
    public int _mjesec;
    public int _godina;

    public static Datum Napravi(int d, int m, int g)
    {
        Datum datum = new Datum();
        datum._dan = d;
        datum._mjesec = m;
        datum._godina = g;

        return datum;
    }


    @Override
    public boolean equals(Object datum)
    {
        if (!(datum instanceof Datum))
            return false;

        Datum drugi = (Datum) datum;

        return (_dan == drugi._dan) && (_mjesec == drugi._mjesec) && (_godina == drugi._godina);
    }

    public final void Ispis()
    {
        System.out.print("helper.Datum: ");
        System.out.print(_dan);
        System.out.print(".");
        System.out.print(_mjesec);
        System.out.print(".");
        System.out.print(_godina);
        System.out.print("\n");
    }

    public String ispis(){
        return _dan+"/"+_mjesec+"/"+_godina;
    }
}
