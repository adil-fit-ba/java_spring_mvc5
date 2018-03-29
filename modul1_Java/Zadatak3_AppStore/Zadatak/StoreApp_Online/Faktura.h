#pragma once
#include "Datum.h"
#include "Klijent.h"
#include "stavkafakture.h"
#include "Kolekcija.h"
#include <string>


int FakturaBrojac = 0;

struct Faktura
{
	int _ID;
	Datum _datum;
	Klijent _klijent;
	Kolekcija<StavkaFakture> _stavke;
	
	static Faktura* Napravi(Datum datum, Klijent klijent)
	{
		Faktura* x = new Faktura;
		x->_ID = FakturaBrojac++;
		x->_datum = datum;
		x->_klijent = klijent;
		return x;
	}


	void Ispis()
	{
		cout << "Datum: ";
		_datum.Ispis();
		cout << ", ID: " << _ID << ", ";
		_klijent.Ispis();
		cout << ", suma: " <<  endl;
	}

	

	float GetIznosSuma()
	{
		float suma = 0;

		for (int i=0;i<_stavke.brojac;i++)
		{
			suma += _stavke.Get(i).GetIznos();
		}

		return suma;
	}
};

