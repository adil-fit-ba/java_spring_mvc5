#pragma once


#pragma once

#include <iostream>
#include <memory>
#include"Datum.h"
#include <functional>
#include "StoreApp.h"
#include "Odabiri.h"
#include "stavkafakture.h"
#include "Faktura.h"
using namespace std;



struct App
{
StoreApp* _app = nullptr;


//UNOS NOVOG KLIJENTA
void m_klijent_add()
{
	if (_app == nullptr)
	{
		cout << "Greška: StoreApp je null";
		return;
	}

	cout << "UNOS NOVOG KLIJENTA" << endl;
	cout << " Ime: ";
	cin.ignore();
	char ime[100];
	cin.getline(ime, 100);

	cout << " Prezime: ";
	cin.ignore();
	char prezime[100];
	cin.getline(prezime, 100);

	_app->Klijenti.Dodaj(Klijent::Napravi(ime, prezime));
}


//UNOS NOVOG PROIZVODA
void m_proizvod_add()
{
	if (_app == nullptr)
	{
		cout << "Greška: StoreApp je null";
		return;
	}

	cout << "UNOS NOVOG PROIZVODA" << endl;
	cout << " Naziv: ";
	cin.ignore();
	char naziv[100];
	cin.getline(naziv, 100);

	cout << " Jedinica mjere: ";
	cin.ignore();
	char jmj[10];
	cin.getline(jmj, 10);

	cout << " Cijena: ";
	cin.ignore();
	float cijena;
	cin >> cijena;

	_app->Proizvodi.Dodaj(Proizvod::Napravi(naziv, jmj, cijena));
}

//NOVA PRODAJA
void m_prodaja_add()
{
	if (_app == nullptr)
	{
		cout << "Greška: StoreApp je null";
		return;
	}

	cout << "PRODAJA - FAKTURA" << endl;
	cout << "Unesite datum (d m g)" << endl;
	int d, m, g;
	cin >> d >> m >> g;
	Datum datum = Datum::Napravi(d, m, g);

	cout << "Odaberite klijenta: " << endl;
	Klijent* klijent = odaberi(_app->Klijenti);

	Faktura* f = Faktura::Napravi(datum, *klijent);
	_app->Fakture.Dodaj(f);

	Proizvod* proizvod;
	do
	{
		proizvod = odaberi(_app->Proizvodi);
		if (proizvod != nullptr)
		{
			cout << "Unesite kolicinu: " << endl;
			int kolicina;
			cin >> kolicina;
			f->_stavke.Dodaj(*StavkaFakture::Napravi(*proizvod, kolicina));
		}
	} while (proizvod != nullptr);
}

//NOVI STORE
void m_store_new()
{
	if (_app != nullptr)
	{
		cout << "Greška: StoreApp nije zatvoren";
		return;
	}
	cout << "Unesite JIB firme (store)" << endl;
	int id;
	cin >> id;

	cout << "Unesite naziv firme" << endl;
	char naziv[100];
	cin.ignore();
	cin.getline(naziv, 100);
	
	_app = StoreApp::Napravi(id, naziv);
}

void m_prikaz_faktura()
{
	if (_app == nullptr)
	{
		cout << "Greška: StoreApp je zatvoren";
		return;
	}
	_app->Fakture.ForEach([](Faktura* f){
		f->Ispis();
	});
}

void m_test_app()
{
	_app = StoreApp::Napravi(1, "Bingo doo");

	_app->Klijenti.Dodaj(Klijent::Napravi("Denis", "Music"));
	_app->Klijenti.Dodaj(Klijent::Napravi("Adil", "Joldic"));

	_app->Proizvodi.Dodaj(Proizvod::Napravi("Mlijeko", "L", 1.50));
	_app->Proizvodi.Dodaj(Proizvod::Napravi("Jabuke", "KG", 1.30));
	_app->Proizvodi.Dodaj(Proizvod::Napravi("Kafa 500 g", "kom", 6.90));

	Faktura* faktura1 = Faktura::Napravi(Datum::Napravi(25, 5, 2017), *_app->Klijenti.Get(0));
	Faktura* faktura2 = Faktura::Napravi(Datum::Napravi(26, 5, 2017), *_app->Klijenti.Get(1));
	_app->Fakture.Dodaj(faktura1);
	_app->Fakture.Dodaj(faktura2);

	faktura1->_stavke.Dodaj(*StavkaFakture::Napravi(*_app->Proizvodi.Get(0), 2));
	faktura1->_stavke.Dodaj(*StavkaFakture::Napravi(*_app->Proizvodi.Get(1), 2.5));
	faktura2->_stavke.Dodaj(*StavkaFakture::Napravi(*_app->Proizvodi.Get(0), 3));
	faktura2->_stavke.Dodaj(*StavkaFakture::Napravi(*_app->Proizvodi.Get(1), 1.5));
	faktura2->_stavke.Dodaj(*StavkaFakture::Napravi(*_app->Proizvodi.Get(2), 2));

	

	cout << "Suma za fakturu 1 " << _app->Fakture.Get(0)->GetIznosSuma() << endl;
	cout << "Suma za fakturu 2 " << _app->Fakture.Get(1)->GetIznosSuma() << endl;
}

void m_izbornik()
{
	int unos;
	do
	{
		cout << "2. NEW STORE" << endl;
		cout << "4. DODAJ: KLIJENT" << endl;
		cout << "5. DODAJ: PROIZVOD" << endl;
		cout << "6. DODAJ: PRODAJA (FAKTURA)" << endl;
		cout << "7. Prikaz faktura" << endl;
		cout << "8. Dodaj testne podatke i testiraj" << endl;
		cout << "0. EXIT" << endl;

		cin >> unos;
		if (unos == 2) m_store_new();
		if (unos == 4) m_klijent_add();
		if (unos == 5) m_proizvod_add();
		if (unos == 6) m_prodaja_add();
		if (unos == 7) m_prikaz_faktura();
		if (unos == 8) m_test_app();
	} while (unos != 0);
}
};