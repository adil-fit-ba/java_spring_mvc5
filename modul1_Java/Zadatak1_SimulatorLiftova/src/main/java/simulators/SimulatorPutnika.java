package simulators;

import java.util.Random;

import domain.LampicaZaSmjerLifta;
import domain.Lift;
import domain.LiftPodaci;
import domain.Putnik;
import domain.Sprat;
import domain.enums.SmjerLifta;
import domain.enums.StanjaVrata;
import domain.enums.StatusLampice;
import domain.enums.StatusTipki;

import ui._Starter.Bool;



public class SimulatorPutnika implements Runnable
{
	private final LiftPodaci liftPodaci;
	private Bool pauza = null;

	public SimulatorPutnika(LiftPodaci liftPodaci, Bool pauza)
	{
		this.liftPodaci = liftPodaci;
		this.pauza = pauza;
	}

	@Override
	public void run()
	{
		while (true)
		{
			if (pauza.value)
			{
				sleep(500);
				continue;
			}
			Putnik putnik = buildOsoba();
			sleep(2000);
			pritisniTipkuZaPozivLifta(putnik);

			while (!putnik.napusio)
			{

				//zadatak dovrsiti

				sleep(250);
			}
		}
	}

	private void pritisniTipkuZaPozivLifta(Putnik putnik)
	{
		Sprat sprat = liftPodaci.spratovi.get(putnik.polaziste);
		int d = putnik.odrediste - sprat.id;
		if (d > 0)// smjer gore
			sprat.tipka_gore.statusTipki = StatusTipki.TIPKA_STISNUTA;

		if (d < 0)// smjer dole
			sprat.tipka_dole.statusTipki = StatusTipki.TIPKA_STISNUTA;
	}



	private Putnik buildOsoba()
	{
		int odrediste = Math.abs(new Random().nextInt() % liftPodaci.brojSpratova);
		int polaziste = Math.abs(new Random().nextInt() % liftPodaci.brojSpratova);
		if (polaziste == odrediste)
			odrediste = (odrediste + 1) % liftPodaci.brojSpratova;
		Putnik putnik = new Putnik(odrediste, polaziste, liftPodaci);
		return putnik;
	}

	private void sleep(int i)
	{
		try
		{
			Thread.sleep(i);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
