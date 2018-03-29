#pragma once
#include <iostream>
#include "Kolekcija.h"
#include "Klijent.h"
#include "Proizvod.h"
#include "Faktura.h"
using namespace  std;

template <typename Tip>
Tip* odaberi(Kolekcija<Tip*> kolekcija)
{
	int unos;
	do
	{
		int rb = 1;
		kolekcija.ForEach([&](Tip* g) {
			cout << rb++ << ". ";
			g->Ispis();
		});
		cout << "0. Cancel" << endl;
		cout << "Unesite RB: " << endl;
		cin >> unos;
		if (unos == 0)
			return nullptr;
		if (unos >= 1 && unos <= kolekcija.brojac)
			return kolekcija.podaci[unos - 1];
		cout << " !!Pogrešan unos" << endl;
	} while (true);
}