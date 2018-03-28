package helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KonzolniAlati
{
	public static String ucitajString()
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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

}
