package simulators;


import domain.LampicaULiftu;
import domain.Lift;
import domain.LiftPodaci;
import domain.Sprat;
import domain.TipkaULiftu;
import domain.enums.NaredbeLampice;
import domain.enums.StanjaLifta;
import domain.enums.StatusTipki;
import utilities.FLogger;


public class SimulatorTipki implements Runnable
{
	final private LiftPodaci liftPodaci;

	public SimulatorTipki(LiftPodaci liftPodaci)
	{
		this.liftPodaci = liftPodaci;
	}

	@Override
	public void run()
	{
		while(true)
		{
			for (Sprat sprat : liftPodaci.spratovi)
			{
				for (int i = 0; i < sprat.tipke_u_liftu.size(); i++)
				{
					TipkaULiftu tipkaULiftu = sprat.tipke_u_liftu.get(i);
					if (tipkaULiftu.statusTipki == StatusTipki.TIPKA_STISNUTA)
					{
						tipkaULiftu.statusTipki = StatusTipki.TIPKA_NEAKTIVNA;
						LampicaULiftu lampicaULiftu = sprat.lampice_u_liftu.get(i);
						lampicaULiftu.naredbeLampice = NaredbeLampice.UPALI;
					}
				}
				
				if (sprat.tipka_gore.statusTipki == StatusTipki.TIPKA_STISNUTA)
				{
					Lift liftGore = pronadjiLiftGore(sprat.id);
					if (liftGore== null)
						continue;
					sprat.tipka_gore.statusTipki = StatusTipki.TIPKA_NEAKTIVNA;
					sprat.lampica_gore.naredbeLampice = NaredbeLampice.UPALI;
					
					liftGore.lampice_u_liftu.get(sprat.id).naredbeLampice = NaredbeLampice.UPALI;
					FLogger.info(this, "SimulatorTipki :: SPRAT: " + sprat.id + ", Poziv GORE, za " + liftGore);
				}
				if (sprat.tipka_dole.statusTipki == StatusTipki.TIPKA_STISNUTA)
				{
					Lift liftDole = pronadjiLiftDole(sprat.id);
					if (liftDole== null)
						continue;
					sprat.tipka_dole.statusTipki = StatusTipki.TIPKA_NEAKTIVNA;
					sprat.lampica_dole.naredbeLampice = NaredbeLampice.UPALI;
					
					liftDole.lampice_u_liftu.get(sprat.id).naredbeLampice = NaredbeLampice.UPALI;
					FLogger.info(this, "SimulatorTipki :: SPRAT: " + sprat.id + ", Poziv DOLE, za " + liftDole);
				}
			}
			Sleep(100);
		}
	}

	private Lift pronadjiLiftDole(int polaziste)
	{
		Lift najbliziLift = null;
		int blizina = liftPodaci.brojSpratova;
		for (Lift lift : liftPodaci.liftovi)
		{
			if (lift.stanjaLifta != StanjaLifta.STOJI_OTVOREN)
			{
				if (!daLiPutujePremaDole(lift, polaziste))
					continue;
			}
				
			int d = Math.abs(lift.pozicija - polaziste);
			if (d < blizina)
			{
				blizina = d;
				najbliziLift = lift;
			}
		}
		return najbliziLift;
	}

	private Lift pronadjiLiftGore(int polaziste)
	{
		Lift najbliziLift = null;
		int blizina = liftPodaci.brojSpratova;
		for (Lift lift : liftPodaci.liftovi)
		{
			if (lift.stanjaLifta != StanjaLifta.STOJI_OTVOREN)
			{
				if (!daLiPutujePremaGore(lift, polaziste))
					continue;
			}
				
			int d = Math.abs(lift.pozicija - polaziste);
			if (d < blizina)
			{
				blizina = d;
				najbliziLift = lift;
			}
		}
		return najbliziLift;
	}

	private boolean daLiPutujePremaDole(Lift lift, int polaziste)
	{
		if (lift.pozicija <= polaziste)
			return false; //ve� je pro�ao sprat
		
		int m = Common.getMinSprat(lift);
		if (m>polaziste)
			return false; //nije planirao do�i do polazista putnici
		
		return true;
	}

	private boolean daLiPutujePremaGore(Lift lift, int polaziste)
	{
		if (lift.pozicija >= polaziste)
			return false; //ve� je pro�ao sprat
		
		int m = Common.getMaxSprat(lift);
		if (m<polaziste)
			return false; //nije planirao do�i do polazista putnici
		
		return true;
	}

	private void Sleep(int i)
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
