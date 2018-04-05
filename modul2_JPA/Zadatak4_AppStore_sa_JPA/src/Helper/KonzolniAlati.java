package Helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KonzolniAlati
{
    public static String ucitajString()
    {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String result;
        try
        {
            result = reader.readLine();
        } catch (IOException e)
        {
            result = "";
        }
        return result;
    }

    public static Integer ucitajInteger()
    {
        final String s = ucitajString();
        final Integer i = Integer.parseInt(s);
        return i;
    }

    public static Float ucitajFloat() {
        final String s = ucitajString();
        final Float i = Float.parseFloat(s);
        return i;
    }
}

