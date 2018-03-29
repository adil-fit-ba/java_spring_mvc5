#pragma once
#include <fstream>

struct Proizvod
{
	int _ID;
	char _naziv[20];
	char _jmj[10];
	float _cijena;
	static Proizvod* Napravi(char* naziv, char* jmj, float cijena)
	{
		static int generalID = 0;
		Proizvod* p = new Proizvod;
		strcpy_s(p->_naziv, naziv);
		strcpy_s(p->_jmj, jmj);
		p->_cijena = cijena;
		p->_ID = generalID++;
		return p;
	}

	void Ispis()
	{
		cout << "Naziv: " << _naziv << ", jmj: " << _jmj << ", _cijena: " << _cijena << endl;
	}

	
};
