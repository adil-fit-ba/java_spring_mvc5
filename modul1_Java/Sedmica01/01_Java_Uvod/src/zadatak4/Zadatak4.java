package zadatak4;

import helper.KonzolniAlati;
import zadatak2.Kupac;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Zadatak4
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

		ucitaj(N);
		ispis(N);
	}

	static void ucitaj(ArrayList<Kupac> kupci)
	{
		Predicate<Integer> positive1 = i -> i > 0;
		Predicate<Integer> positive2 = new Predicate<Integer>() {
			@Override
			public boolean test(Integer i) {
				return i>0;
			}
		};

		String unos;
		do
		{
			Kupac k = new Kupac();
			System.out.println("==============");

			System.out.println("Ime: ");
			k.setIme(KonzolniAlati.ucitajString());

			System.out.println("Email: ");
			k.setEmail(KonzolniAlati.ucitajString());

			kupci.add(k);
			System.out.println("Nastaviti ? (DA/NE)");
			unos = KonzolniAlati.ucitajString();
		} while (unos.compareToIgnoreCase("da") == 0);
	}
}
