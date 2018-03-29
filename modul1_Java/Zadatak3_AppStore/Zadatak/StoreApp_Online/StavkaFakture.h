#pragma once
#include "Proizvod.h"
#include <fstream>
using namespace  std;

int StavkaBrojac = 0;
struct StavkaFakture
{
	int _ID;
	float _kolicina;
	Proizvod _proizvod;
	static StavkaFakture* Napravi(Proizvod proizvod, float kolicina)
	{
		StavkaFakture* s = new StavkaFakture;
		s->_ID = StavkaBrojac++;
		s->_kolicina = kolicina;
		s->_proizvod = proizvod;
		return s;
	}

	float GetIznos()
	{
		return _kolicina * _proizvod._cijena;
	}

	void Ispis()
	{
		cout << _proizvod._naziv << ", " << _proizvod._cijena << " x " << _kolicina << " = " << GetIznos() << endl;
	}




};
