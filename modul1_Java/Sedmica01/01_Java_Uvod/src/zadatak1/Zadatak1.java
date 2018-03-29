package zadatak1;

import helper.KonzolniAlati;

public class Zadatak1
{
	public static void main(String[] args)
	{
		System.out.println("Unesite x:");
		int x = Integer.parseInt(KonzolniAlati.ucitajString());

		int[] n = new int[x];

		for (int i = 0; i < n.length; i++)
		{
			System.out.println("Unesite broj:");
			n[i] = Integer.parseInt(KonzolniAlati.ucitajString());
		}

		for (int i : n)
		{
			System.out.println(i);
		}
	}

}
