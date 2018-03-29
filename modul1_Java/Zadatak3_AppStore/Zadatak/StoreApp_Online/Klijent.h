#pragma once

int KlijentBrojac = 0;
struct Klijent
{
	char* _ime;
	char _prezime[20];
	int _ID;

	static Klijent* Napravi(char* ime, char* prezime)
	{
		Klijent * k = new Klijent;
		k->_ID = KlijentBrojac++;

		int v = strlen(ime) + 1;
		k->_ime = new char[v];
		strcpy_s(k->_ime, v, ime);
		strcpy_s(k->_prezime, prezime);
		return k;
	}



	void Ispis()
	{
		cout << "ID: "<< _ID<<", "<< _ime << " " << _prezime<<" ";
	}
};
