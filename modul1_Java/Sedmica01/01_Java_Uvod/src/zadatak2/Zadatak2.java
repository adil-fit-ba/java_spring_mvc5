package zadatak2;

import helper.KonzolniAlati;

public class Zadatak2
{
	static void ispis(Kupac[] n)
	{
		for (Kupac k : n)
		{
			System.out.println("#####################");

			System.out.println("  Ime: " + k.getIme());
			System.out.println("Email: " + k.getEmail());
		}
		System.out.println("Ukupno: " + n.length);
	}

	public static void main(String[] args)
	{
		Kupac[] N = new Kupac[3];

		ucitaj(N);
		ispis(N);
	}

	static void ucitaj(Kupac[] n)
	{
		for (int i = 0; i < n.length; i++)
		{
			n[i] = new Kupac();
			System.out.println("==============");

			System.out.print("Ime: ");
			n[i].setIme(KonzolniAlati.ucitajString());

			System.out.print("Email: ");
			n[i].setEmail(KonzolniAlati.ucitajString());
		}
	}

}
