#pragma once


#include<iostream>
using namespace std;

struct Datum
{
	int _dan;
	int _mjesec;
	int _godina;

static Datum Napravi(int d, int m, int g)
{
	Datum datum;
	datum._dan = d;
	datum._mjesec = m;
	datum._godina = g;
	return datum;
}

	bool jednako(Datum& drugi)
	{
		return
			(_dan == drugi._dan) &&
			(_mjesec == drugi._mjesec) &&
			(_godina == drugi._godina);
	}

	void Ispis()
	{
		cout << "Datum: " << _dan << "." << _mjesec << "." << _godina << " ";
	}
};

