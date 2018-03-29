#pragma once
#include "Faktura.h"

struct StoreApp
{
	int _ID;
	char _Naziv[20];
	Kolekcija<Proizvod*> Proizvodi;
	Kolekcija<Faktura*> Fakture;
	Kolekcija<Klijent*> Klijenti;

	

	static StoreApp *Napravi(int Id, char* nazivStore)
	{
		StoreApp *y = new StoreApp;
		y->_ID = Id;
		strcpy_s(y->_Naziv, nazivStore);
		return y;
	}

};

