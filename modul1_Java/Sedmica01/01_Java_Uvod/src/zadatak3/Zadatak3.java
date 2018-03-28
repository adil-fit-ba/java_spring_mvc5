package zadatak3;

import helper.KonzolniAlati;
import zadatak2.Kupac;

import java.util.ArrayList;

public class Zadatak3
{
	static void ispis(ArrayList<Kupac> n)
	{
		for (Kupac k : n)
		{
			System.out.println("#####################");

			System.out.println("  Ime: " + k.getIme());
			System.out.println("Email: " + k.getEmail());
		}
		System.out.println("Ukupno: " + n.size());
	}

	public static void main(String[] args)
	{
		ArrayList<Kupac> N = new ArrayList<>();

		ucitaj(N, 3);
		ispis(N);
	}

	static void ucitaj(ArrayList<Kupac> kupci, int v)
	{
		for (int i = 0; i < v; i++)
		{
			Kupac k = new Kupac();
			System.out.println("==============");

			System.out.println("Ime: ");
			k.setIme(KonzolniAlati.ucitajString());

			System.out.println("Email: ");
			k.setEmail(KonzolniAlati.ucitajString());

			kupci.add(k);
		}
	}

}
